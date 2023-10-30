package org.elis.softwareprenotazioneeventi.PostoTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.elis.softwareprenotazioneeventi.DTO.request.CreapostoDTO;
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

@SpringBootTest
@ContextConfiguration(classes = SoftwarePrenotazioneEventiApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc(print = MockMvcPrint.LOG_DEBUG, printOnlyOnFailure = false)
public class PostoTestsSucceded {

    @Autowired
    private MockMvc mock;

    @Order(2)
    @Test
    @WithUserDetails("daniele.cozzi@gmail.com")
    public void getAllPostiSuccess() throws Exception{

        mock.perform(MockMvcRequestBuilders.get("/venditore/getAllPosti"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }

    @Order(1)
    @Test
    @WithUserDetails("daniele.cozzi@gmail.com")
    public void creaPosto() throws Exception{

        CreapostoDTO request = new CreapostoDTO();

        request.setNome("12");
        request.setIdSezione(1);

        String json = new ObjectMapper().writeValueAsString(request);

        mock.perform(MockMvcRequestBuilders.post("/venditore/creaPosto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().is(200));


    }


}
