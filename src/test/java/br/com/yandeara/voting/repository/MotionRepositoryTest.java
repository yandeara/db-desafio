package br.com.yandeara.voting.repository;

import br.com.yandeara.voting.domain.model.Motion;
import br.com.yandeara.voting.domain.repository.MotionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class MotionRepositoryTest {

    @Autowired
    private MotionRepository motionRepository;

    @Test
    void save_validRequest_shouldBeSaved() {
        Motion motion = new Motion();
        Motion savedMotion = motionRepository.save(motion);

        assertNotNull(savedMotion.getId());
    }

}
