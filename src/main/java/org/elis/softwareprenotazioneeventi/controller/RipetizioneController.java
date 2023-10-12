package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.CreaReplicaDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllRipetizioneResponseDTO;
import org.elis.softwareprenotazioneeventi.service.definition.RipetizioneService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RipetizioneController {

    RipetizioneService service;

    public RipetizioneController(RipetizioneService s)
    {
        service =s;
    }


    @GetMapping("/venditore/getAllRepliche")
    public ResponseEntity<List<GetAllRipetizioneResponseDTO>> getAllRepliche(UsernamePasswordAuthenticationToken upat)
    {
        return ResponseEntity.ok(service.getAllRipetizioni());
    }


    @PostMapping("/venditore/creaRipetizione")
    public ResponseEntity<Void> creaRipetizione(@Valid @RequestBody CreaReplicaDTO request, UsernamePasswordAuthenticationToken upat)
    {
        boolean creato = service.creaRepliche(request);
        return ResponseEntity.ok().build();
    }





}
