package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.SezioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllSezioniResponseDTO;
import org.elis.softwareprenotazioneeventi.service.definition.SezioneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SezioneController {


    SezioneService service;

    public SezioneController(SezioneService s)
    {
        service = s;
    }

    @GetMapping("/getAllSezioni")
    public ResponseEntity<List<GetAllSezioniResponseDTO>> getAllSezioni()
    {
        return ResponseEntity.ok().body(service.getAllSezioni());
    }


    @PostMapping("/creaSezione")
    public ResponseEntity<Void> creaSezione(@Valid @RequestBody SezioneRequestDTO request)
    {
        boolean creato = service.creazioneSezione(request);
        return ResponseEntity.ok().build();
    }
}
