package br.com.yandeara.voting.web.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequest {

    @NotNull(message = "Motion Id is required")
    private Long motionId;

    @NotNull(message = "Associate Id is required")
    private Long associateId;

    @NotNull(message = "Associate Vote is required")
    private VoteEnum associateVote;

}
