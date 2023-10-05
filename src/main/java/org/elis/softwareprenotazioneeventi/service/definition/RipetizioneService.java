package org.elis.softwareprenotazioneeventi.service.definition;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaReplicaDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllRipetizioneResponseDTO;

import java.util.List;

public interface RipetizioneService {

    public boolean creaRepliche(CreaReplicaDTO request);

    public List<GetAllRipetizioneResponseDTO> getAllRipetizioni();



}
