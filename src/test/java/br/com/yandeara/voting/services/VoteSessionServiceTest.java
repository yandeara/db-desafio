package br.com.yandeara.voting.services;

import br.com.yandeara.voting.application.mapper.VoteSessionMapper;
import br.com.yandeara.voting.application.service.VoteSessionServiceImpl;
import br.com.yandeara.voting.application.service.TimeService;
import br.com.yandeara.voting.domain.model.Motion;
import br.com.yandeara.voting.domain.model.VoteSession;
import br.com.yandeara.voting.domain.repository.MotionRepository;
import br.com.yandeara.voting.domain.repository.VoteSessionRepository;
import br.com.yandeara.voting.web.request.VoteSessionRequest;
import br.com.yandeara.voting.web.response.VoteSessionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class VoteSessionServiceTest {

    @Mock
    private VoteSessionRepository voteSessionRepository;

    @Mock
    private MotionRepository motionRepository;

    @Mock
    private VoteSessionMapper voteSessionMapper;

    @Mock
    private TimeService timeService;

    @InjectMocks
    private VoteSessionServiceImpl voteSessionServiceImpl;

    @Test
    public void create_validRequest_shouldBeSaved() {
        Motion motion = new Motion(1L, null);
        VoteSession voteSession = new VoteSession(1L, motion, LocalDateTime.now(), LocalDateTime.now());
        VoteSessionResponse voteSessionResponse = new VoteSessionResponse(1L, 1L, LocalDateTime.now(), LocalDateTime.now());

        when(motionRepository.findById(any())).thenReturn(Optional.of(motion));
        when(timeService.addTime(any(), any())).thenReturn(LocalDateTime.now());
        when(voteSessionRepository.save(any(VoteSession.class))).thenReturn(voteSession);
        when(voteSessionMapper.toResponse(any(VoteSession.class))).thenReturn(voteSessionResponse);

        VoteSessionRequest voteSessionRequest = new VoteSessionRequest(1L, "1m");
        VoteSessionResponse voteSessionResponseSaved = voteSessionServiceImpl.create(voteSessionRequest);

        assertNotNull(voteSessionResponseSaved);
        assertNotNull(voteSessionResponseSaved.getId());
    }

}
