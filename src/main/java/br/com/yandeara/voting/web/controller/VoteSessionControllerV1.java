package br.com.yandeara.voting.web.controller;

import br.com.yandeara.voting.application.service.VoteSessionService;
import br.com.yandeara.voting.web.request.VoteSessionRequest;
import br.com.yandeara.voting.web.response.VoteSessionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vote-session")
public class VoteSessionControllerV1 {

    private final VoteSessionService voteSessionService;

    public VoteSessionControllerV1(VoteSessionService voteSessionService) {
        this.voteSessionService = voteSessionService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<VoteSessionResponse> create(@RequestBody VoteSessionRequest voteSessionRequest) {
        return ResponseEntity.ok().body(voteSessionService.create(voteSessionRequest));
    }

}
