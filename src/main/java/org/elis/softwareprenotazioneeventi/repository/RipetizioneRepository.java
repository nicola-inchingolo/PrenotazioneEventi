package org.elis.softwareprenotazioneeventi.repository;

import org.elis.softwareprenotazioneeventi.model.Evento;
import org.elis.softwareprenotazioneeventi.model.Luogo;
import org.elis.softwareprenotazioneeventi.model.Ripetizione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface RipetizioneRepository extends JpaRepository<Ripetizione,Long>{

public Optional<Ripetizione> findByDatainizioAndOraInizioAndEventoAndLuogo(LocalDate dataI, LocalTime oraI, Evento e, Luogo l);

}
