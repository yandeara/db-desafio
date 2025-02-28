package br.com.yandeara.votacao.services;

import br.com.yandeara.votacao.application.mapper.PautaMapper;
import br.com.yandeara.votacao.application.service.PautaServiceImpl;
import br.com.yandeara.votacao.domain.model.Pauta;
import br.com.yandeara.votacao.domain.repository.PautaRepository;
import br.com.yandeara.votacao.web.request.PautaRequest;
import br.com.yandeara.votacao.web.response.PautaResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private PautaMapper pautaMapper;

    @InjectMocks
    private PautaServiceImpl pautaService;

    @Test
    public void assertPautaCreation() {
        Pauta pauta = new Pauta(1L, "Descricao Teste");
        PautaResponse pautaResponse = new PautaResponse(1L, "Descricao Teste");

        when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);

        when(pautaMapper.toDto(any(Pauta.class))).thenReturn(pautaResponse);
        when(pautaMapper.toEntity(any(PautaRequest.class))).thenReturn(pauta);

        PautaResponse pautaResponseSaved = pautaService.create(new PautaRequest());

        assertNotNull(pautaResponseSaved);
        assertNotNull(pautaResponseSaved.getId());
    }

}
