package org.elis.softwareprenotazioneeventi.repository;

import org.elis.softwareprenotazioneeventi.model.Posto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostoRepository extends JpaRepository<Posto, Long> {

    Optional<Posto> findPostoByNomeAndAndSezione_Nome(String nome, String nome_sezione);

}
