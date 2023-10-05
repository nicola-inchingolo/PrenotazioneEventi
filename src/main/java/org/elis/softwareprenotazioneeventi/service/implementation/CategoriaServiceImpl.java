package org.elis.softwareprenotazioneeventi.service.implementation;

import org.elis.softwareprenotazioneeventi.DTO.request.CreaCategoriaRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllBigliettiResponseDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllCategoriaResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Categoria;
import org.elis.softwareprenotazioneeventi.repository.CategoriaRepository;
import org.elis.softwareprenotazioneeventi.service.definition.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository c)
    {
        categoriaRepository = c;
    }


    @Override
    public boolean creaCategoria(CreaCategoriaRequestDTO request) {
        Optional<Categoria> c = categoriaRepository.findByNome(request.getNome());
        if(c.isEmpty())
        {
            Categoria categoria = new Categoria();
            categoria.setNome(request.getNome());
            categoriaRepository.save(categoria);
            return true;
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "categoria già esistente");
        }
    }

    @Override
    public List<GetAllCategoriaResponseDTO> getAllCategorie() {
        List<Categoria> categorie = categoriaRepository.findAll();
        List<GetAllCategoriaResponseDTO> response = new ArrayList<>();

        categorie.forEach(c->
        {
            response.add(new GetAllCategoriaResponseDTO(c.getId(),c.getNome(),c.getEventi())

            );

        });

        return  response;
    }


}
