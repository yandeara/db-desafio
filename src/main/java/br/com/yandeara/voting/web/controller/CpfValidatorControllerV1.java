package br.com.yandeara.voting.web.controller;

import br.com.yandeara.voting.application.service.CpfValidatorService;
import br.com.yandeara.voting.web.response.CpfValidatorResponse;
import br.com.yandeara.voting.web.response.VoteResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cpf")
public class CpfValidatorControllerV1 {

    private final CpfValidatorService cpfValidatorService;

    public CpfValidatorControllerV1(CpfValidatorService cpfValidatorService) {
        this.cpfValidatorService = cpfValidatorService;
    }

    @GetMapping(produces = "application/json")
    @Operation(
            summary = "Valida CPF",
            description = "Este endpoint valida se um CPF é valido e se está habilitado a votar."
    )
    public ResponseEntity<CpfValidatorResponse> validate(@RequestParam("cpf") String cpf) {
        return ResponseEntity.ok().body(cpfValidatorService.validate(cpf));
    }

}
