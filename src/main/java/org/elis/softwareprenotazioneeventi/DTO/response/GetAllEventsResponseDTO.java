package org.elis.softwareprenotazioneeventi.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.elis.softwareprenotazioneeventi.model.Categoria;
import org.elis.softwareprenotazioneeventi.model.Ripetizione;

import java.util.List;
@AllArgsConstructor
@Data
public class GetAllEventsResponseDTO {

    private long idEvento;
    private String nome;
    private String descrizione;
    private List<Categoria> categorie;
    private List<Ripetizione> repliche;




}
