package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.CreaEventoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllEventsResponseDTO;
import org.elis.softwareprenotazioneeventi.service.definition.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EventoController {


    EventoService service;

    public EventoController(EventoService s)
    {
        service = s;
    }

    @PostMapping(path="/creaEvento")
    public ResponseEntity<Void> creaEvento(@Valid @RequestBody CreaEventoRequestDTO request)
    {
        boolean creato = service.creazioneEvento(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllEvents")
    public ResponseEntity<List<GetAllEventsResponseDTO>> getAllEvents()
    {
        List<GetAllEventsResponseDTO> eventi =  service.getAllEvents();
        return ResponseEntity.ok(eventi);
    }

    @GetMapping("/getAllEventsByDataInizio")
    public ResponseEntity<List<GetAllEventsResponseDTO>> getAllEventsByDataInizio(@RequestParam LocalDate data)
    {
        List<GetAllEventsResponseDTO> eventi =  service.getAllEventsByDataInizio(data);
        return ResponseEntity.ok(eventi);
    }

    @GetMapping("/getAllEventsByLuogo")
    public ResponseEntity<List<GetAllEventsResponseDTO>> getAllEventsByLuogo(@RequestParam  String nomeLuogo)
    {
        List<GetAllEventsResponseDTO> eventi =  service.getAllEventsByLuogo(nomeLuogo);
        return ResponseEntity.ok(eventi);
    }

    @DeleteMapping("/deleteEvento")
    public ResponseEntity<Void> deleteEvento(@RequestParam long idEvento)
    {
        boolean eliminato = service.deleteEvent(idEvento);
        return ResponseEntity.ok().build();
    }

}
