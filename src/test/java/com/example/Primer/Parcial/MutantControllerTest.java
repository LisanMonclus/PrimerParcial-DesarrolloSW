package com.example.Primer.Parcial;

import com.example.Primer.Parcial.controllers.MutantController;
import com.example.Primer.Parcial.services.MutantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(MutantController.class)
public class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MutantService mutantService;

    @Test
    public void testIsMutant() throws Exception {
        // Configura el comportamiento simulado del servicio
        when(mutantService.isMutant(any(String[].class))).thenReturn(true);

        mockMvc.perform(post("/api/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Mutant detected"));
    }
}
