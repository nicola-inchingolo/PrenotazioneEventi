package org.elis.softwareprenotazioneeventi.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAllRecensioniResponseDTO {

    private long id;
    private String descrizione;
    private int votazione;
    private String userNome;
    private String eventoNome;

}
