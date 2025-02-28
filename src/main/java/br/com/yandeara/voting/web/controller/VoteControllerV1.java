package br.com.yandeara.voting.web.controller;

import br.com.yandeara.voting.application.service.VoteService;
import br.com.yandeara.voting.web.request.VoteRequest;
import br.com.yandeara.voting.web.response.VoteCountResponse;
import br.com.yandeara.voting.web.response.VoteResponse;
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
    public ResponseEntity<VoteResponse> create(@Valid @RequestBody VoteRequest voteRequest) {
        return ResponseEntity.ok().body(voteService.vote(voteRequest));
    }

    @GetMapping(path = "/count", produces = "application/json")
    public ResponseEntity<VoteCountResponse> countVotes(@RequestParam("motionId") Long motionId) {
        return ResponseEntity.ok().body(voteService.countVotes(motionId));
    }

}
