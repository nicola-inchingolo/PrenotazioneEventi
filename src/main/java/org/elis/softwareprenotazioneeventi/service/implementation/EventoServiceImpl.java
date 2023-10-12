package org.elis.softwareprenotazioneeventi.service.implementation;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaEventoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllCategoriaResponseDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllEventsResponseDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllRipetizioneResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Categoria;
import org.elis.softwareprenotazioneeventi.model.Evento;
import org.elis.softwareprenotazioneeventi.model.Role;
import org.elis.softwareprenotazioneeventi.model.User;
import org.elis.softwareprenotazioneeventi.repository.CategoriaRepository;
import org.elis.softwareprenotazioneeventi.repository.EventoRepository;
import org.elis.softwareprenotazioneeventi.repository.UserRepository;
import org.elis.softwareprenotazioneeventi.service.definition.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements EventoService {

    private EventoRepository eventoRepository;
    private CategoriaRepository categoriaRepository;
    private UserRepository userRepository;

    public EventoServiceImpl(EventoRepository e, UserRepository u, CategoriaRepository c)
    {
        categoriaRepository = c;
        eventoRepository = e;
        userRepository = u;
    }

    @Override
    public boolean creazioneEvento(CreaEventoRequestDTO request)
    {

                Optional<Evento> ev = eventoRepository.findByNome(request.getNome());
                if (ev.isEmpty()) {
                    Evento evento = new Evento();
                    evento.setNome(request.getNome());
                    evento.setDescrizione(request.getDescrizione());
                    List<Categoria> categorie = new ArrayList<>();

                    if(request.getCategorie() != null) {
                        request.getCategorie().forEach(c -> {

                            Optional<Categoria> categoria = categoriaRepository.findByNome(c.getNome());
                            if (categoria.isPresent()) {
                                categoria.get().getEventi().add(evento);
                                categoriaRepository.save(categoria.get());
                                categorie.add(categoria.get());
                            }
                        });
                        evento.setCategorie(categorie);
                    }

                    eventoRepository.save(evento);
                   return true;
                }
                else
                {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "evento già esistente");
                }
    }

    @Override
    public List<GetAllEventsResponseDTO> getAllEvents() {

         List<Evento> eventi = eventoRepository.findAll();
         List<GetAllEventsResponseDTO> response =  new ArrayList<>();
         eventi.forEach(e -> {

             List<GetAllCategoriaResponseDTO> categorie = new ArrayList<>();
             e.getCategorie().forEach(cat->{
                 GetAllCategoriaResponseDTO categoria = new GetAllCategoriaResponseDTO(cat.getId(),cat.getNome());
                 categorie.add(categoria);
             });

             List<GetAllRipetizioneResponseDTO> repliche = new ArrayList<>();
             e.getRepliche().forEach(rep->{
                 GetAllRipetizioneResponseDTO replica = new GetAllRipetizioneResponseDTO(rep.getId(),rep.getDatainizio(),rep.getDatafine(),rep.getOraInizio(),rep.getOraFine(),rep.getEvento().getNome(),rep.getLuogo().getNome());
                 repliche.add(replica);
             });


             response.add(
                     new GetAllEventsResponseDTO(e.getId(),e.getNome(),e.getDescrizione(),categorie,repliche)
             );
         });

         return response;


    }

    @Override
    public List<GetAllEventsResponseDTO> getAllEventsByDataInizio(LocalDate data)
    {

        List<Evento> eventi =  eventoRepository.findAllByRepliche_Datainizio(data);
        List<GetAllEventsResponseDTO> response = new ArrayList<>();
        eventi.forEach(e->{

            List<GetAllCategoriaResponseDTO> categorie = new ArrayList<>();
            e.getCategorie().forEach(cat->{
                GetAllCategoriaResponseDTO categoria = new GetAllCategoriaResponseDTO(cat.getId(),cat.getNome());
                categorie.add(categoria);
            });

            List<GetAllRipetizioneResponseDTO> repliche = new ArrayList<>();
            e.getRepliche().forEach(rep->{
                GetAllRipetizioneResponseDTO replica = new GetAllRipetizioneResponseDTO(rep.getId(),rep.getDatainizio(),rep.getDatafine(),rep.getOraInizio(),rep.getOraFine(),rep.getEvento().getNome(),rep.getLuogo().getNome());
                repliche.add(replica);
            });

            response.add(new GetAllEventsResponseDTO(e.getId(), e.getNome(), e.getDescrizione(), categorie, repliche));
        });

        return response;


    }

    //?? - sbagliato
    @Override
    public List<GetAllEventsResponseDTO> getAllEventsByLuogo(String nomeLuogo) {



        List<Evento> eventi =  eventoRepository.findAllByRepliche_Luogo_Nome(nomeLuogo);
        List<GetAllEventsResponseDTO> response = new ArrayList<>();

        eventi.forEach( e-> {

            List<GetAllCategoriaResponseDTO> categorie = new ArrayList<>();
            e.getCategorie().forEach(cat->{
                GetAllCategoriaResponseDTO categoria = new GetAllCategoriaResponseDTO(cat.getId(),cat.getNome());
                categorie.add(categoria);
            });

            List<GetAllRipetizioneResponseDTO> repliche = new ArrayList<>();
            e.getRepliche().forEach(rep->{
                GetAllRipetizioneResponseDTO replica = new GetAllRipetizioneResponseDTO(rep.getId(),rep.getDatainizio(),rep.getDatafine(),rep.getOraInizio(),rep.getOraFine(),rep.getEvento().getNome(),rep.getLuogo().getNome());
                repliche.add(replica);
            });




            response.add(new GetAllEventsResponseDTO(e.getId(), e.getNome(), e.getDescrizione(), categorie, repliche));

        });

        return response;



    }

    @Override
    public boolean deleteEvent(long idEvento) {

      Optional<Evento> e = eventoRepository.findById(idEvento);
      if(e.isPresent())
      {
          eventoRepository.deleteById(idEvento);
          return true;
      }
      else
      {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "evento non resistente");
      }
    }


}
