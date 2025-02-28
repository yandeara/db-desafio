package br.com.yandeara.votacao.web.controller;

import br.com.yandeara.votacao.application.service.SessaoService;
import br.com.yandeara.votacao.web.request.SessaoRequest;
import br.com.yandeara.votacao.web.response.SessaoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sessao")
public class SessaoControllerV1 {

    private final SessaoService sessaoService;

    public SessaoControllerV1(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<SessaoResponse> create(@RequestBody SessaoRequest sessaoRequest) {
        return ResponseEntity.ok().body(sessaoService.create(sessaoRequest));
    }

}
