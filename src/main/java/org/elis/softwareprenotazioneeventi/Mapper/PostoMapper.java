package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.CreapostoDTO;
import org.elis.softwareprenotazioneeventi.model.Posto;
import org.springframework.stereotype.Component;

@Component
public class PostoMapper {

    public Posto toPostoRequestDTO(CreapostoDTO request)
    {
        Posto posto = new Posto();
        posto.setNome(request.getNome());

        return posto;

    }


}
