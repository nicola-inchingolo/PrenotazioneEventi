package org.elis.softwareprenotazioneeventi.service.definition;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaRecensioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaDescrizioneRecensioneDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaVotazioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllRecensioniResponseDTO;

import java.util.List;

public interface RecensioneService {

    public boolean creaRecensione(CreaRecensioneRequestDTO request);

    public List<GetAllRecensioniResponseDTO> getAllRecensioni();

    public boolean modificaVotazione(ModificaVotazioneRequestDTO request);

    public boolean modificaDescrizione(ModificaDescrizioneRecensioneDTO request);

    public boolean rimuoviRecensione(long idRecensione);

}
