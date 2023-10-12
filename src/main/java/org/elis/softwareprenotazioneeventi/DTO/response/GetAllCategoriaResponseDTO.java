package org.elis.softwareprenotazioneeventi.DTO.response;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.elis.softwareprenotazioneeventi.model.Evento;

import java.util.List;

@Data
@AllArgsConstructor
public class GetAllCategoriaResponseDTO {

    private long id;
    private String nome;

   //private List<GetAllEventsResponseDTO> eventi;

}
