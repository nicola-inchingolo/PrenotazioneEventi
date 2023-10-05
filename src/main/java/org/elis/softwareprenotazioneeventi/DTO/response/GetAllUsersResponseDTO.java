package org.elis.softwareprenotazioneeventi.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.elis.softwareprenotazioneeventi.model.Biglietto;
import org.elis.softwareprenotazioneeventi.model.Recensione;
import org.elis.softwareprenotazioneeventi.model.Role;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
public class GetAllUsersResponseDTO
{
    private long idUser;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private LocalDate dataNascita;
    private String codiceFiscale;
    private Role ruolo;
    private Boolean attivo;
    private List<Biglietto> carrello;
    private List<Biglietto> bigliettiAcquistati;
    private List<Recensione> recensioni;



}
