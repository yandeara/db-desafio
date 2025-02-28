package br.com.yandeara.votacao.repository;

import br.com.yandeara.votacao.domain.model.Pauta;
import br.com.yandeara.votacao.domain.repository.PautaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class PautaRepositoryTest {

    @Autowired
    private PautaRepository pautaRepository;

    @Test
    void assertPautaSaved() {
        Pauta pauta = new Pauta();
        Pauta savedPauta = pautaRepository.save(pauta);

        assertNotNull(savedPauta.getId());
    }

}
