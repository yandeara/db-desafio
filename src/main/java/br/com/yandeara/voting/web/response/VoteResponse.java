package br.com.yandeara.voting.web.response;

import br.com.yandeara.voting.web.request.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponse {

    private Long id;
    private Long motionId;
    private Long associateId;
    private VoteEnum associateVote;
    private ZonedDateTime voteTime;

}
