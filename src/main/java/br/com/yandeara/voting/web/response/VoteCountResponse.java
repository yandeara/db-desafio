package br.com.yandeara.voting.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteCountResponse {
    private Long motionId;
    private Long totalYesVotes;
    private Long totalNoVotes;
}
