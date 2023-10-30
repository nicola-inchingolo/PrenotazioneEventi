package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllCategoriaResponseDTO;
import org.elis.softwareprenotazioneeventi.service.definition.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriaController {


    CategoriaService service;

    public CategoriaController(CategoriaService s)
    {
        service = s;
    }

    @GetMapping("/all/getAllCategorie")
    public ResponseEntity<List<GetAllCategoriaResponseDTO>> getAllCategorie()
    {
        return ResponseEntity.ok().body(service.getAllCategorie());
    }


    @PostMapping("/admin/creaCategoria")
    public ResponseEntity<Void> creaCategoria( @Valid @RequestParam String nome, UsernamePasswordAuthenticationToken upat)
    {
        boolean creato = service.creaCategoria(nome);
        return ResponseEntity.ok().build();
    }
}
