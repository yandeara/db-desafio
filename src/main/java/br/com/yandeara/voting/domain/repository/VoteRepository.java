package br.com.yandeara.voting.domain.repository;

import br.com.yandeara.voting.domain.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByAssociateIdAndMotionId(Long associateId, Long motionId);

}
