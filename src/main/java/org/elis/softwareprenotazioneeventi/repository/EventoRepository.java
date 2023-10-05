package org.elis.softwareprenotazioneeventi.repository;

import org.elis.softwareprenotazioneeventi.model.Evento;
import org.elis.softwareprenotazioneeventi.model.Luogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    Optional<Evento> findByNome(String nome);
    List<Evento> findAllByRepliche_Datainizio(LocalDate data);

    List<Evento> findAllByRepliche_Luogo_Nome(String nomeLuogo);




}
