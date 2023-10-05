package org.elis.softwareprenotazioneeventi.DTO.response;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.elis.softwareprenotazioneeventi.model.Biglietto;
import org.elis.softwareprenotazioneeventi.model.Sezione;

@Data
@AllArgsConstructor
public class GetAllPostiResponseDTO {

    private long id;
    private String nome;
    private String sezioneNome;
    private Biglietto biglietto;
}
