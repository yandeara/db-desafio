package br.com.yandeara.voting.controllers;


import br.com.yandeara.voting.application.exception.*;
import br.com.yandeara.voting.application.service.MotionService;
import br.com.yandeara.voting.application.service.VoteService;
import br.com.yandeara.voting.application.service.VoteSessionService;
import br.com.yandeara.voting.web.controller.MotionControllerV1;
import br.com.yandeara.voting.web.controller.VoteControllerV1;
import br.com.yandeara.voting.web.request.MotionRequest;
import br.com.yandeara.voting.web.request.VoteSessionRequest;
import br.com.yandeara.voting.web.response.MotionResponse;
import br.com.yandeara.voting.web.response.VoteResponse;
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

import java.time.ZonedDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VoteControllerV1.class)
public class VoteControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VoteService voteService;

    @Test
    public void vote_fullBody_returnOk() throws Exception {
        VoteResponse voteResponse = new VoteResponse(1L, 1L, 1L, true, ZonedDateTime.now());

        when(voteService.vote(any())).thenReturn(voteResponse);

        mockMvc.perform(post("/api/v1/vote").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motionId\":1,\"associateId\":1,\"associateVote\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.motionId").exists())
                .andExpect(jsonPath("$.associateId").exists())
                .andExpect(jsonPath("$.associateVote").exists())
                .andExpect(jsonPath("$.voteTime").exists());

    }

    @Test
    public void vote_motionNotFound_return400() throws Exception {
        when(voteService.vote(any())).thenThrow(new EntityNotFoundException());

        mockMvc.perform(post("/api/v1/vote").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motionId\":1,\"associateId\":1,\"associateVote\":true}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void vote_motionNotOpenedException_return400() throws Exception {
        when(voteService.vote(any())).thenThrow(new MotionNotOpenedException());

        mockMvc.perform(post("/api/v1/vote").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motionId\":1,\"associateId\":1,\"associateVote\":true}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void vote_motionAlreadyClosedException_return400() throws Exception {
        when(voteService.vote(any())).thenThrow(new MotionAlreadyClosedException());

        mockMvc.perform(post("/api/v1/vote").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motionId\":1,\"associateId\":1,\"associateVote\":true}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void vote_associateAlreadyVotedException_return400() throws Exception {
        when(voteService.vote(any())).thenThrow(new AssociateAlreadyVotedException());

        mockMvc.perform(post("/api/v1/vote").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motionId\":1,\"associateId\":1,\"associateVote\":true}"))
                .andExpect(status().isBadRequest());
    }

}
