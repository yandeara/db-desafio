package br.com.yandeara.votacao.repository;

import br.com.yandeara.votacao.domain.model.Pauta;
import br.com.yandeara.votacao.domain.model.Sessao;
import br.com.yandeara.votacao.domain.model.Voto;
import br.com.yandeara.votacao.domain.repository.PautaRepository;
import br.com.yandeara.votacao.domain.repository.SessaoRepository;
import br.com.yandeara.votacao.domain.repository.VotoRepository;
import jakarta.validation.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class VotoRepositoryTest {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private VotoRepository votoRepository;

    @Test
    void assertVotoSaved() {
        Pauta pauta = new Pauta();
        pauta.setDescricao("Pauta Teste");

        pauta = pautaRepository.save(pauta);

        Sessao sessao = new Sessao();

        sessao.setPauta(pauta);
        sessao.setOpeningTime(LocalDateTime.now());
        sessao.setClosingTime(LocalDateTime.now());

        sessao = sessaoRepository.save(sessao);

        Voto voto = new Voto();

        voto.setAssociadoId(1L);
        voto.setVoto(true);
        voto.setSessao(sessao);

        Voto savedVoto = votoRepository.save(voto);

        assertNotNull(savedVoto.getId());
    }

    @Test
    void assertUniqueVotoForAssociado() {

        Pauta pauta = new Pauta();
        pauta.setDescricao("Pauta Teste");
        pauta = pautaRepository.save(pauta);

        Sessao sessao = new Sessao();

        sessao.setPauta(pauta);
        sessao.setOpeningTime(LocalDateTime.now());
        sessao.setClosingTime(LocalDateTime.now());

        sessao = sessaoRepository.save(sessao);

        Voto votoOne = new Voto();

        votoOne.setAssociadoId(1L);
        votoOne.setSessao(sessao);
        votoOne.setVoto(true);

        Voto votoTwo = new Voto();

        votoTwo.setAssociadoId(1L);
        votoTwo.setSessao(sessao);
        votoTwo.setVoto(true);

        votoRepository.save(votoOne);

        assertThrows(DataIntegrityViolationException.class, () -> {
            votoRepository.save(votoTwo);
        });
    }

    @Test
    void assertValidAssociadoViolation() {

        Pauta pauta = new Pauta();
        pauta.setDescricao("Pauta Teste");
        pauta = pautaRepository.save(pauta);

        Sessao sessao = new Sessao();

        sessao.setPauta(pauta);
        sessao.setOpeningTime(LocalDateTime.now());
        sessao.setClosingTime(LocalDateTime.now());

        sessao = sessaoRepository.save(sessao);

        Voto voto = new Voto();

        voto.setSessao(sessao);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Voto>> violations = validator.validate(voto);

        assertEquals(1, violations.size());
        assertEquals("associadoId", violations.iterator().next().getPropertyPath().toString());

        assertThrows(ConstraintViolationException.class, () -> {
            votoRepository.save(voto);
        });
    }

    @Test
    void assertValidSessaoViolation() {
        Voto voto = new Voto();

        voto.setAssociadoId(1L);
        voto.setVoto(true);

        assertThrows(DataIntegrityViolationException.class, () -> {
            votoRepository.save(voto);
        });
    }





}
