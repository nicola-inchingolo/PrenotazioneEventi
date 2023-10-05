package org.elis.softwareprenotazioneeventi.DTO.response;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.elis.softwareprenotazioneeventi.model.Ripetizione;
import org.elis.softwareprenotazioneeventi.model.Sezione;

import java.util.List;

@AllArgsConstructor
@Data
public class GetAllLuoghiResponseDTO {

    private long id;
    private String nome;
    private String via;
    private String città;
    private String provincia;
    private String cap;
    private List<Ripetizione> repliche;
    private List<Sezione> sezioni;
}
