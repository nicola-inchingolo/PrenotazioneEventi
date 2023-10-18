package org.elis.softwareprenotazioneeventi.CategoriaTests;


import org.elis.softwareprenotazioneeventi.SoftwarePrenotazioneEventiApplication;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
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
public class CategoriaTestsFailed {

    @Autowired
    private MockMvc mock;

    @Test
    @WithUserDetails("matteo.convertino@gmail.com")
    public void creaCategoriaFail() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/admin/creaCategoria"))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithUserDetails("daniele.cozzi@gmail.com")
    public void creaCategoriaAutorizzazioneFail() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/admin/creaCategoria"))
                .andExpect(MockMvcResultMatchers.status().is(403))
                .andDo(MockMvcResultHandlers.print());
    }








}
