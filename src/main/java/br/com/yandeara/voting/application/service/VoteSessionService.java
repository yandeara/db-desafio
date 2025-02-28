package br.com.yandeara.voting.application.service;

import br.com.yandeara.voting.web.request.VoteSessionRequest;
import br.com.yandeara.voting.web.response.VoteSessionResponse;

public interface VoteSessionService {

    VoteSessionResponse create(VoteSessionRequest voteSessionRequest);

}
