package org.elis.softwareprenotazioneeventi.service.implementation;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaLuogoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllLuoghiResponseDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllRipetizioneResponseDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllSezioniResponseDTO;
import org.elis.softwareprenotazioneeventi.Mapper.LuogoMapper;
import org.elis.softwareprenotazioneeventi.model.Luogo;
import org.elis.softwareprenotazioneeventi.repository.LuogoRepository;
import org.elis.softwareprenotazioneeventi.repository.UserRepository;
import org.elis.softwareprenotazioneeventi.service.definition.LuogoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LuogoServiceImpl implements LuogoService {

    private LuogoRepository luogoRepository;
    private UserRepository userRepository;
    private final LuogoMapper luogoMapper;

    public LuogoServiceImpl(LuogoRepository l, UserRepository u, LuogoMapper m)
    {
        userRepository = u;
        luogoRepository = l;
        luogoMapper = m;
    }

    @Override
    public boolean creazioneLuogo(CreaLuogoRequestDTO request) {

                Optional<Luogo> l = luogoRepository.findByNome(request.getNome());
                if (l.isEmpty()) {
                    Luogo luogo = luogoMapper.toLuogoRequestDTO(request);
                   /* luogo.setNome(request.getNome());
                    luogo.setCap(request.getCap());
                    luogo.setCittà(request.getCittà());
                    luogo.setVia(request.getVia());
                    luogo.setProvincia(request.getProvincia()); */
                    //luogo = mapStructLuogo.fromCreaLuogoRequestDTO(request);
                    luogoRepository.save(luogo);
                    return true;
                }
                else
                {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "questo luogo è già esistente");
                }


    }

    @Override
    public List<GetAllLuoghiResponseDTO> getAllLuoghi() {

        List<Luogo> luoghi = luogoRepository.findAll();
        List<GetAllLuoghiResponseDTO> response = new ArrayList<>();

        luoghi.forEach(l ->
        {
            List<GetAllRipetizioneResponseDTO> repliche = new ArrayList<>();
            l.getRepliche().forEach(rep->{

                GetAllRipetizioneResponseDTO replica = new GetAllRipetizioneResponseDTO(rep.getId(),rep.getDatainizio(),rep.getDatafine(),rep.getOraInizio(),rep.getOraFine(),rep.getEvento().getNome(),rep.getLuogo().getNome());
                repliche.add(replica);
            });

            List<GetAllSezioniResponseDTO> sezioni = new ArrayList<>();
            l.getSezioni().forEach(sez->
            {
                List<String> nomePosti = new ArrayList<>();
                sez.getPosti().forEach(np->
                {
                    String nomeposto = np.getNome();
                    nomePosti.add(nomeposto);
                });
                GetAllSezioniResponseDTO sezione = new GetAllSezioniResponseDTO(sez.getId(),sez.getNome(),sez.getLuogo().getNome(),nomePosti);
                sezioni.add(sezione);
            });

            response.add(
              new GetAllLuoghiResponseDTO(l.getId(),l.getNome(),l.getVia(), l.getCittà(), l.getProvincia(), l.getCap(), repliche, sezioni)
            );
        });

        return response;


    }


}
