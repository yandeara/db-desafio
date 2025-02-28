package br.com.yandeara.votacao.controllers;


import br.com.yandeara.votacao.application.service.SessaoService;
import br.com.yandeara.votacao.web.controller.SessaoControllerV1;
import br.com.yandeara.votacao.web.request.PautaRequest;
import br.com.yandeara.votacao.web.request.SessaoRequest;
import br.com.yandeara.votacao.web.response.PautaResponse;
import br.com.yandeara.votacao.web.response.SessaoResponse;
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
@WebMvcTest(SessaoControllerV1.class)
public class SessaoControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SessaoService sessaoService;

    @Test
    public void assertPostSessaoOk() throws Exception {
        SessaoResponse sessaoResponse = new SessaoResponse(1L, 1L, LocalDateTime.now(), LocalDateTime.now());

        when(sessaoService.create(any(SessaoRequest.class))).thenReturn(sessaoResponse);

        mockMvc.perform(post("/api/v1/sessao").contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                 "  \"pautaId\": 2,\n" +
                                 "  \"sessaoDuration\": \"5m\"\n" +
                                 "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void assertPostSessaoServerError() throws Exception {
        when(sessaoService.create(any(SessaoRequest.class))).thenThrow(new RuntimeException("Server Error"));

        mockMvc.perform(post("/api/v1/sessao").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void assertPostSessaoErrorNullBody() throws Exception {
        SessaoResponse sessaoResponse = new SessaoResponse(1L, 1L, LocalDateTime.now(), LocalDateTime.now());

        when(sessaoService.create(any())).thenReturn(sessaoResponse);

        mockMvc.perform(post("/api/v1/sessao"))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void assertPostSessaoErrorPautaNotFound() throws Exception {
        when(sessaoService.create(any())).thenThrow(new EntityNotFoundException("Pauta not found"));

        mockMvc.perform(post("/api/v1/sessao").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void assertPostSessaoErrorTimeFormatError() throws Exception {
        when(sessaoService.create(any())).thenThrow(new IllegalArgumentException("Unidade de tempo inválida, Precisa ser:'s' - segundos, 'm' - minutos, 'H' - Horas, 'D' - dias, 'W' - semanas, 'M' - meses ou 'y' - anos."));

        mockMvc.perform(post("/api/v1/sessao").contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"pautaId\": 2,\n" +
                                "  \"sessaoDuration\": \"5x\"\n" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

}
