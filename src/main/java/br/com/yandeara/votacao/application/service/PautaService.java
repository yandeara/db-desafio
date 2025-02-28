package br.com.yandeara.votacao.application.service;

import br.com.yandeara.votacao.web.request.PautaRequest;
import br.com.yandeara.votacao.web.response.PautaResponse;

public interface PautaService {

    PautaResponse create(PautaRequest pauta);

}
