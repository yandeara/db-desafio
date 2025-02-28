package br.com.yandeara.voting.services;

import br.com.yandeara.voting.application.exception.InvalidTimeFormatException;
import br.com.yandeara.voting.application.exception.MotionAlreadyOpenedException;
import br.com.yandeara.voting.application.mapper.VoteSessionMapper;
import br.com.yandeara.voting.application.service.TimeService;
import br.com.yandeara.voting.application.service.VoteSessionServiceImpl;
import br.com.yandeara.voting.domain.model.Motion;
import br.com.yandeara.voting.domain.repository.MotionRepository;
import br.com.yandeara.voting.web.request.VoteSessionRequest;
import br.com.yandeara.voting.web.response.VoteSessionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VoteSessionServiceTest {

    @Mock
    private MotionRepository motionRepository;

    @Mock
    private VoteSessionMapper voteSessionMapper;

    @Mock
    private TimeService timeService;

    @InjectMocks
    private VoteSessionServiceImpl voteSessionServiceImpl;

    @Test
    public void openSession_validRequest_shouldBeSaved() {
        Motion motion = new Motion(1L, null, null, null);
        VoteSessionResponse voteSessionResponse = new VoteSessionResponse(1L, null, ZonedDateTime.now(), ZonedDateTime.now());

        when(motionRepository.findById(any())).thenReturn(Optional.of(motion));
        when(timeService.addTime(any(), any())).thenReturn(ZonedDateTime.now());
        when(voteSessionMapper.toResponse(any(Motion.class))).thenReturn(voteSessionResponse);

        VoteSessionRequest voteSessionRequest = new VoteSessionRequest(1L, "1m");
        VoteSessionResponse voteSessionResponseSaved = voteSessionServiceImpl.openSession(voteSessionRequest);

        assertNotNull(voteSessionResponseSaved);
        assertNotNull(voteSessionResponseSaved.getId());
    }

    @Test
    public void openSession_alreadyOpenedMotion_shouldThrowException() {
        Motion motion = new Motion(1L, null, ZonedDateTime.now(), ZonedDateTime.now());
        when(motionRepository.findById(any())).thenReturn(Optional.of(motion));

        VoteSessionRequest voteSessionRequest = new VoteSessionRequest(1L, "1m");

        assertThrows(MotionAlreadyOpenedException.class, () -> {
            voteSessionServiceImpl.openSession(voteSessionRequest);
        });
    }

}
