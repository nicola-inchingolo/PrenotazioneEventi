package org.elis.softwareprenotazioneeventi.EventoTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.elis.softwareprenotazioneeventi.DTO.request.CreaEventoRequestDTO;
import org.elis.softwareprenotazioneeventi.SoftwarePrenotazioneEventiApplication;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = SoftwarePrenotazioneEventiApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc(print = MockMvcPrint.LOG_DEBUG, printOnlyOnFailure = false)
public class EventoTestsSucceded {

    @Autowired
    private MockMvc mock;

    @Order(1)
    @Test
    @WithUserDetails("daniele.cozzi@gmail.com")
    public void creaEvento() throws Exception{

        CreaEventoRequestDTO request = new CreaEventoRequestDTO();
        request.setNome("Utopia");
        request.setDescrizione("T-time?");


        String json = new ObjectMapper().writeValueAsString(request);

        mock.perform(MockMvcRequestBuilders.post("/venditore/creaEvento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }

    @Order(2)
    @Test
    public void getAllEventsSuccess() throws Exception{

        mock.perform(MockMvcRequestBuilders.get("/all/getAllEvents"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllEventsByDataInizioSuccess() throws Exception{

        mock.perform(MockMvcRequestBuilders.get("/all/getAllEventsByDataInizio?data=2024-01-24"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllEventsByLuogoSuccess() throws Exception{

        mock.perform(MockMvcRequestBuilders.get("/all/getAllEventsByLuogo?nomeLuogo=Colosseo"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithUserDetails("daniele.cozzi@gmail.com")
    public void deleteEventoSuccess() throws Exception{

        mock.perform(MockMvcRequestBuilders.delete("/venditore/deleteEvento?idEvento=1"))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }





}



