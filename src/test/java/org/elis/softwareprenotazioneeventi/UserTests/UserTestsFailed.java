package org.elis.softwareprenotazioneeventi.UserTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.elis.softwareprenotazioneeventi.DTO.request.LoginRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaPasswordRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.RegistrazioneRequestDTO;
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
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@ContextConfiguration(classes = SoftwarePrenotazioneEventiApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc(print = MockMvcPrint.LOG_DEBUG, printOnlyOnFailure = false)
public class UserTestsFailed {

    @Autowired
    private MockMvc mock;


    @Test
    public void testLoginPasswordErrata() throws Exception{

        LoginRequestDTO l=new LoginRequestDTO();
        l.setEmail("nico.inchingolo03@gmail.com");
        l.setPassword("Password!2");
        //lo converto in json
        String json=new ObjectMapper().writeValueAsString(l);
        //creo la mia request
        MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.post("/all/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        //la passo al mock
        mock.perform(builder)
                //controllo il risultato
                .andExpect(MockMvcResultMatchers.status().is(400));

    }

    @Test
    public void testLoginEmailErrata() throws Exception{

        LoginRequestDTO l = new LoginRequestDTO();
        l.setEmail("regezsbagliata");
        l.setPassword("Password!1");

        //lo converto in json
        String json=new ObjectMapper().writeValueAsString(l);
        //creo la mia request
        MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.post("/all/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        //la passo al mock
        mock.perform(builder)
                //controllo il risultato
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testLoginVuotaErrata() throws Exception{

        LoginRequestDTO l=new LoginRequestDTO();

        //lo converto in json
        String json=new ObjectMapper().writeValueAsString(l);
        //creo la mia request
        MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.post("/all/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        //la passo al mock
        mock.perform(builder)
                //controllo il risultato
                .andExpect(MockMvcResultMatchers.status().is(400))
                    .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testRegistrazioneCampiErrata() throws Exception{

        RegistrazioneRequestDTO request = new RegistrazioneRequestDTO();
        request.setNome("Marii");
        request.setCognome("lu");
        request.setCodiceFiscale("SBAGLIATO");
        request.setDataDiNascita(LocalDate.of(2023,10,29));
        request.setEmail("regexsbagliata");
        request.setPassword("nostromo");
        request.setPasswordRipetuta("leo");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String json = objectMapper.writeValueAsString(request);

        mock.perform(MockMvcRequestBuilders.post("/all/registraCliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().is(400))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithUserDetails("edoardo.loconte@gmail.com")
    public void testRegistrazioneAutorizzazioneErrata() throws Exception
    {
        RegistrazioneRequestDTO request = new RegistrazioneRequestDTO();
        request.setNome("Luca");
        request.setCognome("Addati");
        request.setPassword("IlBosco!2");
        request.setEmail("luca.addati@gmail.com");
        request.setCodiceFiscale("NCHNCL03M05A662Q");
        request.setDataDiNascita(LocalDate.of(2003, 8,25));
        request.setPasswordRipetuta("IlBosco!2");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String json = objectMapper.writeValueAsString(request);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/admin/registraVenditore")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mock.perform(builder).andExpect(MockMvcResultMatchers.status().is(403))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void modificaPasswordFail() throws Exception {

        ModificaPasswordRequestDTO requestDTO= new ModificaPasswordRequestDTO();
        requestDTO.setIdUser(3);
        requestDTO.setNewPassword("1");

        String json = new ObjectMapper().writeValueAsString(requestDTO);


        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.patch("/all/modificaPassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mock.perform(builder)
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithUserDetails("matteo.convertino@gmail.com")
    public void bloccaAccountFail() throws Exception {
        mock.perform(MockMvcRequestBuilders.put("/admin/bloccaAccount?idUser=6"))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithUserDetails("matteo.convertino@gmail.com")
    public void sbloccaAccountFail() throws Exception {
        mock.perform(MockMvcRequestBuilders.put("/admin/sbloccaAccount?idUser=1"))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andDo(MockMvcResultHandlers.print());
    }


}
