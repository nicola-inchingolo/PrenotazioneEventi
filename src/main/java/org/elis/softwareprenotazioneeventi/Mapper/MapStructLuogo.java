package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaLuogoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllLuoghiResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Luogo;
import org.mapstruct.Mapper;

@Mapper
public interface MapStructLuogo {

    GetAllLuoghiResponseDTO toGetAllLuoghiResponseDTO(Luogo luogo);
    Luogo fromCreaLuogoRequestDTO(CreaLuogoRequestDTO requestDTO);

}
