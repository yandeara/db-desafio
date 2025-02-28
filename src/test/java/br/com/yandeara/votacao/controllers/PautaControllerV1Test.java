package br.com.yandeara.votacao.controllers;


import br.com.yandeara.votacao.application.service.PautaService;
import br.com.yandeara.votacao.web.controller.PautaControllerV1;
import br.com.yandeara.votacao.web.request.PautaRequest;
import br.com.yandeara.votacao.web.response.PautaResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PautaControllerV1.class)
public class PautaControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PautaService pautaService;

    @Test
    public void assertPostPautaOk() throws Exception {
        PautaResponse pautaResponse = new PautaResponse(1L, "Descricao Teste");

        when(pautaService.create(any(PautaRequest.class))).thenReturn(pautaResponse);

        mockMvc.perform(post("/api/v1/pauta").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.descricao").value("Descricao Teste"));
    }

    @Test
    public void assertPostPautaServerError() throws Exception {
        when(pautaService.create(any(PautaRequest.class))).thenThrow(new RuntimeException("Server Error"));

        mockMvc.perform(post("/api/v1/pauta").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void assertPostPautaOkNullBody() throws Exception {
        PautaResponse pautaResponse = new PautaResponse(1L, null);

        when(pautaService.create(any())).thenReturn(pautaResponse);

        mockMvc.perform(post("/api/v1/pauta"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

    }

}
