package org.elis.softwareprenotazioneeventi.SezioneTests;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elis.softwareprenotazioneeventi.DTO.request.SezioneRequestDTO;
import org.elis.softwareprenotazioneeventi.SoftwarePrenotazioneEventiApplication;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ContextConfiguration(classes =  SoftwarePrenotazioneEventiApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc(print = MockMvcPrint.LOG_DEBUG, printOnlyOnFailure = false)
public class SezioneTestsSucceded {

    @Autowired
    private MockMvc mock;


    @Order(2)
    @Test
    @WithUserDetails("daniele.cozzi@gmail.com")
    public void getAllSezioniSuccess() throws Exception{

        mock.perform(MockMvcRequestBuilders.get("/venditore/getAllSezioni"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }

    @Order(1)
    @Test
    @WithUserDetails("nico.inchingolo03@gmail.com")
    public void creaSezione() throws Exception {
        SezioneRequestDTO requestDTO= new SezioneRequestDTO();
        requestDTO.setNome("B");
        requestDTO.setIdLuogo(1);

        String json = new ObjectMapper().writeValueAsString(requestDTO);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/admin/creaSezione")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mock.perform(builder)
                .andExpect(MockMvcResultMatchers.status().is(200));


    }

}
