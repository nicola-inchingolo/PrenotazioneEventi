package org.elis.softwareprenotazioneeventi.service.implementation;

import org.elis.softwareprenotazioneeventi.DTO.request.SezioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllRipetizioneResponseDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllSezioniResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Luogo;
import org.elis.softwareprenotazioneeventi.model.Ripetizione;
import org.elis.softwareprenotazioneeventi.model.Sezione;
import org.elis.softwareprenotazioneeventi.repository.LuogoRepository;
import org.elis.softwareprenotazioneeventi.repository.SezioneRepository;
import org.elis.softwareprenotazioneeventi.service.definition.SezioneService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SezioneServiceImpl implements SezioneService {

    private SezioneRepository sezioneRepository;
    private LuogoRepository luogoRepository;

    public SezioneServiceImpl(SezioneRepository s, LuogoRepository l)
    {
        sezioneRepository = s;
        luogoRepository = l;
    }

    public boolean creazioneSezione(SezioneRequestDTO request)
    {
        boolean esiste= false;
        Optional<Luogo> luogo = luogoRepository.findById(request.getIdLuogo());
        if(luogo.isPresent()) {
            List<Sezione> sezioniLuogo = sezioneRepository.findAllByLuogo_Id(request.getIdLuogo());
            esiste = sezioniLuogo.stream()
                    .anyMatch(sl -> request.getNome().equals(sl.getNome()));

            if (sezioniLuogo.isEmpty() || !esiste ) {
                Sezione sezione = new Sezione();
                sezione.setNome(request.getNome());
                sezione.setLuogo(luogo.get());
                luogo.get().getSezioni().add(sezione);
                sezioneRepository.save(sezione);
                luogoRepository.save(luogo.get());
                return true;
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sezione già esistente");
            }
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"qualcosa è andato storto");
        }

    }

    @Override
    public List<GetAllSezioniResponseDTO> getAllSezioni() {
        List<Sezione> sezioni = sezioneRepository.findAll();
        List<GetAllSezioniResponseDTO> response = new ArrayList<>();

        sezioni.forEach(s ->
        {
            response.add(
                    new GetAllSezioniResponseDTO(s.getId(), s.getNome(), s.getLuogo().getNome(), s.getPosti())
            );
        });

        return response;
    }


}
