package br.com.yandeara.voting.application.service;

import br.com.yandeara.voting.application.exception.AssociateAlreadyVotedException;
import br.com.yandeara.voting.application.exception.MotionAlreadyClosedException;
import br.com.yandeara.voting.application.exception.MotionNotOpenedException;
import br.com.yandeara.voting.application.mapper.VoteMapper;
import br.com.yandeara.voting.domain.model.Motion;
import br.com.yandeara.voting.domain.model.Vote;
import br.com.yandeara.voting.domain.repository.MotionRepository;
import br.com.yandeara.voting.domain.repository.VoteRepository;
import br.com.yandeara.voting.web.request.VoteRequest;
import br.com.yandeara.voting.web.response.VoteCountResponse;
import br.com.yandeara.voting.web.response.VoteResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Slf4j
@Service
public class VoteServiceImpl implements VoteService {

    private final MotionRepository motionRepository;

    private final VoteRepository voteRepository;

    private final VoteMapper voteMapper;

    public VoteServiceImpl(MotionRepository motionRepository, VoteRepository voteRepository, VoteMapper voteMapper) {
        this.motionRepository = motionRepository;
        this.voteRepository = voteRepository;
        this.voteMapper = voteMapper;
    }

    @Override
    public VoteResponse vote(VoteRequest voteRequest) {
        Motion motion = motionRepository
                .findById(voteRequest.getMotionId())
                .orElseThrow(() -> new EntityNotFoundException("Motion not found"));

        if(motion.getOpeningTime() == null)
            throw new MotionNotOpenedException();

        if(motion.getClosingTime().isBefore(ZonedDateTime.now()))
            throw new MotionAlreadyClosedException();

        if(voteRepository.findByAssociateIdAndMotionId(voteRequest.getAssociateId(), voteRequest.getMotionId()).isPresent())
            throw new AssociateAlreadyVotedException();

        Vote vote = voteMapper.toEntity(voteRequest);

        vote.setMotion(motion);
        vote.setVoteTime(ZonedDateTime.now());

        vote = voteRepository.save(vote);

        log.info("Voto Registrado: Pauta: {} - Associado: {}", vote.getMotion().getId(), vote.getAssociateId());

        return voteMapper.toResponse(vote);
    }

    @Override
    public VoteCountResponse countVotes(Long motionId) {
        motionRepository
                .findById(motionId)
                .orElseThrow(() -> new EntityNotFoundException("Motion not found"));

        VoteCountResponse voteCountResponse = voteMapper.toCountResponse(voteRepository.countVotes(motionId));

        voteCountResponse.setMotionId(motionId);

        return voteCountResponse;
    }

}
