package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.AggiungiCarrelloDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.BigliettoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaBigliettoDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllBigliettiResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Biglietto;
import org.elis.softwareprenotazioneeventi.service.definition.BigliettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class BigliettoController {

    BigliettoService service;

    public BigliettoController(BigliettoService s)
    {
        service = s;
    }

    @GetMapping("/bigliettiVendutiPerEvento")
    public ResponseEntity<Map<String, Integer>> bigliettiVendutiPerEvento()
    {
        Map<String , Integer> bigliettiVenduti = service.bigliettiVenduti();
        return ResponseEntity.ok().body(bigliettiVenduti);

    }

    @PostMapping("/creaBiglietto")
    public ResponseEntity<Void> creaBiglietto(@Valid @RequestBody BigliettoRequestDTO request)
    {
        boolean creato = service.creaBiglietto(request);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/aggiungiAlCarello")
    public ResponseEntity<Void> aggiungiAlCarrello(@Valid @RequestBody AggiungiCarrelloDTO request)
    {
        boolean aggiunto = service.aggiungiCarello(request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/rimuoviCarello")
    public  ResponseEntity<Void> rimuoviDalCarello(@Valid @RequestBody AggiungiCarrelloDTO request)
    {
        boolean rimosso = service.rimuoviCarello(request);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/confermaAcquisto")
    public ResponseEntity<Void> confermaAcquisto( @Valid @RequestBody AggiungiCarrelloDTO request)
    {
        boolean acquistato = service.confermaAcquisto(request);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/annullaAcquisto")
    public  ResponseEntity<Void> annullaAcquisto( @Valid @RequestBody AggiungiCarrelloDTO request)
    {
        boolean annullato = service.annullaAcquisto(request);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/modificaBiglietto")
    public ResponseEntity<Void> modificaBiglietto(@Valid @RequestBody ModificaBigliettoDTO request)
    {
        boolean modificato = service.modificaPrezzoBiglietto(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllBiglietti")
    public ResponseEntity<List<GetAllBigliettiResponseDTO>> getAllBiglietti()
    {
        return ResponseEntity.ok().body(service.getAllBiglietti());
    }

    @GetMapping("/getStoricoBiglietti")
    public ResponseEntity<List<GetAllBigliettiResponseDTO>> getStoricoBiglietti(@RequestParam long idUser)
    {
        return ResponseEntity.ok().body(service.getStoricoBiglietti(idUser));
    }

}

