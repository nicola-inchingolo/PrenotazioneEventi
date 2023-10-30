package org.elis.softwareprenotazioneeventi.DTO.response;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elis.softwareprenotazioneeventi.model.Posto;
import org.elis.softwareprenotazioneeventi.model.Ripetizione;
import org.elis.softwareprenotazioneeventi.model.User;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class GetAllBigliettiResponseDTO {

    private long id;
    private double prezzo;
    private Boolean venduto;
    private String nomeUser;
    private String numeroPosto;
    private LocalDate dataRipetizione;
    private String nomeRipetizione;


}
