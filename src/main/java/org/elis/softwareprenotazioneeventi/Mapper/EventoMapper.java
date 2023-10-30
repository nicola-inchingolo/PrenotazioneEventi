package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaEventoRequestDTO;
import org.elis.softwareprenotazioneeventi.model.Evento;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {

    public Evento toEventoRequestDTO(CreaEventoRequestDTO requestDTO)
    {
        Evento evento = new Evento();
        evento.setNome(requestDTO.getNome());
        evento.setDescrizione(requestDTO.getDescrizione());

        return  evento;
    }

}
