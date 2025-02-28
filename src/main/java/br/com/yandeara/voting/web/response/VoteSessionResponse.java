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
public class VoteSessionResponse {

    private Long id;
    private String description;
    private ZonedDateTime openingTime;
    private ZonedDateTime closingTime;

}
