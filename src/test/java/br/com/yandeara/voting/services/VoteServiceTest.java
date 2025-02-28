package br.com.yandeara.voting.services;

import br.com.yandeara.voting.application.exception.MotionAlreadyOpenedException;
import br.com.yandeara.voting.application.mapper.VoteMapper;
import br.com.yandeara.voting.application.mapper.VoteSessionMapper;
import br.com.yandeara.voting.application.service.TimeService;
import br.com.yandeara.voting.application.service.VoteServiceImpl;
import br.com.yandeara.voting.application.service.VoteSessionServiceImpl;
import br.com.yandeara.voting.domain.model.Motion;
import br.com.yandeara.voting.domain.model.Vote;
import br.com.yandeara.voting.domain.repository.MotionRepository;
import br.com.yandeara.voting.domain.repository.VoteRepository;
import br.com.yandeara.voting.web.request.VoteRequest;
import br.com.yandeara.voting.web.request.VoteSessionRequest;
import br.com.yandeara.voting.web.response.VoteResponse;
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
public class VoteServiceTest {

    @Mock
    private MotionRepository motionRepository;

    @Mock
    private VoteMapper voteMapper;

    @Mock
    private VoteRepository voteRepository;

    @InjectMocks
    private VoteServiceImpl voteServiceImpl;

    @Test
    public void vote_validRequest_shouldBeSaved() {
        Motion motion = new Motion(1L, null, ZonedDateTime.now(), ZonedDateTime.now().plusDays(1L));
        Vote vote = new Vote(1L, motion, 1L, true, ZonedDateTime.now());

        VoteResponse voteResponse = new VoteResponse(1L, 1L, 1L, true, ZonedDateTime.now());

        when(motionRepository.findById(any())).thenReturn(Optional.of(motion));
        when(voteMapper.toEntity(any())).thenReturn(vote);
        when(voteMapper.toResponse(any())).thenReturn(voteResponse);
        when(voteRepository.save(any())).thenReturn(vote);

        VoteRequest voteRequest = new VoteRequest(1L, 1L, true);
        VoteResponse voteResponseSaved = voteServiceImpl.vote(voteRequest);

        assertNotNull(voteResponseSaved);
        assertNotNull(voteResponseSaved.getId());
    }


}
