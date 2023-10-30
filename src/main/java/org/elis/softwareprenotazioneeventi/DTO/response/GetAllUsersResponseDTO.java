package org.elis.softwareprenotazioneeventi.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elis.softwareprenotazioneeventi.model.Biglietto;
import org.elis.softwareprenotazioneeventi.model.Recensione;
import org.elis.softwareprenotazioneeventi.model.Role;
import org.elis.softwareprenotazioneeventi.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetAllUsersResponseDTO
{
    private long idUser;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private LocalDate dataNascita;
    private String codiceFiscale;
    private Role ruolo;
    private Boolean attivo;
    private List<GetAllBigliettiResponseDTO> carrello;
    private List<GetAllBigliettiResponseDTO> bigliettiAcquistati;
    private List<GetAllRecensioniResponseDTO> recensioni;


    public GetAllUsersResponseDTO(User user) {

        idUser = user.getId();
        nome = user.getNome();
        cognome = user.getCognome();
        email = user.getEmail();
        password = user.getPassword();
        dataNascita = user.getDataNascita();
        codiceFiscale = user.getCodiceFiscale();
        ruolo = user.getRuolo();
        attivo = user.getAttivo();
        List<GetAllBigliettiResponseDTO> carr = new ArrayList<>();
        user.getCarrello().forEach(ca->{

            GetAllBigliettiResponseDTO bigliettoCarrello = new GetAllBigliettiResponseDTO(ca.getId(),ca.getPrezzo(),ca.getVenduto(),ca.getUser().getNome(),ca.getPosto().getNome(),ca.getRipetizione().getDatainizio(),ca.getRipetizione().getEvento().getNome());
            carrello.add(bigliettoCarrello);
        });
        List<GetAllBigliettiResponseDTO> acq = new ArrayList<>();
        user.getBigliettiAcquistati().forEach(ba->{

            GetAllBigliettiResponseDTO bigliettoAcquistato = new GetAllBigliettiResponseDTO(ba.getId(),ba.getPrezzo(),ba.getVenduto(),ba.getUser().getNome(),ba.getPosto().getNome(),ba.getRipetizione().getDatainizio(),ba.getRipetizione().getEvento().getNome());
            acq.add(bigliettoAcquistato);
        });
        List<GetAllRecensioniResponseDTO> rec = new ArrayList<>();
        user.getRecensioni().forEach(re->{

            GetAllRecensioniResponseDTO recensione = new GetAllRecensioniResponseDTO(re.getId(), re.getDescrizione(), re.getVotazione(),re.getUser().getNome(),re.getEvento().getNome());
            rec.add(recensione);
        });

        carrello = carr;
        bigliettiAcquistati = acq;
        recensioni = rec;
    }
}
