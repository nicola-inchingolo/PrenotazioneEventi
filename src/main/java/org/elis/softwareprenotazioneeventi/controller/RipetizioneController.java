package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.CreaReplicaDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllRipetizioneResponseDTO;
import org.elis.softwareprenotazioneeventi.service.definition.RipetizioneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RipetizioneController {

    RipetizioneService service;

    public RipetizioneController(RipetizioneService s)
    {
        service =s;
    }


    @GetMapping("/getAllRepliche")
    public ResponseEntity<List<GetAllRipetizioneResponseDTO>> getAllRepliche()
    {
        return ResponseEntity.ok(service.getAllRipetizioni());
    }


    @PostMapping("/creaRipetizione")
    public ResponseEntity<Void> creaRipetizione(@Valid @RequestBody CreaReplicaDTO request)
    {
        boolean creato = service.creaRepliche(request);
        return ResponseEntity.ok().build();
    }





}
