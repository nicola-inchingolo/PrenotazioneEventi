package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.elis.softwareprenotazioneeventi.model.Evento;

import java.util.List;

@Data
public class CreaCategoriaRequestDTO {

    @NotBlank(message = "il nome non può essere nullo")
    private String nome;

}
