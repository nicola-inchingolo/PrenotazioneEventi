package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreaReplicaDTO {

    @NotBlank
    @Future(message = "la replica non può essere creata prima della data attuale")
    private LocalDate dataInizio;
    @NotBlank
    @Future(message = "la replica non può essere creata prima della data attuale")
    private LocalDate dataFine;
    @NotBlank
    @Future
    private LocalTime oraInizio;
    @NotBlank
    @Future
    private LocalTime oraFine;
    @NotNull
    private long idEvento;
    @NotNull
    private long idLuogo;



}
