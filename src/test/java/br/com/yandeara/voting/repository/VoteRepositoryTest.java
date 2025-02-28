package br.com.yandeara.voting.repository;

import br.com.yandeara.voting.domain.model.Motion;
import br.com.yandeara.voting.domain.model.Vote;
import br.com.yandeara.voting.domain.repository.MotionRepository;
import br.com.yandeara.voting.domain.repository.VoteRepository;
import jakarta.validation.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.ZonedDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class VoteRepositoryTest {

    @Autowired
    private MotionRepository motionRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Test
    void save_validRequest_shouldBeSaved() {
        Motion motion = new Motion();
        motion.setOpeningTime(ZonedDateTime.now());
        motion.setClosingTime(ZonedDateTime.now());

        motion = motionRepository.save(motion);

        Vote vote = new Vote();

        vote.setAssociateId(1L);
        vote.setAssociateVote(true);
        vote.setMotion(motion);

        Vote savedVote = voteRepository.save(vote);

        assertNotNull(savedVote.getId());
    }

    @Test
    void save_uniqueVoteForAssociate_shouldThrowViolationException() {
        Motion motion = new Motion();
        motion.setOpeningTime(ZonedDateTime.now());
        motion.setClosingTime(ZonedDateTime.now());

        motion = motionRepository.save(motion);

        Vote voteOne = new Vote();

        voteOne.setAssociateId(1L);
        voteOne.setMotion(motion);
        voteOne.setAssociateVote(true);

        Vote voteTwo = new Vote();

        voteTwo.setAssociateId(1L);
        voteTwo.setMotion(motion);
        voteTwo.setAssociateVote(true);

        voteRepository.save(voteOne);

        assertThrows(DataIntegrityViolationException.class, () -> {
            voteRepository.save(voteTwo);
        });
    }

    @Test
    void save_associateIdNull_shouldThrowViolationException() {
        Motion motion = new Motion();
        motion.setOpeningTime(ZonedDateTime.now());
        motion.setClosingTime(ZonedDateTime.now());

        motion = motionRepository.save(motion);

        Vote vote = new Vote();

        vote.setMotion(motion);

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
