package br.com.yandeara.votacao.application.service;

import java.time.LocalDateTime;

public interface TimeService {

    LocalDateTime addTime(LocalDateTime start, String time);

}
