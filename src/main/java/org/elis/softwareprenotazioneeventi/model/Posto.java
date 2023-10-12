package org.elis.softwareprenotazioneeventi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Posto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name="id_sezione", nullable = false)
    private Sezione sezione;
    @OneToOne(mappedBy = "posto")
    private Biglietto biglietto;

    public Posto(String nome) {
        this.nome = nome;
    }
}
