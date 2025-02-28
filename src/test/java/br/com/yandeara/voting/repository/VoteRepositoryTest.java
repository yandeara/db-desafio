package br.com.yandeara.voting.repository;

import br.com.yandeara.voting.domain.model.Motion;
import br.com.yandeara.voting.domain.model.VoteSession;
import br.com.yandeara.voting.domain.model.Vote;
import br.com.yandeara.voting.domain.repository.MotionRepository;
import br.com.yandeara.voting.domain.repository.VoteSessionRepository;
import br.com.yandeara.voting.domain.repository.VoteRepository;
import jakarta.validation.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class VoteRepositoryTest {

    @Autowired
    private VoteSessionRepository voteSessionRepository;

    @Autowired
    private MotionRepository motionRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Test
    void save_validRequest_shouldBeSaved() {
        Motion motion = new Motion();
        motion = motionRepository.save(motion);

        VoteSession voteSession = new VoteSession();

        voteSession.setMotion(motion);
        voteSession.setOpeningTime(LocalDateTime.now());
        voteSession.setClosingTime(LocalDateTime.now());

        voteSession = voteSessionRepository.save(voteSession);

        Vote vote = new Vote();

        vote.setAssociateId(1L);
        vote.setAssociateVote(true);
        vote.setVoteSession(voteSession);

        Vote savedVote = voteRepository.save(vote);

        assertNotNull(savedVote.getId());
    }

    @Test
    void save_uniqueVoteForAssociate_shouldThrowViolationException() {
        Motion motion = new Motion();
        motion = motionRepository.save(motion);

        VoteSession voteSession = new VoteSession();

        voteSession.setMotion(motion);
        voteSession.setOpeningTime(LocalDateTime.now());
        voteSession.setClosingTime(LocalDateTime.now());

        voteSession = voteSessionRepository.save(voteSession);

        Vote voteOne = new Vote();

        voteOne.setAssociateId(1L);
        voteOne.setVoteSession(voteSession);
        voteOne.setAssociateVote(true);

        Vote voteTwo = new Vote();

        voteTwo.setAssociateId(1L);
        voteTwo.setVoteSession(voteSession);
        voteTwo.setAssociateVote(true);

        voteRepository.save(voteOne);

        assertThrows(DataIntegrityViolationException.class, () -> {
            voteRepository.save(voteTwo);
        });
    }

    @Test
    void save_associateIdNull_shouldThrowViolationException() {
        Motion motion = new Motion();
        motion = motionRepository.save(motion);

        VoteSession voteSession = new VoteSession();

        voteSession.setMotion(motion);
        voteSession.setOpeningTime(LocalDateTime.now());
        voteSession.setClosingTime(LocalDateTime.now());

        voteSession = voteSessionRepository.save(voteSession);

        Vote vote = new Vote();

        vote.setVoteSession(voteSession);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Vote>> violations = validator.validate(vote);

        assertEquals(1, violations.size());
        assertEquals("associateId", violations.iterator().next().getPropertyPath().toString());

        assertThrows(ConstraintViolationException.class, () -> {
            voteRepository.save(vote);
        });
    }

    @Test
    void save_sessionIdNull_shouldThrowViolationException() {
        Vote vote = new Vote();

        vote.setAssociateId(1L);
        vote.setAssociateVote(true);

        assertThrows(DataIntegrityViolationException.class, () -> {
            voteRepository.save(vote);
        });
    }
}
