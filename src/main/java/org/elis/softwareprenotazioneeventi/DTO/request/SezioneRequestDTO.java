package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SezioneRequestDTO {

    @NotBlank(message = "nome non deve essere null")
    private String nome;


    @NotNull
    private long idLuogo;
}
