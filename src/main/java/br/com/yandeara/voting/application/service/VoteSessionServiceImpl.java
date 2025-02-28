package br.com.yandeara.voting.application.service;

import br.com.yandeara.voting.application.exception.MotionAlreadyOpenedException;
import br.com.yandeara.voting.application.mapper.VoteSessionMapper;
import br.com.yandeara.voting.domain.model.Motion;
import br.com.yandeara.voting.domain.repository.MotionRepository;
import br.com.yandeara.voting.web.request.VoteSessionRequest;
import br.com.yandeara.voting.web.response.VoteSessionResponse;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class VoteSessionServiceImpl implements VoteSessionService {

    private final TimeService timeService;
    private final MotionRepository motionRepository;
    private final VoteSessionMapper voteSessionMapper;

    public VoteSessionServiceImpl(TimeService timeService, MotionRepository motionRepository, VoteSessionMapper voteSessionMapper) {
        this.timeService = timeService;
        this.motionRepository = motionRepository;
        this.voteSessionMapper = voteSessionMapper;
    }

    @Override
    public VoteSessionResponse openSession(VoteSessionRequest voteSessionRequest) {
        Motion motion = motionRepository
                .findById(voteSessionRequest.getMotionId())
                .orElseThrow(() -> new EntityNotFoundException("Motion not found"));

        if(motion.getOpeningTime() != null)
            throw new MotionAlreadyOpenedException();

        motion.setOpeningTime(ZonedDateTime.now());
        motion.setClosingTime(timeService.addTime(
                motion.getOpeningTime(),
                voteSessionRequest.getSessionDuration() != null ? voteSessionRequest.getSessionDuration() : "1m"));

        motionRepository.save(motion);

        return voteSessionMapper.toResponse(motion);
    }

}
