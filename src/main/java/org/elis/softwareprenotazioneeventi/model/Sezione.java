package org.elis.softwareprenotazioneeventi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sezione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "id_luogo", nullable = false)
    @JsonBackReference
    private Luogo luogo;//SPRING CERCA DI serializzare la connessione tra i due stampando la lista in ciclo perciò la soluzione prevederebbe di non stampare tutto l'oggetto ma solo alcune cose
    @OneToMany(mappedBy = "sezione")
    @JsonManagedReference
    private List<Posto> posti;

    public Sezione(String nome, Posto posto) {
        this.nome = nome;

    }
}
