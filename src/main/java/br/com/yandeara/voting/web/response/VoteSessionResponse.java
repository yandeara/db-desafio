package br.com.yandeara.voting.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteSessionResponse {

    private Long id;
    private Long motionId;
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;

}
