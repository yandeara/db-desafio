package br.com.yandeara.voting.controllers;


import br.com.yandeara.voting.application.exception.InvalidTimeFormatException;
import br.com.yandeara.voting.application.exception.MotionAlreadyOpenedException;
import br.com.yandeara.voting.application.service.MotionService;
import br.com.yandeara.voting.application.service.VoteSessionService;
import br.com.yandeara.voting.domain.model.Motion;
import br.com.yandeara.voting.web.controller.MotionControllerV1;
import br.com.yandeara.voting.web.request.MotionRequest;
import br.com.yandeara.voting.web.request.VoteSessionRequest;
import br.com.yandeara.voting.web.response.MotionResponse;
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
import java.time.ZonedDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MotionControllerV1.class)
public class MotionControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MotionService motionService;

    @MockitoBean
    private VoteSessionService voteSessionService;

    @Test
    public void create_emptyBody_returnOk() throws Exception {
        MotionResponse motionResponse = new MotionResponse(1L, null);

        when(motionService.create(any(MotionRequest.class))).thenReturn(motionResponse);

        mockMvc.perform(post("/api/v1/motion").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.description").doesNotExist());
    }

    @Test
    public void create_nullBody_returnOk() throws Exception {
        MotionResponse motionResponse = new MotionResponse(1L, null);

        when(motionService.create(any())).thenReturn(motionResponse);

        mockMvc.perform(post("/api/v1/motion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.description").doesNotExist());

    }

    @Test
    public void create_fullBody_returnOk() throws Exception {
        MotionResponse motionResponse = new MotionResponse(1L, "test");

        when(motionService.create(any())).thenReturn(motionResponse);

        mockMvc.perform(post("/api/v1/motion").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"test\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.description").exists());

    }

    @Test
    public void create_internalError_return500() throws Exception {
        when(motionService.create(any(MotionRequest.class))).thenThrow(new RuntimeException("Server Error"));

        mockMvc.perform(post("/api/v1/motion").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void openSession_fullBody_returnOk() throws Exception {
        VoteSessionResponse voteSessionResponse = new VoteSessionResponse(1L, "test", ZonedDateTime.now(), ZonedDateTime.now());

        when(voteSessionService.openSession(any(VoteSessionRequest.class))).thenReturn(voteSessionResponse);

        mockMvc.perform(post("/api/v1/motion/open-session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motionId\":1,\"sessionDuration\":\"1m\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.openingTime").exists())
                .andExpect(jsonPath("$.closingTime").exists());
    }

    @Test
    public void openSession_nullBody_return415() throws Exception {
        mockMvc.perform(post("/api/v1/motion/open-session"))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void openSession_emptyBody_return404() throws Exception {
        when(voteSessionService.openSession(any())).thenThrow(new EntityNotFoundException("Motion not found"));

        mockMvc.perform(post("/api/v1/motion/open-session")
                        .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void openSession_internalError_return500() throws Exception {
        when(voteSessionService.openSession(any())).thenThrow(new RuntimeException("Server Error"));

        mockMvc.perform(post("/api/v1/motion/open-session")
                        .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void openSession_invalidTimeFormatInput_return400() throws Exception {
        when(voteSessionService.openSession(any())).thenThrow(new InvalidTimeFormatException());

        mockMvc.perform(post("/api/v1/motion/open-session").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motionId\":1,\"sessionDuration\":\"1x\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void openSession_motionAlreadyOpened_return400() throws Exception {
        when(voteSessionService.openSession(any())).thenThrow(new MotionAlreadyOpenedException());

        mockMvc.perform(post("/api/v1/motion/open-session").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motionId\":1,\"sessionDuration\":\"1x\"}"))
                .andExpect(status().isBadRequest());
    }

}
