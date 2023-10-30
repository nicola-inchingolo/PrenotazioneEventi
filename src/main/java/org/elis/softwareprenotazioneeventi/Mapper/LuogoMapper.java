package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaLuogoRequestDTO;
import org.elis.softwareprenotazioneeventi.model.Luogo;
import org.springframework.stereotype.Component;

@Component
public class LuogoMapper {

    public Luogo toLuogoRequestDTO(CreaLuogoRequestDTO requestDTO)
    {
        Luogo luogo = new Luogo();
        luogo.setNome(requestDTO.getNome());
        luogo.setProvincia(requestDTO.getProvincia());
        luogo.setCap(requestDTO.getCap());
        luogo.setVia(requestDTO.getVia());
        luogo.setCittà(requestDTO.getCittà());

        return luogo;
    }


}
