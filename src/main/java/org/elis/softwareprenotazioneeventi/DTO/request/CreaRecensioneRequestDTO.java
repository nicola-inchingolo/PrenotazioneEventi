package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreaRecensioneRequestDTO {

    private String descrizione;

    @NotNull
    private int votazione;


    @NotNull
    private long idEvento;



}
