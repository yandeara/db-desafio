package br.com.yandeara.voting.application.service;

import br.com.yandeara.voting.web.request.VoteRequest;
import br.com.yandeara.voting.web.response.VoteCountResponse;
import br.com.yandeara.voting.web.response.VoteResponse;

public interface VoteService {

    VoteResponse vote(VoteRequest voteRequest);

    VoteCountResponse countVotes(Long motionId);

}
