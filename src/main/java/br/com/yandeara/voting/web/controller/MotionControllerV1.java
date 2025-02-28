package br.com.yandeara.voting.web.controller;

import br.com.yandeara.voting.web.request.MotionRequest;
import br.com.yandeara.voting.web.response.MotionResponse;
import br.com.yandeara.voting.application.service.MotionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/motion")
public class MotionControllerV1 {

    private final MotionService motionService;

    public MotionControllerV1(MotionService motionService) {
        this.motionService = motionService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<MotionResponse> create(@RequestBody(required = false) MotionRequest motionRequest) {
        return ResponseEntity.ok().body(motionService.create(motionRequest));
    }

}
