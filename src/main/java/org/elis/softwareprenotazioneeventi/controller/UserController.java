package org.elis.softwareprenotazioneeventi.controller;

import jakarta.validation.Valid;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @GetMapping("/all/login")//tutti
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

    @GetMapping("/admin/prova")//solo admin
    public ResponseEntity<String> sonoUnAdmin(UsernamePasswordAuthenticationToken upat){
        User u= (User) upat.getPrincipal();
        GetAllUsersResponseDTO user1 = new GetAllUsersResponseDTO();
        user1.setCognome(u.getCognome());
        user1.setNome(u.getNome());
        return ResponseEntity.status(HttpStatus.OK).body("ciao sono "+user1.getNome()+" "+user1.getCognome());
    }

    @PostMapping("/all/registraCliente")//tutti
    public ResponseEntity<Void> registrazioneCliente(@RequestBody RegistrazioneRequestDTO request){
        boolean registrato= service.registrazioneCliente(request);
        return ResponseEntity.ok().build();
        //else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/admin/registraVenditore")//admin o superadmin
    public ResponseEntity<Void> registrazioneVenditore( @Valid @RequestBody RegistrazioneRequestDTO request, UsernamePasswordAuthenticationToken upat)
    {
        boolean registato = service.registrazioneVenditore(request);
        return ResponseEntity.ok().build();
        //else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/superadmin/registraAdmin")//superadmin
    public ResponseEntity<Void> registrazioneAdmin( @Valid @RequestBody RegistrazioneRequestDTO request, UsernamePasswordAuthenticationToken upat){
        boolean registrato= service.registrazioneAdmin(request);
        return ResponseEntity.ok().build();
        //else return ResponseEntity.badRequest().build();
    }

    @PutMapping("/admin/bloccaAccount")//admin o superadmin
    public ResponseEntity<Void> bloccaAccount(@Valid @RequestParam long idUser, UsernamePasswordAuthenticationToken upat)
    {
        boolean bloccato = service.bloccaAccount(idUser);
        return ResponseEntity.ok().build();
        //else return ResponseEntity.badRequest().build();
    }

    @PutMapping("/admin/sbloccaAccount")//admin o superadmin
    public ResponseEntity<Void> sbloccaAccount(@Valid @RequestParam long idUser,  UsernamePasswordAuthenticationToken upat)
    {
        boolean sbloccato = service.sbloccaAccount(idUser);
        return ResponseEntity.ok().build();
        //else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/admin/getAllUsers")//admin o superadmin
    public ResponseEntity<List<GetAllUsersResponseDTO>> getAllUsers()
    {
        return ResponseEntity.ok().body(service.getAllUsers());
    }

    @PatchMapping("/all/modificaPassword")// tutti
    public ResponseEntity<Void> modificaPassword(@Valid @RequestBody ModificaPasswordRequestDTO request)
    {
        boolean modificato = service.modificaPassword(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/venditore/getAllClienti")//venditore
    public ResponseEntity<List<GetAllUsersResponseDTO>> getAllClienti()
    {
        return ResponseEntity.ok().body(service.findAllClienti());
    }

    @GetMapping("/admin/getAllVenditori")//admin o superadmin
    public ResponseEntity<List<GetAllUsersResponseDTO>> getAllVenditori()
    {
        return ResponseEntity.ok().body(service.findAllVenditori());
    }

    @GetMapping("/superadmin/getAllAdmin")//superadmin
    public ResponseEntity<List<GetAllUsersResponseDTO>> getAllAdmin()
    {
        return ResponseEntity.ok().body(service.findAllAdmin());
    }
}
