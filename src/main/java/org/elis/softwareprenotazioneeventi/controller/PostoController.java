package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.CreapostoDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllPostiResponseDTO;
import org.elis.softwareprenotazioneeventi.service.definition.PostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostoController {


    PostoService service;

    public PostoController(PostoService s)
    {
        service = s;
    }

    @GetMapping("/venditore/getAllPosti")
    public ResponseEntity<List<GetAllPostiResponseDTO>> getAllPosti(UsernamePasswordAuthenticationToken upat)
    {
        return ResponseEntity.ok().body(service.getAllPosti());
    }

    @PostMapping("/venditore/creaPosto")
    public ResponseEntity<Void> creaPosto(@Valid @RequestBody CreapostoDTO request, UsernamePasswordAuthenticationToken upat)
    {
       boolean creato = service.creaPosto(request);
       return ResponseEntity.ok().build();
    }


}
