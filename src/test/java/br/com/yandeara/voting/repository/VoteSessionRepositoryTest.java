package br.com.yandeara.voting.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class VoteSessionRepositoryTest {
/*
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



    */
}
