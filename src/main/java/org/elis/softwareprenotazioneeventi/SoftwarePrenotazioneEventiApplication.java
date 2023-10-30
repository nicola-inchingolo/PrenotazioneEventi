package org.elis.softwareprenotazioneeventi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SoftwarePrenotazioneEventiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftwarePrenotazioneEventiApplication.class, args);
    }

}
