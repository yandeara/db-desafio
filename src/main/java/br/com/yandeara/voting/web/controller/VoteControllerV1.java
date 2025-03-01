package br.com.yandeara.voting.web.controller;

import br.com.yandeara.voting.application.service.VoteService;
import br.com.yandeara.voting.web.request.VoteRequest;
import br.com.yandeara.voting.web.response.VoteCountResponse;
import br.com.yandeara.voting.web.response.VoteResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vote")
public class VoteControllerV1 {

    private final VoteService voteService;

    public VoteControllerV1(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(
            summary = "Criar Voto",
            description = "Este endpoint permite a um associado votar em uma pauta."
    )
    public ResponseEntity<VoteResponse> create(@Valid @RequestBody VoteRequest voteRequest) {
        return ResponseEntity.ok().body(voteService.vote(voteRequest));
    }

    @GetMapping(path = "/count", produces = "application/json")
    @Operation(
            summary = "Contar Votos",
            description = "Este endpoint retorna quantos votos de SIM e NÃO uma pauta recebeu."
    )
    public ResponseEntity<VoteCountResponse> countVotes(@RequestParam("motionId") Long motionId) {
        return ResponseEntity.ok().body(voteService.countVotes(motionId));
    }

}
