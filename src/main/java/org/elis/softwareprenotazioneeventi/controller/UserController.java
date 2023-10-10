package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
import org.elis.softwareprenotazioneeventi.DTO.request.AccountRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.LoginRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.ModificaPasswordRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.request.RegistrazioneRequestDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.GetAllUsersResponseDTO;
import org.elis.softwareprenotazioneeventi.DTO.response.LoginResponseDTO;
import org.elis.softwareprenotazioneeventi.model.User;
import org.elis.softwareprenotazioneeventi.security.TokenUtil;
import org.elis.softwareprenotazioneeventi.service.definition.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class UserController {


    private final UserService service;
    private final TokenUtil util;

    public UserController(UserService s, TokenUtil t)
    {
        service = s;
        util = t;
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponseDTO> login( @Valid @RequestBody LoginRequestDTO request){

        User u= service.login(request);
        String token = util.generaToken(u);
        LoginResponseDTO l = new LoginResponseDTO();
        l.setEmail(u.getEmail());
        l.setId(u.getId());
        l.setRuolo(u.getRuolo().name());
        l.setAnni((int) ChronoUnit.YEARS.between(u.getDataNascita(), LocalDate.now()));
        return ResponseEntity.status(HttpStatus.OK).header("Authorization" , token).body(l);
    }

    @PostMapping("/registraCliente")
    public ResponseEntity<Void> registrazioneCliente(@RequestBody RegistrazioneRequestDTO request){
        boolean registrato= service.registrazioneCliente(request);
        return ResponseEntity.ok().build();
        //else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/registraVenditore")
    public ResponseEntity<Void> registrazioneVenditore( @Valid @RequestBody RegistrazioneRequestDTO request)
    {
        boolean registato = service.registrazioneVenditore(request);
        return ResponseEntity.ok().build();
        //else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/registraAdmin")
    public ResponseEntity<Void> registrazioneAdmin( @Valid @RequestBody RegistrazioneRequestDTO request){
        boolean registrato= service.registrazioneAdmin(request);
        return ResponseEntity.ok().build();
        //else return ResponseEntity.badRequest().build();
    }

    @PutMapping("/bloccaAccount")
    public ResponseEntity<Void> bloccaAccount(@Valid @RequestBody AccountRequestDTO request)
    {
        boolean bloccato = service.bloccaAccount(request);
        return ResponseEntity.ok().build();
        //else return ResponseEntity.badRequest().build();
    }

    @PutMapping("/sbloccaAccount")
    public ResponseEntity<Void> sbloccaAccount(@Valid @RequestBody AccountRequestDTO request)
    {
        boolean sbloccato = service.sbloccaAccount(request);
        return ResponseEntity.ok().build();
        //else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<GetAllUsersResponseDTO>> getAllUsers()
    {
        return ResponseEntity.ok().body(service.getAllUsers());
    }

    @PatchMapping("/modificaPassword")
    public ResponseEntity<Void> modificaPassword(@Valid @RequestBody ModificaPasswordRequestDTO request)
    {
        boolean modificato = service.modificaPassword(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllClienti")
    public ResponseEntity<List<GetAllUsersResponseDTO>> getAllClienti()
    {
        return ResponseEntity.ok().body(service.findAllClienti());
    }

    @GetMapping("/getAllVenditori")
    public ResponseEntity<List<GetAllUsersResponseDTO>> getAllVenditori()
    {
        return ResponseEntity.ok().body(service.findAllVenditori());
    }

    @GetMapping("/getAllAdmin")
    public ResponseEntity<List<GetAllUsersResponseDTO>> getAllAdmin()
    {
        return ResponseEntity.ok().body(service.findAllAdmin());
    }
}
