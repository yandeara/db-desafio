package br.com.yandeara.voting.application.service;

import br.com.yandeara.voting.application.exception.InvalidTimeFormatException;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TimeServiceImpl implements TimeService {

    @Override
    public ZonedDateTime addTime(ZonedDateTime start, String duration) {
        if (duration == null || duration.isEmpty()) {
            throw new InvalidTimeFormatException();
        }

        Pattern pattern = Pattern.compile("(\\d+)([smHDWMy])");
        Matcher matcher = pattern.matcher(duration);

        if (!matcher.matches()) {
            throw new InvalidTimeFormatException();
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
            default -> throw new InvalidTimeFormatException();
        };
    }

}
