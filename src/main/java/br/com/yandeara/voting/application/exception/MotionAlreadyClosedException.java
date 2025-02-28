package br.com.yandeara.voting.application.exception;

public class MotionAlreadyClosedException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Motion is already closed.";

    public MotionAlreadyClosedException() {
        super(DEFAULT_MESSAGE);
    }

    public MotionAlreadyClosedException(String message) {
        super(message != null ? message : DEFAULT_MESSAGE);
    }

}
