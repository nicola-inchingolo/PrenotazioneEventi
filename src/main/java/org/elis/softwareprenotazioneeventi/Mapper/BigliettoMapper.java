package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.BigliettoRequestDTO;
import org.elis.softwareprenotazioneeventi.model.Biglietto;
import org.springframework.stereotype.Component;

@Component
public class BigliettoMapper {

    public Biglietto toBigliettoRequestDTO(BigliettoRequestDTO requestDTO)
    {
        Biglietto biglietto = new Biglietto();
        biglietto.setPrezzo(requestDTO.getPrezzo());

        return biglietto;
    }


}
