package br.com.yandeara.voting.controllers;


import br.com.caelum.stella.SimpleValidationMessage;
import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.yandeara.voting.application.exception.AssociateAlreadyVotedException;
import br.com.yandeara.voting.application.exception.MotionAlreadyClosedException;
import br.com.yandeara.voting.application.exception.MotionNotOpenedException;
import br.com.yandeara.voting.application.service.CpfValidatorService;
import br.com.yandeara.voting.application.service.VoteService;
import br.com.yandeara.voting.web.controller.CpfValidatorControllerV1;
import br.com.yandeara.voting.web.controller.VoteControllerV1;
import br.com.yandeara.voting.web.response.CpfValidatorResponse;
import br.com.yandeara.voting.web.response.VoteCountResponse;
import br.com.yandeara.voting.web.response.VoteResponse;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CpfValidatorControllerV1.class)
public class CpfValidatorControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CpfValidatorService cpfValidatorService;

    @Test
    public void validate_invalidCpf_return404() throws Exception {
        when(cpfValidatorService.validate(any())).thenThrow(new InvalidStateException(new SimpleValidationMessage("")));

        mockMvc.perform(get("/api/v1/cpf")
                        .param("cpf", "00000000000"))
        .andExpect(status().isNotFound());
    }

    @Test
    public void validate_notFoundParamCpf_return400() throws Exception {
        mockMvc.perform(get("/api/v1/cpf"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void validate_validCpf_return200() throws Exception {
        CpfValidatorResponse response = new CpfValidatorResponse("00000000000", "ABLE_TO_VOTE");
        when(cpfValidatorService.validate(any())).thenReturn(response);

        mockMvc.perform(get("/api/v1/cpf")
                .param("cpf", "00000000000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").exists())
                .andExpect(jsonPath("$.voteStatus").exists());
    }

}
