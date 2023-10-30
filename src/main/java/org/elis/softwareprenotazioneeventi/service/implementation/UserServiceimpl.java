package org.elis.softwareprenotazioneeventi.service.implementation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elis.softwareprenotazioneeventi.DTO.request.FiltroUser;
import org.elis.softwareprenotazioneeventi.DTO.request.LoginRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaPasswordRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.RegistrazioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.*;
import org.elis.softwareprenotazioneeventi.Mapper.UserMapper;
import org.elis.softwareprenotazioneeventi.exception.gestori.UserNotFoundException;
import org.elis.softwareprenotazioneeventi.model.Role;
import org.elis.softwareprenotazioneeventi.model.User;
import org.elis.softwareprenotazioneeventi.repository.CriteriaUserRepository;
import org.elis.softwareprenotazioneeventi.repository.UserRepository;
import org.elis.softwareprenotazioneeventi.service.definition.UserService;
import org.elis.softwareprenotazioneeventi.utils.PasswordValidator;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@CacheConfig(cacheNames = {"users"})
public class UserServiceimpl implements UserService {

    private UserRepository userRepository;
    private CriteriaUserRepository criteriaUserRepository;

    private final Validator validator;
    private final static Logger logger = LogManager.getLogger(UserServiceimpl.class.getName());

    private final UserMapper userMapper;
    public UserServiceimpl(UserRepository repository, Validator v, CriteriaUserRepository c, UserMapper u)
    {
        userRepository = repository;
        validator = v;
        criteriaUserRepository = c;
        userMapper = u;
    }


    @Override
    @Caching(evict = @CacheEvict(cacheNames = "clienti"))
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
                User u = userMapper.toRegistrazioneRequestDTO(request);
               /* u.setEmail(request.getEmail());
                u.setCognome(request.getCognome());
                u.setNome(request.getNome());*/
                u.setRuolo(Role.CLIENTE);
                /*u.setDataNascita(request.getDataDiNascita());
                u.setCodiceFiscale(request.getCodiceFiscale());
                u.setPassword(request.getPassword());*/
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
    @Caching( evict = @CacheEvict(cacheNames = "venditori"))
    public boolean registrazioneVenditore(RegistrazioneRequestDTO request) {

                Optional<User> user = userRepository.findUserByEmailAndPassword(request.getEmail(), request.getPassword());
                if (user.isEmpty()) {
                    /*if (EmailValidator.isValidEmail(request.getEmail())) {
                        if (PasswordValidator.isValidPassword(request.getPassword())) {
                            if (request.getDataDiNascita().isBefore(LocalDate.now())) {
                                if(CodiceFicaleValidator.isCodiceFiscaleValid(request.getCodiceFiscale())) {*/
                    if(request.getPassword().equals(request.getPasswordRipetuta())) {
                        User u = userMapper.toRegistrazioneRequestDTO(request);
                        /*u.setEmail(request.getEmail());
                        u.setCognome(request.getCognome());
                        u.setNome(request.getNome());*/
                        u.setRuolo(Role.VENDITORE);
                        /*u.setDataNascita(request.getDataDiNascita());
                        u.setCodiceFiscale(request.getCodiceFiscale());
                        u.setPassword(request.getPassword());*/
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

    @Override
    @Caching( evict = @CacheEvict(cacheNames = "admin"))
    public boolean registrazioneAdmin(RegistrazioneRequestDTO request) {

                Optional<User> user = userRepository.findUserByEmailAndPassword(request.getEmail(), request.getPassword());
                if (user.isEmpty()) {
                    /*if (EmailValidator.isValidEmail(request.getEmail())) {
                        if (PasswordValidator.isValidPassword(request.getPassword())) {
                            if (request.getDataDiNascita().isBefore(LocalDate.now()))
                            {
                                if(CodiceFicaleValidator.isCodiceFiscaleValid(request.getCodiceFiscale())) {*/

                    if(request.getPassword().equals(request.getPasswordRipetuta()))
                    {
                        User u = userMapper.toRegistrazioneRequestDTO(request);
                        /*u.setEmail(request.getEmail());
                        u.setCognome(request.getCognome());
                        u.setNome(request.getNome());*/
                        u.setRuolo(Role.ADMIN);
                        /*u.setDataNascita(request.getDataDiNascita());
                        u.setCodiceFiscale(request.getCodiceFiscale());
                        u.setPassword(request.getPassword());*/
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

    @Override
    public User login(LoginRequestDTO request) {
         return login(request.getEmail(),request.getPassword());
    }

    @Cacheable(value = "user", key = "#email.concat('-').concat(#password)")
    public User login(String email, String password)
    {
        Optional<User> user = userRepository.findUserByEmailAndPassword(email, password);
        if(user.isPresent()) {
            if(user.get().getAttivo()) {
                return user.orElseThrow(UserNotFoundException::new);
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "l'utente è disattivato");
            }
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email e/o password errati");
        }

    }

    @Cacheable(value = "clienti")
    @Override
    public List<GetAllUsersResponseDTO> findAllClienti() {

        List<User> users = userRepository.findAllByRuolo(Role.CLIENTE);;
        List<GetAllUsersResponseDTO> response =  new ArrayList<>();
        users.forEach(u -> {

            List<GetAllBigliettiResponseDTO> carrello = new ArrayList<>();
            u.getCarrello().forEach(ca->{

                GetAllBigliettiResponseDTO bigliettoCarrello = new GetAllBigliettiResponseDTO(ca.getId(),ca.getPrezzo(),ca.getVenduto(),ca.getUser().getNome(),ca.getPosto().getNome(),ca.getRipetizione().getDatainizio(),ca.getRipetizione().getEvento().getNome());
                carrello.add(bigliettoCarrello);
            });
            List<GetAllBigliettiResponseDTO> acquistati = new ArrayList<>();
            u.getBigliettiAcquistati().forEach(ba->{

                GetAllBigliettiResponseDTO bigliettoAcquistato = new GetAllBigliettiResponseDTO(ba.getId(),ba.getPrezzo(),ba.getVenduto(),ba.getUser().getNome(),ba.getPosto().getNome(),ba.getRipetizione().getDatainizio(),ba.getRipetizione().getEvento().getNome());
                acquistati.add(bigliettoAcquistato);
            });
            List<GetAllRecensioniResponseDTO> recensioni = new ArrayList<>();
            u.getRecensioni().forEach(re->{

                GetAllRecensioniResponseDTO recensione = new GetAllRecensioniResponseDTO(re.getId(), re.getDescrizione(), re.getVotazione(),re.getUser().getNome(),re.getEvento().getNome());
                recensioni.add(recensione);
            });

            response.add(
                    new GetAllUsersResponseDTO(u.getId(),u.getNome(), u.getCognome(), u.getEmail(), u.getPassword(), u.getDataNascita(), u.getCodiceFiscale(), u.getRuolo(), u.getAttivo(), carrello,acquistati, recensioni)
            );
        });

        return response;

    }

    @Cacheable(value = "venditori")
    @Override
    public List<GetAllUsersResponseDTO> findAllVenditori() {
       List<User> users = userRepository.findAllByRuolo(Role.VENDITORE);;
        List<GetAllUsersResponseDTO> response =  new ArrayList<>();
        users.forEach(u -> {

            List<GetAllBigliettiResponseDTO> carrello = new ArrayList<>();
            u.getCarrello().forEach(ca->{

                GetAllBigliettiResponseDTO bigliettoCarrello = new GetAllBigliettiResponseDTO(ca.getId(),ca.getPrezzo(),ca.getVenduto(),ca.getUser().getNome(),ca.getPosto().getNome(),ca.getRipetizione().getDatainizio(),ca.getRipetizione().getEvento().getNome());
                carrello.add(bigliettoCarrello);
            });
            List<GetAllBigliettiResponseDTO> acquistati = new ArrayList<>();
            u.getBigliettiAcquistati().forEach(ba->{

                GetAllBigliettiResponseDTO bigliettoAcquistato = new GetAllBigliettiResponseDTO(ba.getId(),ba.getPrezzo(),ba.getVenduto(),ba.getUser().getNome(),ba.getPosto().getNome(),ba.getRipetizione().getDatainizio(),ba.getRipetizione().getEvento().getNome());
                acquistati.add(bigliettoAcquistato);
            });
            List<GetAllRecensioniResponseDTO> recensioni = new ArrayList<>();
            u.getRecensioni().forEach(re->{

                GetAllRecensioniResponseDTO recensione = new GetAllRecensioniResponseDTO(re.getId(), re.getDescrizione(), re.getVotazione(),re.getUser().getNome(),re.getEvento().getNome());
                recensioni.add(recensione);
            });

            response.add(
                    new GetAllUsersResponseDTO(u.getId(),u.getNome(), u.getCognome(), u.getEmail(), u.getPassword(), u.getDataNascita(), u.getCodiceFiscale(), u.getRuolo(), u.getAttivo(), carrello,acquistati, recensioni)
            );
        });

        return response;

    }

    @Cacheable(value = "admin")
    @Override
    public List<GetAllUsersResponseDTO> findAllAdmin(){

        List<User> users = userRepository.findAllByRuolo(Role.ADMIN);;
        List<GetAllUsersResponseDTO> response =  new ArrayList<>();
        users.forEach(u -> {

            List<GetAllBigliettiResponseDTO> carrello = new ArrayList<>();
            u.getCarrello().forEach(ca->{

                GetAllBigliettiResponseDTO bigliettoCarrello = new GetAllBigliettiResponseDTO(ca.getId(),ca.getPrezzo(),ca.getVenduto(),ca.getUser().getNome(),ca.getPosto().getNome(),ca.getRipetizione().getDatainizio(),ca.getRipetizione().getEvento().getNome());
                carrello.add(bigliettoCarrello);
            });
            List<GetAllBigliettiResponseDTO> acquistati = new ArrayList<>();
            u.getBigliettiAcquistati().forEach(ba->{

                GetAllBigliettiResponseDTO bigliettoAcquistato = new GetAllBigliettiResponseDTO(ba.getId(),ba.getPrezzo(),ba.getVenduto(),ba.getUser().getNome(),ba.getPosto().getNome(),ba.getRipetizione().getDatainizio(),ba.getRipetizione().getEvento().getNome());
                acquistati.add(bigliettoAcquistato);
            });
            List<GetAllRecensioniResponseDTO> recensioni = new ArrayList<>();
            u.getRecensioni().forEach(re->{

                GetAllRecensioniResponseDTO recensione = new GetAllRecensioniResponseDTO(re.getId(), re.getDescrizione(), re.getVotazione(),re.getUser().getNome(),re.getEvento().getNome());
                recensioni.add(recensione);
            });

            response.add(
                    new GetAllUsersResponseDTO(u.getId(),u.getNome(), u.getCognome(), u.getEmail(), u.getPassword(), u.getDataNascita(), u.getCodiceFiscale(), u.getRuolo(), u.getAttivo(), carrello,acquistati, recensioni)
            );
        });

        return response;

    }

    @Override//da rivedere
    public boolean bloccaAccount(long idUser, User userRichiesta) {

       Optional<User> user = userRepository.findById(idUser);
       /*Optional<User> userRichiesta = userRepository.findById(request.getIdUserRichiesta());*/

       if(user.isPresent() && userRichiesta!=null) {

           if (user.get().getAttivo()) {
               if (userRichiesta.getRuolo().equals(Role.SUPERADMIN)) {
                   user.get().setAttivo(false);
                   userRepository.save(user.get());
                   return true;
               } else if (userRichiesta.getRuolo().equals(Role.ADMIN) && (user.get().getRuolo().equals(Role.VENDITORE) || user.get().getRuolo().equals(Role.CLIENTE))) {
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
    public boolean sbloccaAccount(long idUser, User userRichiesta) {

        Optional<User> user = userRepository.findById(idUser);
       /* Optional<User> userRichiesta = userRepository.findById(request.getIdUserRichiesta());*/

        if(user.isPresent() && userRichiesta!=null) {
            if (!user.get().getAttivo()) {
               if (userRichiesta.getRuolo().equals(Role.SUPERADMIN)) {
                    user.get().setAttivo(true);
                    userRepository.save(user.get());
                    return true;
                } else if (userRichiesta.getRuolo().equals(Role.ADMIN) && (user.get().getRuolo().equals(Role.VENDITORE) || user.get().getRuolo().equals(Role.CLIENTE))) {
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

    @Cacheable(value = "users")
    @Override
    public List<GetAllUsersResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<GetAllUsersResponseDTO> response =  new ArrayList<>();
        users.forEach(u -> {

            List<GetAllBigliettiResponseDTO> carrello = new ArrayList<>();

            logger.info(u.getEmail());
            logger.info((u.getCarrello()));
            u.getCarrello().forEach(biglietto->{

                GetAllBigliettiResponseDTO bigliettoCarrello = new GetAllBigliettiResponseDTO(biglietto.getId(),biglietto.getPrezzo(),biglietto.getVenduto(),biglietto.getUser()==null?null:biglietto.getUser().getNome(),biglietto.getPosto().getNome(),biglietto.getRipetizione().getDatainizio(),biglietto.getRipetizione().getEvento().getNome());
                carrello.add(bigliettoCarrello);
            });
            List<GetAllBigliettiResponseDTO> acquistati = new ArrayList<>();
            u.getBigliettiAcquistati().forEach(ba->{

                GetAllBigliettiResponseDTO bigliettoAcquistato = new GetAllBigliettiResponseDTO(ba.getId(),ba.getPrezzo(),ba.getVenduto(),ba.getUser()==null?null:ba.getUser().getNome(),ba.getPosto().getNome(),ba.getRipetizione().getDatainizio(),ba.getRipetizione().getEvento().getNome());
                acquistati.add(bigliettoAcquistato);
            });
            List<GetAllRecensioniResponseDTO> recensioni = new ArrayList<>();
            u.getRecensioni().forEach(re->{

                GetAllRecensioniResponseDTO recensione = new GetAllRecensioniResponseDTO(re.getId(), re.getDescrizione(), re.getVotazione(),re.getUser().getNome(),re.getEvento().getNome());
                recensioni.add(recensione);
            });
            response.add(
                    new GetAllUsersResponseDTO(u.getId(),u.getNome(), u.getCognome(), u.getEmail(), u.getPassword(), u.getDataNascita(), u.getCodiceFiscale(), u.getRuolo(), u.getAttivo(), carrello,acquistati, recensioni)
            );
        });

        return response;
    }

    @Override
    public boolean modificaPassword(ModificaPasswordRequestDTO request) {

        Optional<User> u = userRepository.findById(request.getIdUser());

        if(u.isPresent())
        {
            if(u.get().getAttivo()) {
                User user = u.get();
                if (PasswordValidator.isValidPassword(request.getNewPassword())) {
                    user.setPassword(request.getNewPassword());
                    userRepository.save(user);
                    return true;
                } else {
                    return false;
                }
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "l'utente è stato bloccato");
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "utente non trovato");
        }
    }

    @Cacheable(value = "user", key = "#email", sync = true)
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> getUsersFiltrati(FiltroUser request) {
        return criteriaUserRepository.getUserFiltered(request);
    }
}
