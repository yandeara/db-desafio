package br.com.yandeara.voting.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteSessionRequest {

    private Long motionId;
    private String sessionDuration;

}
