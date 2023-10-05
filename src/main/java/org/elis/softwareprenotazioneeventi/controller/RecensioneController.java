package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.CreaRecensioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaDescrizioneRecensioneDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaVotazioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllRecensioniResponseDTO;
import org.elis.softwareprenotazioneeventi.service.definition.RecensioneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecensioneController {

    RecensioneService service;

    public RecensioneController(RecensioneService s)
    {
        service = s;
    }

    @GetMapping("/getAllRecensioni")
    public ResponseEntity<List<GetAllRecensioniResponseDTO>> getAllRecensioni()
    {
        return ResponseEntity.ok().body(service.getAllRecensioni());
    }



    @PostMapping("/creaRecensione")
    public ResponseEntity<Void> creaRecensione(@Valid @RequestBody CreaRecensioneRequestDTO request)
    {
        boolean creato = service.creaRecensione(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/modificaVotazione")
    public ResponseEntity<Void> modificaVotazione(@Valid @RequestBody ModificaVotazioneRequestDTO request)
    {
        boolean modificato = service.modificaVotazione(request);
        return  ResponseEntity.ok().build();
    }

    @PatchMapping("/modificaDescrizione")
    public ResponseEntity<Void> modificaDescrizione(@Valid @RequestBody ModificaDescrizioneRecensioneDTO request)
    {
        boolean modificato = service.modificaDescrizione(request);
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/rimuoviRecensione")
    public ResponseEntity<Void> rimuoviRecensione(@RequestParam long idRecensione)
    {
        boolean eliminato = service.rimuoviRecensione(idRecensione);
        return ResponseEntity.ok().build();
    }

}
