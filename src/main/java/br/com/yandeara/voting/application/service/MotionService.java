package br.com.yandeara.voting.application.service;

import br.com.yandeara.voting.web.request.MotionRequest;
import br.com.yandeara.voting.web.response.MotionResponse;

public interface MotionService {

    MotionResponse create(MotionRequest request);

}
