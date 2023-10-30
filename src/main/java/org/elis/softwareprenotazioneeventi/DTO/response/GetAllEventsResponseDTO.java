package org.elis.softwareprenotazioneeventi.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elis.softwareprenotazioneeventi.model.Categoria;
import org.elis.softwareprenotazioneeventi.model.Evento;
import org.elis.softwareprenotazioneeventi.model.Ripetizione;
import org.elis.softwareprenotazioneeventi.model.User;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllEventsResponseDTO {

    private long idEvento;
    private String nome;
    private String descrizione;
    private List<GetAllCategoriaResponseDTO> categorie;
    private List<GetAllRipetizioneResponseDTO> repliche;



    public GetAllEventsResponseDTO(Evento e) {

        idEvento = e.getId();
        nome = e.getNome();
        descrizione = e.getDescrizione();

        List<GetAllCategoriaResponseDTO> categ = new ArrayList<>();
        e.getCategorie().forEach(cat->{
            GetAllCategoriaResponseDTO categoria = new GetAllCategoriaResponseDTO(cat.getId(),cat.getNome());
            categ.add(categoria);
        });

        List<GetAllRipetizioneResponseDTO> repl = new ArrayList<>();
        e.getRepliche().forEach(rep->{
            GetAllRipetizioneResponseDTO replica = new GetAllRipetizioneResponseDTO(rep.getId(),rep.getDatainizio(),rep.getDatafine(),rep.getOraInizio(),rep.getOraFine(),rep.getEvento().getNome(),rep.getLuogo().getNome());
            repl.add(replica);
        });

        categorie = categ;
        repliche = repl;


    }

}
