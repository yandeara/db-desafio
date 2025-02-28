package br.com.yandeara.voting.application.service;

import br.com.yandeara.voting.application.mapper.VoteSessionMapper;
import br.com.yandeara.voting.domain.model.VoteSession;
import br.com.yandeara.voting.domain.repository.MotionRepository;
import br.com.yandeara.voting.domain.repository.VoteSessionRepository;
import br.com.yandeara.voting.web.request.VoteSessionRequest;
import br.com.yandeara.voting.web.response.VoteSessionResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VoteSessionServiceImpl implements VoteSessionService {

    private final VoteSessionRepository voteSessionRepository;
    private final TimeService timeService;
    private final MotionRepository motionRepository;
    private final VoteSessionMapper voteSessionMapper;

    public VoteSessionServiceImpl(VoteSessionRepository voteSessionRepository, TimeService timeService, MotionRepository motionRepository, VoteSessionMapper voteSessionMapper) {
        this.voteSessionRepository = voteSessionRepository;
        this.timeService = timeService;
        this.motionRepository = motionRepository;
        this.voteSessionMapper = voteSessionMapper;
    }

    @Override
    public VoteSessionResponse create(VoteSessionRequest voteSessionRequest) {
        VoteSession voteSession = new VoteSession();

        voteSession.setOpeningTime(LocalDateTime.now());
        voteSession.setClosingTime(timeService.addTime(
                voteSession.getOpeningTime(),
                voteSessionRequest.getSessionDuration() != null ? voteSessionRequest.getSessionDuration() : "1m"));
        voteSession.setMotion(motionRepository
                .findById(voteSessionRequest.getMotionId())
                .orElseThrow(() -> new EntityNotFoundException("Motion not found")));

        voteSessionRepository.save(voteSession);

        return voteSessionMapper.toResponse(voteSession);
    }

}
