package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.elis.softwareprenotazioneeventi.model.Posto;
import org.elis.softwareprenotazioneeventi.model.Ripetizione;

@Data
public class BigliettoRequestDTO {

    @NotBlank(message = "il prezzo non può essere null")
    private double prezzo;
    @NotNull
    private long idPosto;
    @NotNull
    private long idRipetizione;

}
