package org.elis.softwareprenotazioneeventi.service.definition;

import org.elis.softwareprenotazioneeventi.DTO.request.LoginRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaPasswordRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.RegistrazioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllUsersResponseDTO;
import org.elis.softwareprenotazioneeventi.model.User;

import java.util.List;

public interface UserService {

    public User login(LoginRequestDTO request);
    public boolean registrazioneCliente(RegistrazioneRequestDTO request);
    public boolean registrazioneVenditore(RegistrazioneRequestDTO request);
    public boolean registrazioneAdmin(RegistrazioneRequestDTO request);
    public List<GetAllUsersResponseDTO> findAllClienti();
    public List<GetAllUsersResponseDTO> findAllVenditori();
    public List<GetAllUsersResponseDTO> findAllAdmin();
    public boolean bloccaAccount(long idUser, User userRichiesta);
    public boolean sbloccaAccount(long idUser, User userRichiesta);
    public List<GetAllUsersResponseDTO> getAllUsers();
    public boolean modificaPassword(ModificaPasswordRequestDTO request);

    public User findByEmail(String email);


}
