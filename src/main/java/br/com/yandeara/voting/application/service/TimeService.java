package br.com.yandeara.voting.application.service;

import java.time.LocalDateTime;

public interface TimeService {

    LocalDateTime addTime(LocalDateTime start, String duration);

}
