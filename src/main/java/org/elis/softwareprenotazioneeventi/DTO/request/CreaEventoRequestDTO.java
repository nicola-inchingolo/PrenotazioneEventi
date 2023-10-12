package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.elis.softwareprenotazioneeventi.model.Categoria;
import org.elis.softwareprenotazioneeventi.model.User;

import java.util.List;

@Data
public class CreaEventoRequestDTO {

    @NotBlank(message = "il nome non può essere nullo")
    private String nome;

    private String descrizione;

    @Nullable
    private List<CreaCategoriaRequestDTO> categorie;



}
