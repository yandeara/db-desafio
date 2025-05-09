package br.com.yandeara.voting.web.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteSessionRequest {

    @NotNull(message = "Motion Id is required")
    private Long motionId;
    private String sessionDuration;

}
