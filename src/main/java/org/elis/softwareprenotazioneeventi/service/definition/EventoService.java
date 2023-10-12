package org.elis.softwareprenotazioneeventi.service.definition;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaEventoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllEventsResponseDTO;
import org.elis.softwareprenotazioneeventi.model.User;

import java.time.LocalDate;
import java.util.List;

public interface EventoService {

    public boolean creazioneEvento(CreaEventoRequestDTO request);

    public List<GetAllEventsResponseDTO> getAllEvents();
    public List<GetAllEventsResponseDTO> getAllEventsByDataInizio(LocalDate data);

    public List<GetAllEventsResponseDTO> getAllEventsByLuogo(String nomeLuogo);

    public boolean deleteEvent(long idEvento);

}
