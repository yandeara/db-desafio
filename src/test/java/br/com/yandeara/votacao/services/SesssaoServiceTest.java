package br.com.yandeara.votacao.services;

import br.com.yandeara.votacao.application.mapper.PautaMapper;
import br.com.yandeara.votacao.application.mapper.SessaoMapper;
import br.com.yandeara.votacao.application.service.PautaServiceImpl;
import br.com.yandeara.votacao.application.service.SessaoServiceImpl;
import br.com.yandeara.votacao.application.service.TimeService;
import br.com.yandeara.votacao.domain.model.Pauta;
import br.com.yandeara.votacao.domain.model.Sessao;
import br.com.yandeara.votacao.domain.repository.PautaRepository;
import br.com.yandeara.votacao.domain.repository.SessaoRepository;
import br.com.yandeara.votacao.web.request.PautaRequest;
import br.com.yandeara.votacao.web.request.SessaoRequest;
import br.com.yandeara.votacao.web.response.PautaResponse;
import br.com.yandeara.votacao.web.response.SessaoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SesssaoServiceTest {

    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private SessaoMapper sessaoMapper;

    @Mock
    private TimeService timeService;

    @InjectMocks
    private SessaoServiceImpl sessaoService;

    @Test
    public void assertSessaoCreation() {
        Pauta pauta = new Pauta(1L, "Descricao Teste");
        Sessao sessao = new Sessao(1L, pauta, LocalDateTime.now(), LocalDateTime.now());

        when(pautaRepository.findById(any())).thenReturn(Optional.of(pauta));
        when(timeService.addTime(any(), any())).thenReturn(LocalDateTime.now());
        when(sessaoRepository.save(any(Sessao.class))).thenReturn(sessao);

        SessaoRequest sessaoRequest = new SessaoRequest(1L, "1m");

        SessaoResponse sessaoResponse = new SessaoResponse(1L, 1L, LocalDateTime.now(), LocalDateTime.now());

        when(sessaoMapper.toDto(any(Sessao.class))).thenReturn(sessaoResponse);

        SessaoResponse sessaoResponseSaved = sessaoService.create(sessaoRequest);

        assertNotNull(sessaoResponseSaved);
        assertNotNull(sessaoResponseSaved.getId());
    }

}
