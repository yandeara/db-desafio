package br.com.yandeara.votacao.application.service;

import br.com.yandeara.votacao.web.request.SessaoRequest;
import br.com.yandeara.votacao.web.response.SessaoResponse;

public interface SessaoService {

    SessaoResponse create(SessaoRequest sessaoRequest);

}
