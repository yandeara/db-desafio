package br.com.yandeara.voting.application.exception;

public class MotionNotOpenedException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Motion is not opened.";

    public MotionNotOpenedException() {
        super(DEFAULT_MESSAGE);
    }

    public MotionNotOpenedException(String message) {
        super(message != null ? message : DEFAULT_MESSAGE);
    }

}
