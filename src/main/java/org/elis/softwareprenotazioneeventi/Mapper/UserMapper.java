package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.RegistrazioneRequestDTO;
import org.elis.softwareprenotazioneeventi.model.User;
import org.hibernate.mapping.UserDefinedType;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toRegistrazioneRequestDTO(RegistrazioneRequestDTO request)
    {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setCognome(request.getCognome());
        user.setNome(request.getNome());
        user.setDataNascita(request.getDataDiNascita());
        user.setCodiceFiscale(request.getCodiceFiscale());
        user.setPassword(request.getPassword());

        return user;
    }



}
