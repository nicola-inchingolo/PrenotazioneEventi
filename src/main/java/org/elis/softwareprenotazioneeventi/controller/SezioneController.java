package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.SezioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllSezioniResponseDTO;
import org.elis.softwareprenotazioneeventi.service.definition.SezioneService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SezioneController {


    SezioneService service;

    public SezioneController(SezioneService s)
    {
        service = s;
    }

    @GetMapping("/venditore/getAllSezioni")// venditore o admin
    public ResponseEntity<List<GetAllSezioniResponseDTO>> getAllSezioni(UsernamePasswordAuthenticationToken upat)
    {
        return ResponseEntity.ok().body(service.getAllSezioni());
    }


    @PostMapping("/admin/creaSezione")//admin o superadmin
    public ResponseEntity<Void> creaSezione(@Valid @RequestBody SezioneRequestDTO request, UsernamePasswordAuthenticationToken upat)
    {
        boolean creato = service.creazioneSezione(request);
        return ResponseEntity.ok().build();
    }
}
