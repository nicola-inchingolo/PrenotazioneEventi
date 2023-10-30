package org.elis.softwareprenotazioneeventi.service.implementation;

import org.elis.softwareprenotazioneeventi.DTO.request.CreapostoDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllBigliettiResponseDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllLuoghiResponseDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllPostiResponseDTO;
import org.elis.softwareprenotazioneeventi.Mapper.MapStructPosto;
import org.elis.softwareprenotazioneeventi.model.Luogo;
import org.elis.softwareprenotazioneeventi.model.Posto;
import org.elis.softwareprenotazioneeventi.model.Sezione;
import org.elis.softwareprenotazioneeventi.repository.PostoRepository;
import org.elis.softwareprenotazioneeventi.repository.SezioneRepository;
import org.elis.softwareprenotazioneeventi.service.definition.PostoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostoServiceImpl implements PostoService {

    PostoRepository postoRepository;
    SezioneRepository sezioneRepository;


    public PostoServiceImpl(PostoRepository p, SezioneRepository s)
    {
        postoRepository = p;
        sezioneRepository = s;
    }

    @Override
    public boolean creaPosto(CreapostoDTO request) {

        Optional<Sezione> sezione = sezioneRepository.findById(request.getIdSezione());

        if(sezione.isPresent()) {
            Optional<Posto> p = postoRepository.findPostoByNomeAndAndSezione_Nome(request.getNome(), sezione.get().getNome());
            if (p.isEmpty()) {
                Posto posto = new Posto();
               /* posto.setNome(request.getNome());*/
                posto = mapStructPosto.fromCreaPostoDTO(request);
                posto.setSezione(sezione.get());
                postoRepository.save(posto);
                return true;
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "posto già registrato");
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sezione non esistente");
        }
    }

    @Override
    public List<GetAllPostiResponseDTO> getAllPosti() {
        List<Posto> posti = postoRepository.findAll();
        List<GetAllPostiResponseDTO> response = new ArrayList<>();

        posti.forEach(p ->
        {
            if(p.getBiglietto()!=null) {

                response.add(
                        new GetAllPostiResponseDTO(p.getId(), p.getNome(), p.getSezione().getNome(), p.getBiglietto().getRipetizione().getDatainizio(), p.getBiglietto().getRipetizione().getEvento().getNome())
                );
            }
            else
            {
                response.add(
                        new GetAllPostiResponseDTO(p.getId(), p.getNome(), p.getSezione().getNome(), null, null)
                );
            }
        });
        return response;
    }
}
