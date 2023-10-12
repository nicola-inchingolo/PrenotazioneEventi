package org.elis.softwareprenotazioneeventi.service.implementation;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaRecensioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaDescrizioneRecensioneDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaVotazioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllRecensioniResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Biglietto;
import org.elis.softwareprenotazioneeventi.model.Evento;
import org.elis.softwareprenotazioneeventi.model.Recensione;
import org.elis.softwareprenotazioneeventi.model.User;
import org.elis.softwareprenotazioneeventi.repository.BigliettoRepository;
import org.elis.softwareprenotazioneeventi.repository.EventoRepository;
import org.elis.softwareprenotazioneeventi.repository.RecensioneRepository;
import org.elis.softwareprenotazioneeventi.repository.UserRepository;
import org.elis.softwareprenotazioneeventi.service.definition.RecensioneService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecensioneServiceImpl implements  RecensioneService{

    RecensioneRepository recensioneRepository;
    UserRepository userRepository;
    EventoRepository eventoRepository;

    BigliettoRepository bigliettoRepository;

    public RecensioneServiceImpl(RecensioneRepository r, UserRepository u, EventoRepository e, BigliettoRepository b)
    {
        recensioneRepository = r;
        userRepository = u;
        eventoRepository = e;
        bigliettoRepository = b;
    }

    @Override
    public boolean creaRecensione(CreaRecensioneRequestDTO request, User user) {

        Optional<Evento> evento = eventoRepository.findById(request.getIdEvento());




        if(evento.isPresent())
        {
            List<Biglietto> biglietti = bigliettoRepository.findAllByUser_EmailAndAndRipetizione_Evento_Nome(user.getEmail(), evento.get().getNome());
            if(!biglietti.isEmpty()) {
                Recensione recensione = new Recensione();
                recensione.setDescrizione(request.getDescrizione());
                recensione.setVotazione(request.getVotazione());
                recensione.setUser(user);
                recensione.setEvento(evento.get());

                user.getRecensioni().add(recensione);
                evento.get().getRecensioni().add(recensione);

                recensioneRepository.save(recensione);
                userRepository.save(user);
                eventoRepository.save(evento.get());
                return true;
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "l'utente deve aver partecipato all'evento");
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
        }
    }

    @Override
    public List<GetAllRecensioniResponseDTO> getAllRecensioni() {

        List<Recensione> recensioni = recensioneRepository.findAll();
        List<GetAllRecensioniResponseDTO> response = new ArrayList<>();

        recensioni.forEach( r->
        {
            response.add(new GetAllRecensioniResponseDTO(r.getId(), r.getDescrizione(), r.getVotazione(), r.getUser().getNome(), r.getEvento().getNome()));
        });

        return response;
    }

    @Override
    public boolean modificaVotazione(ModificaVotazioneRequestDTO request, User user) {

        Optional<Recensione> r = recensioneRepository.findById(request.getIdRecensione());

        if(r.isPresent())
        {
            if(r.get().getUser().equals(user)) {
                r.get().setVotazione(request.getNewVotazione());
                return true;
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "non sei autorizzato a modificare la recensione di un altro utente");
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
        }
    }

    @Override
    public boolean modificaDescrizione(ModificaDescrizioneRecensioneDTO request, User user) {

        Optional<Recensione> r = recensioneRepository.findById(request.getIdRecensione());

        if(r.isPresent())
        {
            if(r.get().getUser().equals(user)) {
                r.get().setDescrizione(request.getNewDescrizione());
                return true;
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "non sei autorizzato a modificare la recensione di un altro utente");
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
        }

    }

    @Override
    public boolean rimuoviRecensione(long idRecensione) {
        Optional<Recensione> r = recensioneRepository.findById(idRecensione);
        if(r.isPresent())
        {
            eventoRepository.deleteById(idRecensione);
            return true;
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "recensione non esistente");
        }
    }


}
