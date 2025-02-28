package br.com.yandeara.votacao.application.service;

import br.com.yandeara.votacao.web.request.PautaRequest;
import br.com.yandeara.votacao.web.response.PautaResponse;
import br.com.yandeara.votacao.application.mapper.PautaMapper;
import br.com.yandeara.votacao.domain.model.Pauta;
import br.com.yandeara.votacao.domain.repository.PautaRepository;
import org.springframework.stereotype.Service;

@Service
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;
    private final PautaMapper pautaMapper;

    public PautaServiceImpl(PautaRepository pautaRepository, PautaMapper pautaMapper) {
        this.pautaRepository = pautaRepository;
        this.pautaMapper = pautaMapper;
    }

    @Override
    public PautaResponse create(PautaRequest dto) {
        Pauta pauta = pautaMapper.toEntity(dto);
        pauta = pautaRepository.save(pauta);
        return pautaMapper.toDto(pauta);
    }

}
