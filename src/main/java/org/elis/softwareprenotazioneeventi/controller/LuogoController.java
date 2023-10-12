package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.CreaLuogoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllLuoghiResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Luogo;
import org.elis.softwareprenotazioneeventi.service.definition.LuogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LuogoController {

    LuogoService service;

    public LuogoController(LuogoService s)
    {
        service = s;
    }

    @GetMapping("/venditore/getAllLuoghi")
    public ResponseEntity<List<GetAllLuoghiResponseDTO>> getAllLuoghi(UsernamePasswordAuthenticationToken upat)
    {
        return ResponseEntity.ok(service.getAllLuoghi());
    }


    @PostMapping("/admin/creaLuogo")
    public ResponseEntity<Void> creaLuogo(@Valid @RequestBody CreaLuogoRequestDTO request, UsernamePasswordAuthenticationToken upat)
    {
        boolean creato = service.creazioneLuogo(request);
        return ResponseEntity.ok().build();
    }



}
