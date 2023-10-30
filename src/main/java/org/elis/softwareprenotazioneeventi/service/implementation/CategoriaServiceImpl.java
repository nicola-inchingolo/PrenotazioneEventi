package org.elis.softwareprenotazioneeventi.service.implementation;

import org.elis.softwareprenotazioneeventi.DTO.response.GetAllCategoriaResponseDTO;
import org.elis.softwareprenotazioneeventi.Mapper.CategoriaMapper;
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



    public CategoriaServiceImpl(CategoriaRepository c, CategoriaMapper m)
    {
        categoriaRepository = c;
    }


    @Override
    public boolean creaCategoria(String nome) {
        Optional<Categoria> c = categoriaRepository.findByNome(nome);
        if(c.isEmpty())
        {
            Categoria categoria = new Categoria();
            categoria.setNome(nome);
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
            response.add(new GetAllCategoriaResponseDTO(c.getId(),c.getNome())
            );

        });

        return  response;
    }


}
