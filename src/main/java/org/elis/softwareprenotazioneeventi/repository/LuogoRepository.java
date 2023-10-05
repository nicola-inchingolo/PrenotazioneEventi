package org.elis.softwareprenotazioneeventi.repository;

import org.elis.softwareprenotazioneeventi.model.Luogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LuogoRepository extends JpaRepository<Luogo, Long> {

     Optional<Luogo> findByNome(String nome);
}
