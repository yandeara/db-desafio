package br.com.yandeara.voting.web.controller;

import br.com.yandeara.voting.application.service.VoteSessionService;
import br.com.yandeara.voting.web.request.MotionRequest;
import br.com.yandeara.voting.web.request.VoteSessionRequest;
import br.com.yandeara.voting.web.response.MotionResponse;
import br.com.yandeara.voting.application.service.MotionService;
import br.com.yandeara.voting.web.response.VoteSessionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/motion")
public class MotionControllerV1 {

    private final MotionService motionService;

    private final VoteSessionService voteSessionService;

    public MotionControllerV1(MotionService motionService, VoteSessionService voteSessionService) {
        this.motionService = motionService;
        this.voteSessionService = voteSessionService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<MotionResponse> create(@RequestBody(required = false) MotionRequest motionRequest) {
        return ResponseEntity.ok().body(motionService.create(motionRequest));
    }

    @PostMapping(path = "/open-session", consumes = "application/json", produces = "application/json")
    public ResponseEntity<VoteSessionResponse> openSession(@RequestBody VoteSessionRequest voteSessionRequest) {
        return ResponseEntity.ok().body(voteSessionService.openSession(voteSessionRequest));
    }

}
