package org.elis.softwareprenotazioneeventi.service.implementation;

import jakarta.transaction.Transactional;
import org.elis.softwareprenotazioneeventi.DTO.request.BigliettoRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaBigliettoDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllBigliettiResponseDTO;
import org.elis.softwareprenotazioneeventi.Mapper.MapStructBiglietto;
import org.elis.softwareprenotazioneeventi.model.*;
import org.elis.softwareprenotazioneeventi.repository.BigliettoRepository;
import org.elis.softwareprenotazioneeventi.repository.PostoRepository;
import org.elis.softwareprenotazioneeventi.repository.RipetizioneRepository;
import org.elis.softwareprenotazioneeventi.repository.UserRepository;
import org.elis.softwareprenotazioneeventi.service.definition.BigliettoService;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class BigliettoServiceImpl  implements BigliettoService {

    private BigliettoRepository bigliettoRepository;
    private UserRepository userRepository;
    private PostoRepository postoRepository;
    private RipetizioneRepository ripetizioneRepository;



    public BigliettoServiceImpl(BigliettoRepository b, UserRepository u, PostoRepository p, RipetizioneRepository r)
    {
        userRepository = u;
        bigliettoRepository = b;
        postoRepository = p;
        ripetizioneRepository = r;
    }
    @Override
    public boolean creaBiglietto(BigliettoRequestDTO request) {

        Optional<Ripetizione> ripetizione = ripetizioneRepository.findById(request.getIdRipetizione());
        Optional<Posto> posto = postoRepository.findById(request.getIdPosto());

        if(posto.isPresent() && ripetizione.isPresent()) {

            Optional<Biglietto> b = bigliettoRepository.findByPosto_NomeAndPosto_Sezione_Id(posto.get().getNome(), posto.get().getSezione().getId());
            if (b.isEmpty()) {
                Biglietto biglietto = new Biglietto();
               /* biglietto.setPosto(posto.get());
                biglietto.setPrezzo(request.getPrezzo());*/
                biglietto = mapStructBiglietto.fromCreaBigliettorequestDTO(request);
                biglietto.setRipetizione(ripetizione.get());
                bigliettoRepository.save(biglietto);
                return true;
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "biglietto già esistente");
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
        }
    }


    @Override
    public boolean aggiungiCarello(long idBiglietto, User user) {
        if(user != null)
        {
            Optional<Biglietto> b = bigliettoRepository.findById(idBiglietto);
            user = userRepository.findById(user.getId()).get();
            if(b.isPresent())
            {
                Biglietto biglietto = b.get();
                if(!biglietto.getVenduto()) {
                    if (biglietto.getUser() == null || (user != biglietto.getUser())  ) {
                        user.getCarrello().add(biglietto);
                        userRepository.save(user);
                        return true;
                    }
                    else
                    {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "l'elemento è già presente nel carrello");
                    }
                }
                else
                {
                   throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "il biglietto è già stato venduto");
                }

            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
        }
    }

    //aggiusta
    @Override
    public boolean confermaAcquisto(long idBiglietto, User user) {
        Optional<Biglietto> b = bigliettoRepository.findById(idBiglietto);
        user = userRepository.findById(user.getId()).get();

        System.out.println(user);
        if(user != null) {

            if (b.isPresent()) {
                Biglietto biglietto = b.get();
                System.out.println(user.getCarrello());
                System.out.println(user.getCarrello().contains(biglietto));
                if (user.getCarrello().contains(biglietto) && !biglietto.getVenduto() )
                {

                    biglietto.setVenduto(true);
                    user.getBigliettiAcquistati().add(biglietto);
                    biglietto.setUser(user);
                    biglietto.getUtenti().forEach(us->{
                                us.getCarrello().remove(biglietto);
                                userRepository.save(us);
                            }
                    );
                    biglietto.setUtenti(null);
                    userRepository.save(user);
                    bigliettoRepository.save(biglietto);
                    return true;
                }
                else
                {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "il biglietto è già venduto");
                }
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
            }
            }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
        }
    }


    @Override
    public boolean rimuoviCarello(long idBiglietto, User user) {

        Optional<Biglietto> b = bigliettoRepository.findById(idBiglietto);
        if (user != null) {

            if (b.isPresent()) {
                Biglietto biglietto = b.get();
                if(biglietto.getUtenti().contains(user) && user.getCarrello().contains(biglietto)) {
                    user.getCarrello().remove(biglietto);
                    biglietto.getUtenti().remove(user);
                    userRepository.save(user);
                    bigliettoRepository.save(biglietto);
                    return true;
                }
                else
                {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "il biglietto non è presente nel carrello dell'utente");
                }
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
            }
        }
        else
        {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
        }
    }

    @Override
    public boolean annullaAcquisto(long idBiglietto, User user) {

        Optional<Biglietto> b = bigliettoRepository.findById(idBiglietto);
        if (user != null) {

            if (b.isPresent()) {
                Biglietto biglietto = b.get();
                if (biglietto.getUser().getEmail().equals(user.getEmail()) && biglietto.getVenduto()) {
                    if((ChronoUnit.DAYS.between(LocalDate.now(),biglietto.getRipetizione().getDatainizio() )) > 20) {
                        biglietto.setVenduto(false);
                        user.getBigliettiAcquistati().remove(biglietto);
                        biglietto.setUser(null);
                        bigliettoRepository.save(biglietto);
                        userRepository.save(user);
                        return true;
                    }
                    else
                    {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mancano " + (ChronoUnit.DAYS.between(LocalDate.now(), biglietto.getRipetizione().getDatainizio()))+ " giorni all'evento, troppo tardi ");                    }
                }
                else
                {
                    throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"non ci sono le condizioni per annullare l'acquisto");
                }
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato storto");
        }
    }

    @Override
    public Map<String, Integer> bigliettiVenduti() {
        List<Object[]> results = bigliettoRepository.bigliettiVenduti();

        Map<String, Integer> resultMap = new HashMap<>();

        for (Object[] result : results) {
            String eventoNome = (String) result[0];
            Long count = (Long) result[1];

            // Se eventoNome non è nullo, inserisci nella mappa
            if (eventoNome != null) {
                resultMap.put(eventoNome, count != null ? count.intValue() : 0);
            }
        }

        return resultMap;
    }

    @Override
    public boolean modificaPrezzoBiglietto(ModificaBigliettoDTO request) {
        Optional<Biglietto> b = bigliettoRepository.findById(request.getIdBiglietto());
        if(b.isPresent())
        {
            Biglietto biglietto = b.get();
            if(!biglietto.getVenduto())
            {
                biglietto.setPrezzo(request.getNewPrezzo());
                bigliettoRepository.save(biglietto);
                return true;
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "il biglietto è stato già venduto");
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qualcosa è andato di storto");
        }
    }

    @Override
    public List<GetAllBigliettiResponseDTO> getAllBiglietti() {
        List<Biglietto> biglietti = bigliettoRepository.findAll();
        List<GetAllBigliettiResponseDTO> response =  new ArrayList<>();
        biglietti.forEach(b -> {
            if(b.getUser()!=null) {
                response.add(
                        new GetAllBigliettiResponseDTO(b.getId(), b.getPrezzo(), b.getVenduto(), b.getUser().getNome(), b.getPosto().getNome(), b.getRipetizione().getDatainizio(), b.getRipetizione().getEvento().getNome())

                );
            }else
            {
                response.add(
                        new GetAllBigliettiResponseDTO(b.getId(), b.getPrezzo(), b.getVenduto(), null, b.getPosto().getNome(), b.getRipetizione().getDatainizio(), b.getRipetizione().getEvento().getNome())

                );
            }
        });

        return response;
    }

    @Override
    public List<GetAllBigliettiResponseDTO> getStoricoBiglietti(User user) {

        List<Biglietto> biglietti = bigliettoRepository.findAllByUser_Id(user.getId());
        List<GetAllBigliettiResponseDTO> response = new ArrayList<>();
        biglietti.forEach( b-> {
                response.add(new GetAllBigliettiResponseDTO(b.getId(),b.getPrezzo(),b.getVenduto(),b.getUser().getNome(),b.getPosto().getNome(),b.getRipetizione().getDatainizio(), b.getRipetizione().getEvento().getNome()));

                }
        );

        return  response;
    }


}
