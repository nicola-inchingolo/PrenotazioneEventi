package org.elis.softwareprenotazioneeventi.repository;

import org.elis.softwareprenotazioneeventi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

   Optional<Categoria> findByNome(String nome);

}
