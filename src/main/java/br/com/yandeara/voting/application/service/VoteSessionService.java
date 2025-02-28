package br.com.yandeara.voting.application.service;

import br.com.yandeara.voting.web.request.VoteSessionRequest;
import br.com.yandeara.voting.web.response.VoteSessionResponse;
import org.apache.coyote.BadRequestException;

public interface VoteSessionService {

    VoteSessionResponse openSession(VoteSessionRequest voteSessionRequest);

}
