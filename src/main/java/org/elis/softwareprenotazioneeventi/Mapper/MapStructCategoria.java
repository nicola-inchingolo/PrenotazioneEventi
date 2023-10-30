package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaCategoriaRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllCategoriaResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Categoria;
import org.mapstruct.Mapper;

@Mapper
public interface MapStructCategoria {

    GetAllCategoriaResponseDTO toGetAllCategoriaResponseDTO(Categoria categoria);
    Categoria fromCreaCategoriaRequestDTO(CreaCategoriaRequestDTO requestDTO);

}
