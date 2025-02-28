package br.com.yandeara.voting.application.exception;

public class AssociateAlreadyVotedException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "This associate already voted in this motion.";

    public AssociateAlreadyVotedException() {
        super(DEFAULT_MESSAGE);
    }

    public AssociateAlreadyVotedException(String message) {
        super(message != null ? message : DEFAULT_MESSAGE);
    }

}
