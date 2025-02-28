package br.com.yandeara.voting.application.service;

import br.com.caelum.stella.validation.InvalidStateException;
import br.com.yandeara.voting.web.response.CpfValidatorResponse;

public interface CpfValidatorService {

    CpfValidatorResponse validate(String cpf) throws InvalidStateException;

}
