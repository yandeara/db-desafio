package br.com.yandeara.voting.application.exception;

public class InvalidTimeFormatException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Invalid or null time unit. It must be: 's' - seconds, 'm' - minutes, 'H' - hours, 'D' - days, 'W' - weeks, 'M' - months, or 'y' - years";

    public InvalidTimeFormatException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidTimeFormatException(String message) {
        super(message != null ? message : DEFAULT_MESSAGE);
    }

}
