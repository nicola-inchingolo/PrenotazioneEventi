package org.elis.softwareprenotazioneeventi.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.elis.softwareprenotazioneeventi.model.Recensione;

@Data
@AllArgsConstructor
public class GetAllRecensioniResponseDTO {

    private long id;
    private String descrizione;
    private int votazione;
    private String userNome;
    private String eventoNome;

    public GetAllRecensioniResponseDTO(Recensione r)
    {
        id = r.getId();
        descrizione = r.getDescrizione();
        votazione = r.getVotazione();
        userNome = r.getUser().getNome();
        eventoNome = r.getEvento().getNome();



    }




}
