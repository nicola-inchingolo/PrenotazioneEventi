package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.elis.softwareprenotazioneeventi.model.Sezione;

@Data
public class CreapostoDTO {

    @NotBlank(message = "il nome del posto non può essere nullo")
    private String nome;

    @NotNull
    private long idSezione;


}
