package org.elis.softwareprenotazioneeventi.repository;

import org.elis.softwareprenotazioneeventi.model.Biglietto;
import org.elis.softwareprenotazioneeventi.model.Evento;
import org.elis.softwareprenotazioneeventi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BigliettoRepository extends JpaRepository<Biglietto, Long> {

    Optional<Biglietto> findByPosto_NomeAndPosto_Sezione_Id(String Nome, long id);

    List<Biglietto> findAllByUser_EmailAndAndRipetizione_Evento_Nome(String email, String nomeEvento);

   // @Query(nativeQuery = true, "select COUNT(*) from Biglietto inner join Ripetizione inner join Evento where Biglietto.venduto = true GROUP BY Evento.nome")


    //da rivedere
   @Query("select  r.evento.nome, count (b) from Biglietto b " +
           "inner join Ripetizione r on b.ripetizione.id = r.id " +
           "where b.venduto = true " +
           "group by r.evento.nome")
    public  List<Object[]>  bigliettiVenduti();

    //da rivedere
    //@Query("select u.nome, b.ripetizione.evento.nome from Biglietto b inner join User u where b.venduto = true group by u.nome")
    public List<Biglietto> findAllByUser_Id(long id);

}
