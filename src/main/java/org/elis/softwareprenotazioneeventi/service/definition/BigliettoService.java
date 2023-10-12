package org.elis.softwareprenotazioneeventi.service.definition;

import org.elis.softwareprenotazioneeventi.DTO.request.BigliettoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaBigliettoDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllBigliettiResponseDTO;
import org.elis.softwareprenotazioneeventi.model.User;

import java.util.List;
import java.util.Map;

public interface BigliettoService {

    public boolean creaBiglietto(BigliettoRequestDTO request);

    public boolean aggiungiCarello(long idBiglietto, User u);

    public boolean confermaAcquisto(long idBiglietto, User u);

    public boolean rimuoviCarello(long idBiglietto, User u);

    public boolean annullaAcquisto(long idBigliettos, User u);

    public Map<String, Integer> bigliettiVenduti();

    public boolean modificaPrezzoBiglietto(ModificaBigliettoDTO request);

    public List<GetAllBigliettiResponseDTO> getAllBiglietti();

    List<GetAllBigliettiResponseDTO> getStoricoBiglietti(User user);



}
