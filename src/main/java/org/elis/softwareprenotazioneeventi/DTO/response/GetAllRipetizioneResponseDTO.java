package org.elis.softwareprenotazioneeventi.DTO.response;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.elis.softwareprenotazioneeventi.model.Biglietto;
import org.elis.softwareprenotazioneeventi.model.Evento;
import org.elis.softwareprenotazioneeventi.model.Luogo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
public class GetAllRipetizioneResponseDTO {

    private long id;

    private LocalDate datainizio;
    private LocalDate datafine;
    private LocalTime oraInizio;
    private LocalTime oraFine;
    private String NomeEvento;
    private String NomeLuogo;
   // private List<Biglietto> biglietti;





}
