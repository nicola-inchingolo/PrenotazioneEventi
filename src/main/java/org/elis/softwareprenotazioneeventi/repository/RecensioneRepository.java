package org.elis.softwareprenotazioneeventi.repository;

import org.elis.softwareprenotazioneeventi.model.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecensioneRepository extends JpaRepository<Recensione, Long> {

    Optional<Recensione> findByDescrizioneAndUser_Email(String descrizione, String email);

}
