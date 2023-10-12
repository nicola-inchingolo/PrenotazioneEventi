package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.elis.softwareprenotazioneeventi.model.User;


@Data
public class CreaLuogoRequestDTO {
    @NotBlank(message = "il nome del luogo non può eseere null")
    private String nome;
    @NotBlank(message = "il nome della via non può essere nullo")
    private String via;
    @NotBlank(message = "il nome della città non può essere nullo")
    private String città;

    private String provincia;

    private String cap;



}
