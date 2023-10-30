package org.elis.softwareprenotazioneeventi.DTO.request;

import lombok.Data;

@Data
public class FiltroRecensione {

    private String descrizione;
    private String votazione;
    private String nomeUser;
    private String nomeEvento;

}
