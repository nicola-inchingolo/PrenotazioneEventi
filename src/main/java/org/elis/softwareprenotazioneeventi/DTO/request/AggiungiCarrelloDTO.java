package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AggiungiCarrelloDTO {

    @NotNull
    private long idUser;

    @NotNull
    private long idBiglietto;



}
