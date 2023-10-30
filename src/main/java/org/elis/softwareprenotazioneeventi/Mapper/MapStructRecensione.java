package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaRecensioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllRecensioniResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Recensione;
import org.mapstruct.Mapper;

@Mapper
public interface MapStructRecensione {

    GetAllRecensioniResponseDTO toGetAllRecensioniResponseDTO(Recensione recensione);
    Recensione fromCreaRecensioneRequestDTO(CreaRecensioneRequestDTO requestDTO);




}
