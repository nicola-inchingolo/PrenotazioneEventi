package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModificaVotazioneRequestDTO {

    @NotNull
    private long idRecensione;

    @NotNull
    private int newVotazione;


}
