package org.elis.softwareprenotazioneeventi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.With;
import org.elis.softwareprenotazioneeventi.DTO.request.LoginRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.RegistrazioneRequestDTO;
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

import java.time.LocalDate;

@SpringBootTest
@ContextConfiguration(classes = SoftwarePrenotazioneEventiApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc(print = MockMvcPrint.LOG_DEBUG, printOnlyOnFailure = false)
class SoftwarePrenotazioneEventiApplicationTests {

    @Autowired
    private MockMvc mock;

    @Order(1)
    @Test
    public void testRegistrazione() throws Exception{

        RegistrazioneRequestDTO request = new RegistrazioneRequestDTO();
        request.setNome("Luca");
        request.setCognome("Abete");
        request.setPassword("IlBosco!1");
        request.setEmail("luca.abete@gmail.com");
        request.setCodiceFiscale("NCHNCL03M05A662Q");
        request.setDataDiNascita(LocalDate.of(2003, 6,14));
        request.setPasswordRipetuta("IlBosco!1");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String json = objectMapper.writeValueAsString(request);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/all/registraCliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mock.perform(builder).andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }

    @Order(2)
    @Test
    public void testLoginSuccess() throws Exception {

        //oggetto request
        LoginRequestDTO l = new LoginRequestDTO();
        l.setEmail("nico.inchingolo03@gmail.com");
        l.setPassword("Password!1");

        //lo converto in JSON
        String json = new ObjectMapper().writeValueAsString(l);

        //creo la mia request
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/all/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        //la passo al mock
        mock.perform(builder)
                //controllo il risultato
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email")
                        .exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email")
                        .value("nico.inchingolo03@gmail.com"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getHeader("Authorization");
    }

    @Order(3)
    @Test
    public void testLoginPasswordErrata() throws Exception{

        LoginRequestDTO l=new LoginRequestDTO();
        l.setEmail("nico.inchingolo03@gmail.com");
        l.setPassword("Password!2");
        //lo converto in json
        String json=new ObjectMapper().writeValueAsString(l);
        //creo la mia request
        MockHttpServletRequestBuilder builder=MockMvcRequestBuilders.post("/all/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        //la passo al mock
        mock.perform(builder)
                //controllo il risultato
                .andExpect(MockMvcResultMatchers.status().is(400));

    }

    @Test
    @WithUserDetails("nico.inchingolo03@gmail.com")
    public void provaSecurity() throws Exception{

        mock.perform(MockMvcRequestBuilders.get("/admin/prova"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
    }

    @Test
    public void provaSecurityConToken() throws Exception
    {
        LoginRequestDTO request = new LoginRequestDTO();
        request.setEmail("nico.inchingolo03@gmail.com");
        request.setPassword("Password!1");

        String json = new ObjectMapper().writeValueAsString(request);

        String tokenJwt = mock.perform(MockMvcRequestBuilders.post("/all/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andReturn().getResponse().getHeader("Authorization");

        System.out.println(tokenJwt);
        mock.perform(MockMvcRequestBuilders.get("/admin/prova")
                .header("Authorization", "Bearer " + tokenJwt)
        )
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
    }







}
