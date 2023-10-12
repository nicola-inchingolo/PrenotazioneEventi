package org.elis.softwareprenotazioneeventi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @ManyToMany( cascade = CascadeType.ALL)
    @JoinTable(name = "eventi_categorie",
    joinColumns = @JoinColumn(name = "id_categoria", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "id_evento", nullable = false))
    private List<Evento> eventi;

    public Categoria(String nome) {
        this.nome = nome;
    }
}
