package org.elis.softwareprenotazioneeventi.LuogoTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.elis.softwareprenotazioneeventi.DTO.request.CreaLuogoRequestDTO;
import org.elis.softwareprenotazioneeventi.SoftwarePrenotazioneEventiApplication;
import org.junit.jupiter.api.MethodOrderer;
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
public class LuogoTestsFailed {

    @Autowired
    private MockMvc mock;

    @Test
    @WithUserDetails("nico.inchingolo03@gmail.com")
    public void creaLuogoFailed() throws Exception{

        CreaLuogoRequestDTO requestDTO = new CreaLuogoRequestDTO();

        String json = new ObjectMapper().writeValueAsString(requestDTO);
        mock.perform(MockMvcRequestBuilders.post("/admin/creaLuogo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithUserDetails("daniele.cozzi@gmail.com")
    public void creaLuogoFailedAuthorization() throws Exception{

        CreaLuogoRequestDTO requestDTO = new CreaLuogoRequestDTO();

        String json = new ObjectMapper().writeValueAsString(requestDTO);
        mock.perform(MockMvcRequestBuilders.post("/admin/creaLuogo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is(403))
                .andDo(MockMvcResultHandlers.print());
    }


}
