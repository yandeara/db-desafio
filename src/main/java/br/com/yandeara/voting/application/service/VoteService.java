package br.com.yandeara.voting.application.service;

import br.com.yandeara.voting.web.request.VoteRequest;
import br.com.yandeara.voting.web.request.VoteSessionRequest;
import br.com.yandeara.voting.web.response.VoteResponse;
import br.com.yandeara.voting.web.response.VoteSessionResponse;

public interface VoteService {

    VoteResponse vote(VoteRequest voteRequest);

}
