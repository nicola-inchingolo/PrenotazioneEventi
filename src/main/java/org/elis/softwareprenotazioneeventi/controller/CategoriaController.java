package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.CreaCategoriaRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllCategoriaResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Categoria;
import org.elis.softwareprenotazioneeventi.service.definition.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoriaController {


    CategoriaService service;

    public CategoriaController(CategoriaService s)
    {
        service = s;
    }

    @GetMapping("/getAllCategorie")
    public ResponseEntity<List<GetAllCategoriaResponseDTO>> getAllCategorie()
    {
        return ResponseEntity.ok().body(service.getAllCategorie());
    }


    @PostMapping("/creaCategoria")
    public ResponseEntity<Void> creaCategoria( @Valid @RequestBody CreaCategoriaRequestDTO request)
    {
        boolean creato = service.creaCategoria(request);
        return ResponseEntity.ok().build();
    }
}
