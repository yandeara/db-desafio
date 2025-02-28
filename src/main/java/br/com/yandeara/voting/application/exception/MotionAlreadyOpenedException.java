package br.com.yandeara.voting.application.exception;

public class MotionAlreadyOpenedException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Motion is already opened, can't open a second session.";

    public MotionAlreadyOpenedException() {
        super(DEFAULT_MESSAGE);
    }

    public MotionAlreadyOpenedException(String message) {
        super(message != null ? message : DEFAULT_MESSAGE);
    }

}
