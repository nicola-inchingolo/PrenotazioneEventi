package org.elis.softwareprenotazioneeventi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    private String descrizione;
    @ManyToMany(mappedBy = "eventi", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Categoria> categorie;
    @OneToMany(mappedBy = "evento", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Ripetizione> repliche;
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Recensione> recensioni;

    public Evento(String nome, String descrizione, List<Luogo> luoghi, List<Categoria> categorie) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.categorie=categorie;
    }
}
