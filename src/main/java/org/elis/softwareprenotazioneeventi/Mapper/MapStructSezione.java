package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.SezioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllSezioniResponseDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllUsersResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Sezione;
import org.elis.softwareprenotazioneeventi.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface MapStructSezione {

    GetAllSezioniResponseDTO toGetAllSezioniResponspeDTO(Sezione sezione);
    Sezione fromSezioneRequestDTO(SezioneRequestDTO request);

}
