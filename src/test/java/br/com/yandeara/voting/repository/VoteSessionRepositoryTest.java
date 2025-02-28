package br.com.yandeara.voting.repository;

import br.com.yandeara.voting.domain.model.Motion;
import br.com.yandeara.voting.domain.model.VoteSession;
import br.com.yandeara.voting.domain.repository.MotionRepository;
import br.com.yandeara.voting.domain.repository.VoteSessionRepository;
import jakarta.validation.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class VoteSessionRepositoryTest {

    @Autowired
    private VoteSessionRepository voteSessionRepository;

    @Autowired
    private MotionRepository motionRepository;

    @Test
    void save_validRequest_shouldBeSaved() {
        Motion motion = new Motion();
        motion.setDescription("Test");

        motion = motionRepository.save(motion);

        VoteSession voteSession = new VoteSession();

        voteSession.setMotion(motion);
        voteSession.setOpeningTime(LocalDateTime.now());
        voteSession.setClosingTime(LocalDateTime.now());

        VoteSession savedVoteSession = voteSessionRepository.save(voteSession);

        assertNotNull(savedVoteSession.getId());
    }

    @Test
    void save_openingTimeNull_shouldThrowViolationException() {
        Motion motion = new Motion();
        motion = motionRepository.save(motion);

        VoteSession voteSession = new VoteSession();

        voteSession.setMotion(motion);
        voteSession.setClosingTime(LocalDateTime.now());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<VoteSession>> violations = validator.validate(voteSession);

        assertEquals(1, violations.size());
        assertEquals("openingTime", violations.iterator().next().getPropertyPath().toString());

        assertThrows(ConstraintViolationException.class, () -> {
            voteSessionRepository.save(voteSession);
        });
    }

    @Test
    void save_closingTimeNull_shouldThrowViolationException() {
        Motion motion = new Motion();
        motion = motionRepository.save(motion);

        VoteSession voteSession = new VoteSession();

        voteSession.setMotion(motion);
        voteSession.setOpeningTime(LocalDateTime.now());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<VoteSession>> violations = validator.validate(voteSession);

        assertEquals(1, violations.size());
        assertEquals("closingTime", violations.iterator().next().getPropertyPath().toString());

        assertThrows(ConstraintViolationException.class, () -> {
            voteSessionRepository.save(voteSession);
        });
    }

    @Test
    void save_MotionNull_shouldThrowViolationException() {
        VoteSession voteSession = new VoteSession();

        voteSession.setOpeningTime(LocalDateTime.now());
        voteSession.setClosingTime(LocalDateTime.now());

        assertThrows(DataIntegrityViolationException.class, () -> {
            voteSessionRepository.save(voteSession);
        });
    }
}
