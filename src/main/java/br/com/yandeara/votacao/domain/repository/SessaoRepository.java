package br.com.yandeara.votacao.domain.repository;

import br.com.yandeara.votacao.domain.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
}
