package org.elis.softwareprenotazioneeventi.service.definition;

import org.elis.softwareprenotazioneeventi.DTO.request.SezioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllSezioniResponseDTO;

import java.util.List;

public interface SezioneService {

    public boolean creazioneSezione(SezioneRequestDTO request);

    public List<GetAllSezioniResponseDTO> getAllSezioni();
}
