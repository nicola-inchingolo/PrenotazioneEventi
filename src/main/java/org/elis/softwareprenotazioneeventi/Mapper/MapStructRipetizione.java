package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaReplicaDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllRipetizioneResponseDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllSezioniResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Ripetizione;
import org.elis.softwareprenotazioneeventi.model.Sezione;
import org.mapstruct.Mapper;

@Mapper
public interface MapStructRipetizione {

  GetAllRipetizioneResponseDTO toGetAllRipetizioneResponseDTO(Ripetizione ripetizione);
  Ripetizione fromCreaReplicaDTO(CreaReplicaDTO request);

}
