package br.com.yandeara.voting.application.service;

import java.time.ZonedDateTime;

public interface TimeService {

    ZonedDateTime addTime(ZonedDateTime start, String duration);

}
