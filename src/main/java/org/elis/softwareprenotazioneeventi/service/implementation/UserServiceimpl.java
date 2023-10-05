package org.elis.softwareprenotazioneeventi.service.implementation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.elis.softwareprenotazioneeventi.DTO.request.AccountRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.LoginRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaPasswordRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.RegistrazioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllEventsResponseDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllUsersResponseDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.LoginResponseDTO;
import org.elis.softwareprenotazioneeventi.model.Role;
import org.elis.softwareprenotazioneeventi.model.User;
import org.elis.softwareprenotazioneeventi.repository.UserRepository;
import org.elis.softwareprenotazioneeventi.service.definition.UserService;
import org.elis.softwareprenotazioneeventi.utils.CodiceFicaleValidator;
import org.elis.softwareprenotazioneeventi.utils.EmailValidator;
import org.elis.softwareprenotazioneeventi.utils.PasswordValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceimpl implements UserService {

    private UserRepository userRepository;

    private final Validator validator;

    public UserServiceimpl(UserRepository repository, Validator v)
    {
        userRepository = repository;
        validator = v;
    }

    @Override
    public boolean registrazioneCliente(RegistrazioneRequestDTO request) {
        Set<ConstraintViolation<RegistrazioneRequestDTO>> violation=validator.validate(request);
        if(!violation.isEmpty()){
            if(violation.size()>1)throw new ConstraintViolationException(violation);
            //viene utilizzato per regisdtrare un cliente, venditore o admin in base all'id del richiedente
            //quindi, se l'idRichiedente è nullo allora registrerà un cliente, se corrisponde ad un admin, registrerà un venditore, se corrisponde ad un superadmin allora registrerà un admin
            for(ConstraintViolation<RegistrazioneRequestDTO> r:violation){
                if(!r.getPropertyPath().toString().equals("idRichiedente")){
                    throw new ConstraintViolationException(violation);
                }
            }
        }
        Optional<User> user = userRepository.findUserByEmailAndPassword(request.getEmail(), request.getPassword());
        if(user.isEmpty())
        {
            /*if(EmailValidator.isValidEmail(request.getEmail())) {
                if(PasswordValidator.isValidPassword(request.getPassword())) {
                    if(request.getDataDiNascita().isBefore(LocalDate.now())) {
                        if(CodiceFicaleValidator.isCodiceFiscaleValid(request.getCodiceFiscale())) {*/
            if(request.getPassword().equals(request.getPasswordRipetuta())) {
                User u = new User();
                u.setEmail(request.getEmail());
                u.setCognome(request.getCognome());
                u.setNome(request.getNome());
                u.setRuolo(Role.CLIENTE);
                u.setDataNascita(request.getDataDiNascita());
                u.setCodiceFiscale(request.getCodiceFiscale());
                u.setPassword(request.getPassword());
                userRepository.save(u);
                return true;
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password e passwordRipetuta devono essere uguali");
            }
                        }
                       /* else
                        {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "codice fscale non valida ");
                        }
                    }
                    else
                    {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "data non valida");
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email non valida");
            }

        }*/
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email già registrata");
        }
    }

    @Override
    public boolean registrazioneVenditore(RegistrazioneRequestDTO request) {

        Optional<User> uR = userRepository.findById(request.getIdUserRichiesta());

        if(uR.isPresent()) {
            User userRichiesta = uR.get();

            if (userRichiesta.getRuolo().equals(Role.ADMIN) || userRichiesta.getRuolo().equals(Role.SUPERADMIN)) {
                Optional<User> user = userRepository.findUserByEmailAndPassword(request.getEmail(), request.getPassword());
                if (user.isEmpty()) {
                    /*if (EmailValidator.isValidEmail(request.getEmail())) {
                        if (PasswordValidator.isValidPassword(request.getPassword())) {
                            if (request.getDataDiNascita().isBefore(LocalDate.now())) {
                                if(CodiceFicaleValidator.isCodiceFiscaleValid(request.getCodiceFiscale())) {*/
                    if(request.getPassword().equals(request.getPasswordRipetuta())) {
                        User u = new User();
                        u.setEmail(request.getEmail());
                        u.setCognome(request.getCognome());
                        u.setNome(request.getNome());
                        u.setRuolo(Role.VENDITORE);
                        u.setDataNascita(request.getDataDiNascita());
                        u.setCodiceFiscale(request.getCodiceFiscale());
                        u.setPassword(request.getPassword());
                        userRepository.save(u);
                        return true;
                    }
                    else
                    {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la password e padswordRipetuta devono essere uguali");
                    }
                                }
                                /*else {
                                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "codice fscale non valida ");
                                }
                            } else {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "data non valida");
                            }
                        } else {
                            return false;
                        }
                    } else {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email non valida");
                    }
                }*/
                else { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email già registrata");}
            }
            else {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "l'utente non è autorizzato a svolgere questa azione");}
        }
        else {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");}
    }

    @Override
    public boolean registrazioneAdmin(RegistrazioneRequestDTO request) {


        Optional<User> uR = userRepository.findById(request.getIdUserRichiesta());
        if(uR.isPresent()) {
            User userRichiesta = uR.get();

            if (userRichiesta.getRuolo().equals(Role.SUPERADMIN)) {
                Optional<User> user = userRepository.findUserByEmailAndPassword(request.getEmail(), request.getPassword());
                if (user.isEmpty()) {
                    /*if (EmailValidator.isValidEmail(request.getEmail())) {
                        if (PasswordValidator.isValidPassword(request.getPassword())) {
                            if (request.getDataDiNascita().isBefore(LocalDate.now()))
                            {
                                if(CodiceFicaleValidator.isCodiceFiscaleValid(request.getCodiceFiscale())) {*/

                    if(request.getPassword().equals(request.getPasswordRipetuta()))
                    {
                        User u = new User();
                        u.setEmail(request.getEmail());
                        u.setCognome(request.getCognome());
                        u.setNome(request.getNome());
                        u.setRuolo(Role.ADMIN);
                        u.setDataNascita(request.getDataDiNascita());
                        u.setCodiceFiscale(request.getCodiceFiscale());
                        u.setPassword(request.getPassword());
                        userRepository.save(u);
                        return true;
                    }
                    else
                    {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la password e la passwordRipetuta devono essere uguali");
                    }
                   /* else
                    {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "codice fscale non valida ");
                    }
                } else
                            {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "data non valida");
                            }
                        } else
                        {
                            return false;
                        }
                    } else
                    {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email non valida");
                    }*/
                }
                else { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email già registrata");}
            }
            else {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "l'utente non è autorizzato a svolgere questa azione");}
        }
        else {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");}

    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {

        Optional<User> user = userRepository.findUserByEmailAndPassword(request.getEmail(), request.getPassword());
        if(user.isPresent()) {
            if(user.get().getAttivo()) {
                LoginResponseDTO l = new LoginResponseDTO();
                l.setEmail(user.get().getEmail());
                l.setId(user.get().getId());
                l.setRuolo(user.get().getRuolo().name());
                l.setAnni((int) ChronoUnit.YEARS.between(user.get().getDataNascita(), LocalDate.now()));
                return l;
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "l'utente è disattivato");
            }
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email e/o password errati");
        }
    }

    @Override
    public List<GetAllUsersResponseDTO> findAllClienti() {
        List<User> users = userRepository.findAllByRuolo(Role.CLIENTE);;
        List<GetAllUsersResponseDTO> response =  new ArrayList<>();
        users.forEach(u -> {
            response.add(
                    new GetAllUsersResponseDTO(u.getId(),u.getNome(), u.getCognome(), u.getEmail(), u.getPassword(), u.getDataNascita(), u.getCodiceFiscale(), u.getRuolo(), u.getAttivo(), u.getCarrello(),u.getBigliettiAcquistati(), u.getRecensioni())
            );
        });

        return response;
    }

    @Override
    public List<GetAllUsersResponseDTO> findAllVenditori() {
        List<User> users = userRepository.findAllByRuolo(Role.VENDITORE);;
        List<GetAllUsersResponseDTO> response =  new ArrayList<>();
        users.forEach(u -> {
            response.add(
                    new GetAllUsersResponseDTO(u.getId(),u.getNome(), u.getCognome(), u.getEmail(), u.getPassword(), u.getDataNascita(), u.getCodiceFiscale(), u.getRuolo(), u.getAttivo(), u.getCarrello(),u.getBigliettiAcquistati(), u.getRecensioni())
            );
        });

        return response;
    }

    @Override
    public List<GetAllUsersResponseDTO> findAllAdmin(){

        List<User> users = userRepository.findAllByRuolo(Role.ADMIN);;
        List<GetAllUsersResponseDTO> response =  new ArrayList<>();
        users.forEach(u -> {
            response.add(
                    new GetAllUsersResponseDTO(u.getId(),u.getNome(), u.getCognome(), u.getEmail(), u.getPassword(), u.getDataNascita(), u.getCodiceFiscale(), u.getRuolo(), u.getAttivo(), u.getCarrello(),u.getBigliettiAcquistati(), u.getRecensioni())
            );
        });

        return response;
    }

    @Override
    public boolean bloccaAccount(AccountRequestDTO request) {

       Optional<User> user = userRepository.findById(request.getIdUser());
       Optional<User> userRichiesta = userRepository.findById(request.getIdUserRichiesta());


       if(user.isPresent() && userRichiesta.isPresent()) {

           if (user.get().getAttivo()) {
               if (userRichiesta.get().getRuolo().equals(Role.SUPERADMIN)) {
                   user.get().setAttivo(false);
                   userRepository.save(user.get());
                   return true;
               } else if (userRichiesta.get().getRuolo().equals(Role.ADMIN) && (user.get().getRuolo().equals(Role.VENDITORE) || user.get().getRuolo().equals(Role.CLIENTE))) {
                   user.get().setAttivo(false);
                   userRepository.save(user.get());
                   return true;
               }
               else { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "l'utente non è autorizzato a svolgere quest'azione");}
           }
           else {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "l'utente è già disattivo");
           }
       }
       else
       {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
       }

    }

    @Override
    public boolean sbloccaAccount(AccountRequestDTO request) {

        Optional<User> user = userRepository.findById(request.getIdUser());
        Optional<User> userRichiesta = userRepository.findById(request.getIdUserRichiesta());

        if(user.isPresent() && userRichiesta.isPresent()) {
            if (!user.get().getAttivo()) {
                if (userRichiesta.get().getRuolo().equals(Role.SUPERADMIN)) {
                    user.get().setAttivo(true);
                    userRepository.save(user.get());
                    return true;
                } else if (userRichiesta.get().getRuolo().equals(Role.ADMIN) && (user.get().getRuolo().equals(Role.VENDITORE) || user.get().getRuolo().equals(Role.CLIENTE))) {
                    user.get().setAttivo(true);
                    userRepository.save(user.get());
                    return true;
                }
                else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "l'utente non è autorizzato a svolgere quest'azione");
                }
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "l'utente è già attivo");
            }
        }
        else
        {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
        }
    }

    @Override
    public List<GetAllUsersResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<GetAllUsersResponseDTO> response =  new ArrayList<>();
        users.forEach(u -> {
            response.add(
                    new GetAllUsersResponseDTO(u.getId(),u.getNome(), u.getCognome(), u.getEmail(), u.getPassword(), u.getDataNascita(), u.getCodiceFiscale(), u.getRuolo(), u.getAttivo(), u.getCarrello(),u.getBigliettiAcquistati(), u.getRecensioni())
            );
        });

        return response;
    }

    @Override
    public boolean modificaPassword(ModificaPasswordRequestDTO request) {

        Optional<User> u = userRepository.findById(request.getIdUser());

        if(u.isPresent())
        {
            User user = u.get();
            if(PasswordValidator.isValidPassword(request.getNewPassword())) {
                user.setPassword(request.getNewPassword());
                userRepository.save(user);
                return true;
            }
            else {
                return false;
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "utente non trovato");
        }
    }
}
