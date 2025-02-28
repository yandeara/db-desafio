package br.com.yandeara.voting.domain.repository;

import br.com.yandeara.voting.domain.model.VoteSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteSessionRepository extends JpaRepository<VoteSession, Long> {
}
