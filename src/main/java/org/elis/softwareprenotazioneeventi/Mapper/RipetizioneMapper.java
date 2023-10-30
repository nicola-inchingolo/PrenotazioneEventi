package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaReplicaDTO;
import org.elis.softwareprenotazioneeventi.model.Ripetizione;
import org.springframework.stereotype.Component;

@Component
public class RipetizioneMapper {

    public Ripetizione toRipetizioneRequestDTO(CreaReplicaDTO replicaDTO)
    {
        Ripetizione ripetizione = new Ripetizione();
        ripetizione.setDatainizio(replicaDTO.getDataInizio());
        ripetizione.setDatafine(replicaDTO.getDataFine());
        ripetizione.setOraInizio(replicaDTO.getOraInizio());
        ripetizione.setOraFine(replicaDTO.getOraFine());

        return ripetizione;
    }




}
