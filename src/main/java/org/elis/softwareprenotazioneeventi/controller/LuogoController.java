package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.CreaLuogoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllLuoghiResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Luogo;
import org.elis.softwareprenotazioneeventi.service.definition.LuogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LuogoController {

    LuogoService service;

    public LuogoController(LuogoService s)
    {
        service = s;
    }

    @GetMapping("/getAllLuoghi")
    public ResponseEntity<List<GetAllLuoghiResponseDTO>> getAllLuoghi()
    {
        return ResponseEntity.ok(service.getAllLuoghi());
    }


    @PostMapping("/creaLuogo")
    public ResponseEntity<Void> creaLuogo( @Valid @RequestBody CreaLuogoRequestDTO request)
    {
        boolean creato = service.creazioneLuogo(request);
        return ResponseEntity.ok().build();
    }



}
