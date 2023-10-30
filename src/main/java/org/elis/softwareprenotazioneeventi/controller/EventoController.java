package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.CreaEventoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.FiltroAvanzato;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllEventsResponseDTO;
import org.elis.softwareprenotazioneeventi.service.definition.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @PostMapping("/venditore/creaEvento")
    public ResponseEntity<Void> creaEvento(@Valid @RequestBody CreaEventoRequestDTO request, UsernamePasswordAuthenticationToken upat)
    {
        boolean creato = service.creazioneEvento(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/getAllEvents")
    public ResponseEntity<List<GetAllEventsResponseDTO>> getAllEvents()
    {
        List<GetAllEventsResponseDTO> eventi =  service.getAllEvents();
        return ResponseEntity.ok(eventi);
    }

    @GetMapping("/all/getAllEventsByDataInizio")
    public ResponseEntity<List<GetAllEventsResponseDTO>> getAllEventsByDataInizio(@RequestParam LocalDate data)
    {
        List<GetAllEventsResponseDTO> eventi =  service.getAllEventsByDataInizio(data);
        return ResponseEntity.ok(eventi);
    }

    @GetMapping("/all/getAllEventsByLuogo")
    public ResponseEntity<List<GetAllEventsResponseDTO>> getAllEventsByLuogo(@RequestParam  String nomeLuogo)
    {
        List<GetAllEventsResponseDTO> eventi =  service.getAllEventsByLuogo(nomeLuogo);
        return ResponseEntity.ok(eventi);
    }

    @DeleteMapping("/venditore/deleteEvento")
    public ResponseEntity<Void> deleteEvento(@RequestParam long idEvento, UsernamePasswordAuthenticationToken upat)
    {
        boolean eliminato = service.deleteEvent(idEvento);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/all/getEventiFiltrati")
    public ResponseEntity<List<GetAllEventsResponseDTO>> getEventiFiltrati(@RequestBody FiltroAvanzato request)
    {
        List<GetAllEventsResponseDTO> events = service.getEventiFiltrati(request).stream().map(GetAllEventsResponseDTO::new).toList();
        return  ResponseEntity.status(HttpStatus.OK).body(events);
    }

}
