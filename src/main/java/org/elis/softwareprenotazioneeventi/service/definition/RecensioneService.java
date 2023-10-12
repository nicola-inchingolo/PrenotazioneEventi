package org.elis.softwareprenotazioneeventi.service.definition;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaRecensioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaDescrizioneRecensioneDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaVotazioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllRecensioniResponseDTO;
import org.elis.softwareprenotazioneeventi.model.User;

import java.util.List;

public interface RecensioneService {

    public boolean creaRecensione(CreaRecensioneRequestDTO request, User user);

    public List<GetAllRecensioniResponseDTO> getAllRecensioni();

    public boolean modificaVotazione(ModificaVotazioneRequestDTO request, User user);

    public boolean modificaDescrizione(ModificaDescrizioneRecensioneDTO request, User user);

    public boolean rimuoviRecensione(long idRecensione);

}
