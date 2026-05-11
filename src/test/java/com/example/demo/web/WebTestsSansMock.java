package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.StatistiqueImpl;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.annotation.DirtiesContext;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class WebTestsSansMock {

    @Autowired
    StatistiqueImpl statistiqueImpl;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Voiture voiture;

    @BeforeEach
    public void init()
    {
        this.voiture = new Voiture("Audi", 20000);
    }

    @Test
    public void recupererStatistiquesSansMock() throws Exception
    {
        this.statistiqueImpl.ajouter(this.voiture);

        mockMvc.perform(get("/statistique")).andExpectAll(
	        status().isOk(),
	        jsonPath("$.nombreDeVoitures").value(1),
            jsonPath("$.prixMoyen").value(20000)
        );
    }

    @Test
    public void recupererStatistiquesLanceExceptionSansMock() throws Exception
    {
        mockMvc.perform(get("/statistique")).andExpect(
            status().isInternalServerError()
        );
    }

    @Test
    public void creerUneVoitureSansMock() throws Exception
    {
        mockMvc.perform(post("/voiture")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(this.voiture)))
               .andExpect(status().isOk());
    }
}
