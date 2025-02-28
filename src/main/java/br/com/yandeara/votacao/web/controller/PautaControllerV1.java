package br.com.yandeara.votacao.web.controller;

import br.com.yandeara.votacao.web.request.PautaRequest;
import br.com.yandeara.votacao.web.response.PautaResponse;
import br.com.yandeara.votacao.application.service.PautaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pauta")
public class PautaControllerV1 {

    private final PautaService pautaService;

    public PautaControllerV1(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<PautaResponse> create(@RequestBody(required = false) PautaRequest pautaRequest) {

        PautaResponse pautaResponseDTO = pautaService.create(pautaRequest);

        return ResponseEntity.ok().body(pautaResponseDTO);
    }

}
