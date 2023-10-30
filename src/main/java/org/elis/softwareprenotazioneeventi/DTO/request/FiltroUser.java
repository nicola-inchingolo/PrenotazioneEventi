package org.elis.softwareprenotazioneeventi.DTO.request;

import lombok.Data;

import java.util.Map;

@Data
public class FiltroUser {

    //chiave valore --> nome dle campo e valore da crecare

   // private Map<String, String> filtri;
    private String nome;
    private String cognome;
    private String email;
    private String codiceFiscale;

}
