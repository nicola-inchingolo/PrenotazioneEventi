package org.elis.softwareprenotazioneeventi.service.implementation;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaLuogoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllLuoghiResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Luogo;
import org.elis.softwareprenotazioneeventi.model.Role;
import org.elis.softwareprenotazioneeventi.model.User;
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

    public LuogoServiceImpl(LuogoRepository l, UserRepository u)
    {
        userRepository = u;
        luogoRepository = l;
    }

    @Override
    public boolean creazioneLuogo(CreaLuogoRequestDTO request) {
        Optional<User> user = userRepository.findById(request.getIdUserRichiesta());

        if(user.isPresent()) {
            if (user.get().getRuolo().equals(Role.SUPERADMIN)) {
                Optional<Luogo> l = luogoRepository.findByNome(request.getNome());
                if (l.isEmpty()) {
                    Luogo luogo = new Luogo();
                    luogo.setNome(request.getNome());
                    luogo.setCap(request.getCap());
                    luogo.setCittà(request.getCittà());
                    luogo.setVia(request.getVia());
                    luogo.setProvincia(request.getProvincia());
                    luogoRepository.save(luogo);
                    return true;
                }
                else
                {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "questo luogo è già esistente");
                }
            }
            else
            {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "l'utente non è autorizzato a svolgere quest'azione");
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
        }
    }

    @Override
    public List<GetAllLuoghiResponseDTO> getAllLuoghi() {

        List<Luogo> luoghi = luogoRepository.findAll();
        List<GetAllLuoghiResponseDTO> response = new ArrayList<>();

        luoghi.forEach(l ->
        {
            response.add(
              new GetAllLuoghiResponseDTO(l.getId(),l.getNome(),l.getVia(), l.getCittà(), l.getProvincia(), l.getCap(), l.getRepliche(), l.getSezioni())
            );
        });

        return response;

    }


}
