package br.com.yandeara.votacao.application.service;

import br.com.yandeara.votacao.application.mapper.SessaoMapper;
import br.com.yandeara.votacao.domain.model.Pauta;
import br.com.yandeara.votacao.domain.model.Sessao;
import br.com.yandeara.votacao.domain.repository.PautaRepository;
import br.com.yandeara.votacao.domain.repository.SessaoRepository;
import br.com.yandeara.votacao.web.request.SessaoRequest;
import br.com.yandeara.votacao.web.response.SessaoResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessaoServiceImpl implements SessaoService {

    private final SessaoRepository sessaoRepository;
    private final TimeService timeService;
    private final PautaRepository pautaRepository;
    private final SessaoMapper sessaoMapper;

    public SessaoServiceImpl(SessaoRepository sessaoRepository, TimeService timeService, PautaRepository pautaRepository, SessaoMapper sessaoMapper) {
        this.sessaoRepository = sessaoRepository;
        this.timeService = timeService;
        this.pautaRepository = pautaRepository;
        this.sessaoMapper = sessaoMapper;
    }

    @Override
    public SessaoResponse create(SessaoRequest sessaoRequest) {
        Sessao sessao = new Sessao();

        sessao.setOpeningTime(LocalDateTime.now());
        sessao.setClosingTime(timeService.addTime(
                sessao.getOpeningTime(),
                sessaoRequest.getSessaoDuration() != null ? sessaoRequest.getSessaoDuration() : "1m"));
        sessao.setPauta(pautaRepository
                .findById(sessaoRequest.getPautaId())
                .orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada.")));

        sessaoRepository.save(sessao);

        return sessaoMapper.toDto(sessao);
    }

}
