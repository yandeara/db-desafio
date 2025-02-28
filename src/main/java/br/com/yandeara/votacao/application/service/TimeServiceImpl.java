package br.com.yandeara.votacao.application.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TimeServiceImpl implements TimeService {

    @Override
    public LocalDateTime addTime(LocalDateTime start, String time) {

        if (time == null || time.isEmpty()) {
            throw new IllegalArgumentException("O tempo de encerramento não pode ser vazio.");
        }

        Pattern pattern = Pattern.compile("(\\d+)([HmsDWy])");
        Matcher matcher = pattern.matcher(time);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Unidade de tempo inválida, Precisa ser:'s' - segundos, 'm' - minutos, 'H' - Horas, 'D' - dias, 'W' - semanas, 'M' - meses ou 'y' - anos.");
        }

        int amount = Integer.parseInt(matcher.group(1));
        char unit = matcher.group(2).charAt(0);

        return switch (unit) {
            case 's' -> start.plusSeconds(amount);
            case 'm' -> start.plusMinutes(amount);
            case 'H' -> start.plusHours(amount);
            case 'D' -> start.plusDays(amount);
            case 'W' -> start.plusWeeks(amount);
            case 'M' -> start.plusMonths(amount);
            case 'y' -> start.plusYears(amount);
            default ->
                    throw new IllegalArgumentException("Unidade de tempo inválida, Precisa ser:'s' - segundos, 'm' - minutos, 'H' - Horas, 'D' - dias, 'W' - semanas, 'M' - meses ou 'y' - anos.");
        };
    }

}
