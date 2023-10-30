package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.BigliettoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllBigliettiResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Biglietto;
import org.mapstruct.Mapper;

@Mapper
public interface MapStructBiglietto {

    GetAllBigliettiResponseDTO toGetAllBigliettiResponseDTO(Biglietto biglietto);
    Biglietto fromCreaBigliettorequestDTO(BigliettoRequestDTO requestDTO);

}
