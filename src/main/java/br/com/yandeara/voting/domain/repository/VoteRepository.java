package br.com.yandeara.voting.domain.repository;

import br.com.yandeara.voting.domain.dto.VoteCountDto;
import br.com.yandeara.voting.domain.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByAssociateIdAndMotionId(Long associateId, Long motionId);

    @Query("""
        SELECT new br.com.yandeara.voting.domain.dto.VoteCountDto(
            COUNT(CASE WHEN v.associateVote = TRUE THEN 1 END),
            COUNT(CASE WHEN v.associateVote = FALSE THEN 1 END)
        )
        FROM Vote v
        WHERE v.motion.id = :motionId 
    """)
    VoteCountDto countVotes(@Param("motionId") Long motionId);

}
