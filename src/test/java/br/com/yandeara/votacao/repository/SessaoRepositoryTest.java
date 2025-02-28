package br.com.yandeara.votacao.repository;

import br.com.yandeara.votacao.domain.model.Pauta;
import br.com.yandeara.votacao.domain.model.Sessao;
import br.com.yandeara.votacao.domain.repository.PautaRepository;
import br.com.yandeara.votacao.domain.repository.SessaoRepository;
import jakarta.validation.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SessaoRepositoryTest {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @Test
    void assertSessaoSaved() {
        Pauta pauta = new Pauta();
        pauta.setDescricao("Pauta Teste");

        pauta = pautaRepository.save(pauta);

        Sessao sessao = new Sessao();

        sessao.setPauta(pauta);
        sessao.setOpeningTime(LocalDateTime.now());
        sessao.setClosingTime(LocalDateTime.now());

        Sessao savedSessao = sessaoRepository.save(sessao);

        assertNotNull(savedSessao.getId());
    }

    @Test
    void assertValidOpeningTimeViolation() {

        Pauta pauta = new Pauta();
        pauta.setDescricao("Pauta Teste");
        pauta = pautaRepository.save(pauta);

        Sessao sessao = new Sessao();

        sessao.setPauta(pauta);
        sessao.setClosingTime(LocalDateTime.now());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Sessao>> violations = validator.validate(sessao);

        assertEquals(1, violations.size());
        assertEquals("openingTime", violations.iterator().next().getPropertyPath().toString());

        assertThrows(ConstraintViolationException.class, () -> {
            sessaoRepository.save(sessao);
        });
    }

    @Test
    void assertValidClosingTimeViolation() {

        Pauta pauta = new Pauta();
        pauta.setDescricao("Pauta Teste");
        pauta = pautaRepository.save(pauta);

        Sessao sessao = new Sessao();

        sessao.setPauta(pauta);
        sessao.setOpeningTime(LocalDateTime.now());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Sessao>> violations = validator.validate(sessao);

        assertEquals(1, violations.size());
        assertEquals("closingTime", violations.iterator().next().getPropertyPath().toString());

        assertThrows(ConstraintViolationException.class, () -> {
            sessaoRepository.save(sessao);
        });
    }

    @Test
    void assertValidPautaViolation() {
        Sessao sessao = new Sessao();

        sessao.setOpeningTime(LocalDateTime.now());
        sessao.setClosingTime(LocalDateTime.now());

        assertThrows(DataIntegrityViolationException.class, () -> {
            sessaoRepository.save(sessao);
        });
    }

}
