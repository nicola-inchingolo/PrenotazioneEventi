package org.elis.softwareprenotazioneeventi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Biglietto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double prezzo;
    @Column(nullable = false)
    private Boolean venduto = false;
    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;
    @ManyToMany
    @JoinTable(name = "carrello",
            joinColumns = @JoinColumn(name = "id_biglietto", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_utente", nullable = false))
    @JsonBackReference
    private List<User> utenti;
    @OneToOne
    @JoinColumn(name = "id_posto", referencedColumnName = "id")
    @JsonBackReference
    private Posto posto;
    @ManyToOne
    @JoinColumn(name = "id_ripetizione")
    @JsonBackReference
    private Ripetizione ripetizione;


    public Biglietto(double prezzo, Posto posto ) {
        this.prezzo = prezzo;
        this.posto = posto;

    }
}
