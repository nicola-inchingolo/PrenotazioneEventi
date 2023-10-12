package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.BigliettoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaBigliettoDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllBigliettiResponseDTO;
import org.elis.softwareprenotazioneeventi.model.User;
import org.elis.softwareprenotazioneeventi.service.definition.BigliettoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BigliettoController {

    BigliettoService service;

    public BigliettoController(BigliettoService s)
    {
        service = s;
    }

    @GetMapping("/venditore/bigliettiVendutiPerEvento")
    public ResponseEntity<Map<String, Integer>> bigliettiVendutiPerEvento(UsernamePasswordAuthenticationToken upat)
    {
        Map<String , Integer> bigliettiVenduti = service.bigliettiVenduti();
        return ResponseEntity.ok().body(bigliettiVenduti);

    }

    @PostMapping("/venditore/creaBiglietto")
    public ResponseEntity<Void> creaBiglietto(@Valid @RequestBody BigliettoRequestDTO request, UsernamePasswordAuthenticationToken upat)
    {
        boolean creato = service.creaBiglietto(request);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/cliente/aggiungiAlCarello")
    public ResponseEntity<Void> aggiungiAlCarrello(@Valid @RequestParam long idBiglietto, UsernamePasswordAuthenticationToken upat)
    {
        User u = (User) upat.getPrincipal();
        boolean aggiunto = service.aggiungiCarello(idBiglietto, u);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/cliente/rimuoviCarello")
    public  ResponseEntity<Void> rimuoviDalCarello(@Valid @RequestParam long idBiglietto, UsernamePasswordAuthenticationToken upat)
    {
        User u = (User) upat.getPrincipal();
        boolean rimosso = service.rimuoviCarello(idBiglietto, u);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/cliente/confermaAcquisto")
    public ResponseEntity<Void> confermaAcquisto( @Valid @RequestParam long idBiglietto, UsernamePasswordAuthenticationToken upat)
    {
        User u = (User) upat.getPrincipal();
        boolean acquistato = service.confermaAcquisto(idBiglietto, u);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/cliente/annullaAcquisto")
    public  ResponseEntity<Void> annullaAcquisto( @Valid @RequestParam long idBiglietto, UsernamePasswordAuthenticationToken upat)
    {
        User u = (User) upat.getPrincipal();
        boolean annullato = service.annullaAcquisto(idBiglietto, u);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/venditore/modificaBiglietto")
    public ResponseEntity<Void> modificaBiglietto(@Valid @RequestBody ModificaBigliettoDTO request, UsernamePasswordAuthenticationToken upat)
    {
        boolean modificato = service.modificaPrezzoBiglietto(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/venditore/getAllBiglietti")
    public ResponseEntity<List<GetAllBigliettiResponseDTO>> getAllBiglietti(UsernamePasswordAuthenticationToken upat)
    {
        return ResponseEntity.ok().body(service.getAllBiglietti());
    }

    @GetMapping("/cliente/getStoricoBiglietti")
    public ResponseEntity<List<GetAllBigliettiResponseDTO>> getStoricoBiglietti(UsernamePasswordAuthenticationToken upat)
    {
        User u = (User) upat.getPrincipal();
        return ResponseEntity.ok().body(service.getStoricoBiglietti(u));
    }

}

