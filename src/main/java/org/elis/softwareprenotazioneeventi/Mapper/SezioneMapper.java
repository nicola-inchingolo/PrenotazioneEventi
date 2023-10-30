package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.SezioneRequestDTO;
import org.elis.softwareprenotazioneeventi.model.Sezione;
import org.springframework.stereotype.Component;

@Component
public class SezioneMapper {

    public Sezione toSezioneRequestDTO(SezioneRequestDTO requestDTO)
    {
        Sezione sezione = new Sezione();
        sezione.setNome(requestDTO.getNome());
        return sezione;


    }

}
