package org.elis.softwareprenotazioneeventi.DTO.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.elis.softwareprenotazioneeventi.model.Luogo;
import org.elis.softwareprenotazioneeventi.model.Posto;

import java.util.List;

@Data
@AllArgsConstructor
public class GetAllSezioniResponseDTO {

    private long id;
    private String nome;
    private String NomeLuogo;
    private List<String> nomeposti;

}
