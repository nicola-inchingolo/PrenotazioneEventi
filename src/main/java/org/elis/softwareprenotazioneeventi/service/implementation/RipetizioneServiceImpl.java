package org.elis.softwareprenotazioneeventi.service.implementation;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaReplicaDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllRipetizioneResponseDTO;
import org.elis.softwareprenotazioneeventi.Mapper.RipetizioneMapper;
import org.elis.softwareprenotazioneeventi.model.Evento;
import org.elis.softwareprenotazioneeventi.model.Luogo;
import org.elis.softwareprenotazioneeventi.model.Ripetizione;
import org.elis.softwareprenotazioneeventi.repository.EventoRepository;
import org.elis.softwareprenotazioneeventi.repository.LuogoRepository;
import org.elis.softwareprenotazioneeventi.repository.RipetizioneRepository;
import org.elis.softwareprenotazioneeventi.service.definition.RipetizioneService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RipetizioneServiceImpl implements RipetizioneService {

    RipetizioneRepository ripetizioneRepository;
    EventoRepository eventoRepository;
    LuogoRepository luogoRepository;

    private final RipetizioneMapper ripetizioneMapper;


    public RipetizioneServiceImpl(RipetizioneRepository r, EventoRepository e, LuogoRepository l, RipetizioneMapper ri)
    {
        eventoRepository = e;
        ripetizioneRepository = r;
        luogoRepository = l;
        ripetizioneMapper = ri;

    }

    @Override
    public boolean creaRepliche(CreaReplicaDTO request) {

        Optional<Evento> evento = eventoRepository.findById(request.getIdEvento());
        Optional<Luogo> luogo = luogoRepository.findById(request.getIdLuogo());

        if(evento.isPresent() && luogo.isPresent())
        {
         Optional<Ripetizione> r = ripetizioneRepository.findByDatainizioAndOraInizioAndEventoAndLuogo(request.getDataInizio(), request.getOraInizio(),evento.get(), luogo.get());

         if(r.isEmpty()) {
             /*if(request.getDataInizio().isAfter(LocalDate.now())) {*/
                 if(request.getDataFine().isAfter(request.getDataInizio())) {
                     if(request.getOraFine().isAfter(request.getOraInizio())) {
                         Ripetizione ripetizione = ripetizioneMapper.toRipetizioneRequestDTO(request) ;
                        /* ripetizione.setDatainizio(request.getDataInizio());
                         ripetizione.setDatafine(request.getDataFine());
                         ripetizione.setOraInizio(request.getOraInizio());
                         ripetizione.setOraFine(request.getOraFine()); */
                         //ripetizione = mapStructRipetizione.fromCreaReplicaDTO(request);
                         ripetizione.setEvento(evento.get());
                         ripetizione.setLuogo(luogo.get());
                         ripetizioneRepository.save(ripetizione);
                         return true;
                     }
                     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ora non valida");
                 }
                 else
                 {
                     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "data non valida");
                 }
             }
             /*else
             {
                 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "data non valida");
             }*/

         else
         {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "replica già esistenza");
         }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
        }
    }

    @Override
    public List<GetAllRipetizioneResponseDTO> getAllRipetizioni() {
        List<Ripetizione> repliche = ripetizioneRepository.findAll();
        List<GetAllRipetizioneResponseDTO> response = new ArrayList<>();

        repliche.forEach(r ->
        {
            response.add(
                    new GetAllRipetizioneResponseDTO(r.getId(),r.getDatainizio(),r.getDatafine(),r.getOraInizio(),r.getOraFine(),r.getEvento().getNome(),r.getLuogo().getNome()/*,r.getBiglietti()*/)
            );
        });

        return response;
    }
}
