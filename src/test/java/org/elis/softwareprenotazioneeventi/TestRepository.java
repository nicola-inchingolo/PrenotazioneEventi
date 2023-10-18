package org.elis.softwareprenotazioneeventi;

import org.elis.softwareprenotazioneeventi.model.Role;
import org.elis.softwareprenotazioneeventi.model.User;
import org.elis.softwareprenotazioneeventi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestRepository {




    @Autowired
    private UserRepository userRepository;


    @Test
    public void testDimensioneElencoDati()
    {
        List<User> users = userRepository.findAllByRuolo(Role.SUPERADMIN);
        assertEquals(1, users.size());
    }

    @Test
    public void testLogin(){
        assertThat(userRepository.findUserByEmailAndPassword("nico.inchingolo03@gmail.com", "Password!1"))
                .get().extracting(User::getNome)
                .isEqualTo("Nicola");
    }


}
