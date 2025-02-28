package br.com.yandeara.voting.controllers;


import br.com.yandeara.voting.application.exception.InvalidTimeFormatException;
import br.com.yandeara.voting.application.service.VoteSessionService;
import br.com.yandeara.voting.web.controller.VoteSessionControllerV1;
import br.com.yandeara.voting.web.request.VoteSessionRequest;
import br.com.yandeara.voting.web.response.VoteSessionResponse;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VoteSessionControllerV1.class)
public class VoteSessionControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VoteSessionService voteSessionService;

    @Test
    public void create_fullBody_returnOk() throws Exception {
        VoteSessionResponse voteSessionResponse = new VoteSessionResponse(1L, 1L, LocalDateTime.now(), LocalDateTime.now());

        when(voteSessionService.create(any(VoteSessionRequest.class))).thenReturn(voteSessionResponse);

        mockMvc.perform(post("/api/v1/vote-session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motionId\":1,\"sessionDuration\":\"1m\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.motionId").exists())
                .andExpect(jsonPath("$.openingTime").exists())
                .andExpect(jsonPath("$.closingTime").exists());
    }

    @Test
    public void create_nullBody_return415() throws Exception {
        mockMvc.perform(post("/api/v1/vote-session"))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void create_emptyBody_return404() throws Exception {
        when(voteSessionService.create(any())).thenThrow(new EntityNotFoundException("Motion not found"));

        mockMvc.perform(post("/api/v1/vote-session")
                    .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void create_internalError_return500() throws Exception {
        when(voteSessionService.create(any())).thenThrow(new RuntimeException("Server Error"));

        mockMvc.perform(post("/api/v1/vote-session")
                        .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void create_invalidTimeFormatInput_return400() throws Exception {
        when(voteSessionService.create(any())).thenThrow(new InvalidTimeFormatException());

        mockMvc.perform(post("/api/v1/vote-session").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motionId\":1,\"sessionDuration\":\"1x\"}"))
                .andExpect(status().isBadRequest());
    }

}
