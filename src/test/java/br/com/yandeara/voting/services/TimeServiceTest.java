package br.com.yandeara.voting.services;

import br.com.yandeara.voting.application.exception.InvalidTimeFormatException;
import br.com.yandeara.voting.application.service.TimeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TimeServiceTest {

    @Autowired
    private TimeServiceImpl timeServiceImpl;

    @Test
    public void addTime_validInputSecond_shouldBeSuccessful() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeAdded = timeServiceImpl.addTime(now, "1s");

        assertEquals(now.plusSeconds(1), timeAdded);
    }

    @Test
    public void addTime_validInputMinute_shouldBeSuccessful() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeAdded = timeServiceImpl.addTime(now, "1m");

        assertEquals(now.plusMinutes(1), timeAdded);
    }

    @Test
    public void addTime_validInputHour_shouldBeSuccessful() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeAdded = timeServiceImpl.addTime(now, "1H");

        assertEquals(now.plusHours(1), timeAdded);
    }

    @Test
    public void addTime_validInputDay_shouldBeSuccessful() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeAdded = timeServiceImpl.addTime(now, "1D");

        assertEquals(now.plusDays(1), timeAdded);
    }

    @Test
    public void addTime_validInputWeek_shouldBeSuccessful() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeAdded = timeServiceImpl.addTime(now, "1W");

        assertEquals(now.plusWeeks(1), timeAdded);
    }

    @Test
    public void addTime_validInputMonth_shouldBeSuccessful() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeAdded = timeServiceImpl.addTime(now, "1M");

        assertEquals(now.plusMonths(1), timeAdded);
    }

    @Test
    public void addTime_validInputYear_shouldBeSuccessful() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeAdded = timeServiceImpl.addTime(now, "1y");

        assertEquals(now.plusYears(1), timeAdded);
    }

    @Test
    public void addTime_invalidInput_shouldThrowInvalidTimeFormatException() {
        assertThrows(InvalidTimeFormatException.class, () -> {
            timeServiceImpl.addTime(LocalDateTime.now(), "1x");
        });
    }

}
