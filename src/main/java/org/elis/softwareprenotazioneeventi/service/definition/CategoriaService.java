package org.elis.softwareprenotazioneeventi.service.definition;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaCategoriaRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllCategoriaResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Categoria;

import java.util.List;

public interface CategoriaService {

    public boolean creaCategoria(String nome);

    public List<GetAllCategoriaResponseDTO> getAllCategorie();

}
