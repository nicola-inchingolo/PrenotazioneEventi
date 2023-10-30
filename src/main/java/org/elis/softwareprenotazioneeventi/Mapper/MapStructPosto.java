package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.CreapostoDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllPostiResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Posto;
import org.mapstruct.Mapper;

@Mapper
public interface MapStructPosto {

    GetAllPostiResponseDTO toGetAllPostiResponseDTO(Posto posto);
    Posto fromCreaPostoDTO(CreapostoDTO request);

}
