package br.com.yandeara.voting.web.response;

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
    private Boolean associateVote;
    private ZonedDateTime voteTime;

}
