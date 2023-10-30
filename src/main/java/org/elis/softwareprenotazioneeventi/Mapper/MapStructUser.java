package org.elis.softwareprenotazioneeventi.Mapper;

import org.elis.softwareprenotazioneeventi.DTO.request.LoginRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.RegistrazioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllUsersResponseDTO;
import org.elis.softwareprenotazioneeventi.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface MapStructUser {


    GetAllUsersResponseDTO toGetAllUserResponspeDTO(User user);
    User fromRegistrazionerequestDTO(RegistrazioneRequestDTO requestDTO);




}
