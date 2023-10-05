package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModificaDescrizioneRecensioneDTO {

    @NotNull
    private long idRecensione;

    @NotNull
    private String newDescrizione;
}
