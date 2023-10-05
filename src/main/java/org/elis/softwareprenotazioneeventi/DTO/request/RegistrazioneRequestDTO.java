package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistrazioneRequestDTO {

    @NotBlank(message = "il nome non può essere null")
    private String nome;
    @NotBlank(message = "il cognome non può essere null")
    private String cognome;
    @NotBlank(message = "l'email non può essere nulla")
    @Email(message = "email non valida")
    private String email;
    @NotBlank(message = "la password non può essere nulla")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "inserisci un carattere maiuscolo, una minuscola, un carattere speciale e un numero, tra gli 8 e i 20 caratteri")
    private String password;
    @NotBlank(message = "campo obbligatorio")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "inserisci un carattere maiuscolo, una minuscola, un carattere speciale e un numero, tra gli 8 e i 20 caratteri")
    private String passwordRipetuta;
    @Past(message = "non puoi essere nato nel futuro")
    private LocalDate dataDiNascita;
    @NotBlank(message = "non può essere null")
    @Pattern(regexp = "[A-Z0-9]+.{16}$")
    private String codiceFiscale;
    @NotNull
    private long idUserRichiesta;
}
