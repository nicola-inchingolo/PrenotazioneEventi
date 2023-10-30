package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaRecensioneRequestDTO;
import org.elis.softwareprenotazioneeventi.model.Recensione;
import org.springframework.stereotype.Component;

@Component
public class RecensioneMapper {

    public Recensione toRecensioneRequestDTO(CreaRecensioneRequestDTO requestDTO)
    {
        Recensione recensione = new Recensione();
        recensione.setDescrizione(requestDTO.getDescrizione());
        recensione.setVotazione(requestDTO.getVotazione());

        return recensione;
    }


}
