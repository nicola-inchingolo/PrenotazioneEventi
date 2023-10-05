package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.CreapostoDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllPostiResponseDTO;
import org.elis.softwareprenotazioneeventi.service.definition.PostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostoController {


    PostoService service;

    public PostoController(PostoService s)
    {
        service = s;
    }

    @GetMapping("/getAllPosti")
    public ResponseEntity<List<GetAllPostiResponseDTO>> getAllPosti()
    {
        return ResponseEntity.ok().body(service.getAllPosti());
    }

    @PostMapping("/creaPosto")
    public ResponseEntity<Void> creaPosto( @Valid @RequestBody CreapostoDTO request)
    {
       boolean creato = service.creaPosto(request);
       return ResponseEntity.ok().build();
    }


}
