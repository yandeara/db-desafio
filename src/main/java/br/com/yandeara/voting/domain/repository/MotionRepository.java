package br.com.yandeara.voting.domain.repository;

import br.com.yandeara.voting.domain.model.Motion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotionRepository extends JpaRepository<Motion, Long> {
}
