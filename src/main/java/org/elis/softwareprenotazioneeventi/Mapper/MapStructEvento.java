package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaEventoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllEventsResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MapStructEvento {


    @Mapping(target = "categorie",ignore = true)
    @Mapping(target = "repliche", ignore = true)
    GetAllEventsResponseDTO toGetAllEventsResponseDTO(Evento evento);
    Evento fromCreaEventoRequestDTO(CreaEventoRequestDTO creaEventoRequestDTO);

}
