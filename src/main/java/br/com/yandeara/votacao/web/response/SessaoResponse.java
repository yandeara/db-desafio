package br.com.yandeara.votacao.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessaoResponse {

    private Long id;
    private Long pautaId;
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;

}
