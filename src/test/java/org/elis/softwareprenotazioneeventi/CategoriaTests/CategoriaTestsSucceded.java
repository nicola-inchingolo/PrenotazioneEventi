package org.elis.softwareprenotazioneeventi.CategoriaTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.elis.softwareprenotazioneeventi.DTO.request.CreaCategoriaRequestDTO;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ContextConfiguration(classes = SoftwarePrenotazioneEventiApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc(print = MockMvcPrint.LOG_DEBUG, printOnlyOnFailure = false)
public class CategoriaTestsSucceded {

    @Autowired
    private MockMvc mock;

    @Order(2)
    @Test
    public void getAllCategorieSuccess() throws Exception{

        mock.perform(MockMvcRequestBuilders.get("/all/getAllCategorie"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }

    @Order(1)
    @Test
    @WithUserDetails("matteo.convertino@gmail.com")
    public void creaCategoriaSuccess() throws Exception {

        mock.perform(MockMvcRequestBuilders.post("/admin/creaCategoria?nome=Commedia"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }

}
