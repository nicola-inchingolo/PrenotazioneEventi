package org.elis.softwareprenotazioneeventi.model;

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
public class Luogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String via;
    @Column(nullable = false)
    private String città;
    private String provincia;
    private String cap;
    @OneToMany(mappedBy = "luogo")
    private List<Ripetizione> repliche;
    @OneToMany(mappedBy = "luogo")
    private List<Sezione> sezioni;


    public Luogo(String nome, String via, String città, String provincia, String cap) {
        this.nome = nome;
        this.via = via;
        this.città = città;
        this.provincia = provincia;
        this.cap = cap;
    }
}
