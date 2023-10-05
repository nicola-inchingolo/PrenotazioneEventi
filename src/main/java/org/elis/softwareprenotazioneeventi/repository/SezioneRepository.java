package org.elis.softwareprenotazioneeventi.repository;

import org.elis.softwareprenotazioneeventi.model.Sezione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SezioneRepository extends JpaRepository<Sezione, Long> {

    public List<Sezione> findAllByLuogo_Id(long idLuogo);

}
