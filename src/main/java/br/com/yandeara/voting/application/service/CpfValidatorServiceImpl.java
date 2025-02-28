package br.com.yandeara.voting.application.service;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.yandeara.voting.web.response.CpfValidatorResponse;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CpfValidatorServiceImpl implements CpfValidatorService {

    private final String ABLE_TO_VOTE = "ABLE_TO_VOTE";
    private final String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";

    @Override
    public CpfValidatorResponse validate(String cpf) throws InvalidStateException {
        CPFValidator validator = new CPFValidator();
        validator.assertValid(cpf);

        CpfValidatorResponse response = new CpfValidatorResponse();

        response.setCpf(cpf);

        if (new Random().nextBoolean()) {
            response.setVoteStatus(ABLE_TO_VOTE);
        } else {
            response.setVoteStatus(UNABLE_TO_VOTE);
        }

        return response;
    }

}
