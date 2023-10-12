package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.CreaRecensioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaDescrizioneRecensioneDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaVotazioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllRecensioniResponseDTO;
import org.elis.softwareprenotazioneeventi.model.User;
import org.elis.softwareprenotazioneeventi.service.definition.RecensioneService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecensioneController {

    RecensioneService service;

    public RecensioneController(RecensioneService s)
    {
        service = s;
    }

    @GetMapping("/venditore/getAllRecensioni")//venditore o admin?
    public ResponseEntity<List<GetAllRecensioniResponseDTO>> getAllRecensioni(UsernamePasswordAuthenticationToken upat)
    {
        return ResponseEntity.ok().body(service.getAllRecensioni());
    }



    @PostMapping("/cliente/creaRecensione")//clienti
    public ResponseEntity<Void> creaRecensione(@Valid @RequestBody CreaRecensioneRequestDTO request, UsernamePasswordAuthenticationToken upat)
    {
        User u = (User) upat.getPrincipal();
        boolean creato = service.creaRecensione(request, u);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/cliente/modificaVotazione")//clkiente controlla se l'authentication è uguale al cliente di cui modifico la votazione
    public ResponseEntity<Void> modificaVotazione(@Valid @RequestBody ModificaVotazioneRequestDTO request, UsernamePasswordAuthenticationToken upat)
    {
        User u = (User) upat.getPrincipal();
        boolean modificato = service.modificaVotazione(request, u);
        return  ResponseEntity.ok().build();
    }

    @PatchMapping("/cliente/modificaDescrizione")//cliente  controlla se l'authentication è uguale al cliente di cui modifico la votazion
    public ResponseEntity<Void> modificaDescrizione(@Valid @RequestBody ModificaDescrizioneRecensioneDTO request, UsernamePasswordAuthenticationToken upat)
    {
        User u = (User) upat.getPrincipal();
        boolean modificato = service.modificaDescrizione(request, u);
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/venditore/rimuoviRecensione")//venditore o admin
    public ResponseEntity<Void> rimuoviRecensione(@RequestParam long idRecensione, UsernamePasswordAuthenticationToken upat)
    {
        boolean eliminato = service.rimuoviRecensione(idRecensione);
        return ResponseEntity.ok().build();
    }

}
