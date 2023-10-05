package org.elis.softwareprenotazioneeventi.service.definition;

import org.elis.softwareprenotazioneeventi.DTO.request.AggiungiCarrelloDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.BigliettoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaBigliettoDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllBigliettiResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Biglietto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BigliettoService {

    public boolean creaBiglietto(BigliettoRequestDTO request);

    public boolean aggiungiCarello(AggiungiCarrelloDTO request);

    public boolean confermaAcquisto(AggiungiCarrelloDTO request);

    public boolean rimuoviCarello(AggiungiCarrelloDTO request);

    public boolean annullaAcquisto(AggiungiCarrelloDTO request);

    public Map<String, Integer> bigliettiVenduti();

    public boolean modificaPrezzoBiglietto(ModificaBigliettoDTO request);

    public List<GetAllBigliettiResponseDTO> getAllBiglietti();

    List<GetAllBigliettiResponseDTO> getStoricoBiglietti(long id);



}
