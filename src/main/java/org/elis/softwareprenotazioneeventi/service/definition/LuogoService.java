package org.elis.softwareprenotazioneeventi.service.definition;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaLuogoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllLuoghiResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Categoria;
import org.elis.softwareprenotazioneeventi.model.Luogo;

import java.util.List;

public interface LuogoService {

    public boolean creazioneLuogo(CreaLuogoRequestDTO request);

    public List<GetAllLuoghiResponseDTO> getAllLuoghi();



}
