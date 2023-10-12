package org.elis.softwareprenotazioneeventi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ripetizione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private LocalDate datainizio;
    private LocalDate datafine;
    @Column(nullable = false)
    private LocalTime oraInizio;
    private LocalTime oraFine;
    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false, updatable = false)
    private Evento evento;
    @ManyToOne
    @JoinColumn(name = "id_luogo", nullable = false)
    private Luogo luogo;
    @OneToMany(mappedBy = "ripetizione", cascade = CascadeType.REMOVE)
    private List<Biglietto> biglietti;

    public Ripetizione(LocalDate datainizio, LocalDate datafine, LocalTime oraInizio, LocalTime oraFine, Evento evento) {
        this.datainizio = datainizio;
        this.datafine = datafine;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.evento = evento;
    }
}
