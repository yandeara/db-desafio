package br.com.yandeara.voting.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteCountDto {

    private Long totalYesVotes;
    private Long totalNoVotes;

}
