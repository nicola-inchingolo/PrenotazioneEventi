package org.elis.softwareprenotazioneeventi.service.definition;

import org.elis.softwareprenotazioneeventi.DTO.request.CreapostoDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllPostiResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PostoService {

    public boolean creaPosto(CreapostoDTO request);

    public List<GetAllPostiResponseDTO> getAllPosti();



}
