package org.elis.softwareprenotazioneeventi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalDate dataNascita;
    @Column(nullable = false)
    private String codiceFiscale;
    private Role ruolo = Role.CLIENTE;
    private Boolean attivo = true;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Biglietto> bigliettiAcquistati;

    @ManyToMany(mappedBy = "utenti")
    @JsonManagedReference
    private List<Biglietto> Carrello;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Recensione> recensioni;


}
