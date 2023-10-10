package org.elis.softwareprenotazioneeventi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.elis.softwareprenotazioneeventi.model.Role;
import org.elis.softwareprenotazioneeventi.model.User;
import org.elis.softwareprenotazioneeventi.service.definition.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class TokenUtil {

    private final UserService service;

    @Value("${miocodice.jwt.key}")
    private String key;

    public TokenUtil(UserService s)
    {
        service = s;
    }

    public SecretKey generaChiave() { return Keys.hmacShaKeyFor(key.getBytes());}

    public String generaToken(User u)
    {
        String ruolo = u.getRuolo().toString();
        String username = u.getEmail();
        String dataNascita = u.getDataNascita().format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy"));
        String saluto = "ciao, hai letto i miei dati, e adesso ?";

        long millisecondiDiDurata= 1000L*60*60*24*60;
        return Jwts.builder().claims()
                .add("ruolo", ruolo)
                .add("dataNascita", dataNascita)
                .add("saluto", saluto)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+millisecondiDiDurata))
                .and()
                .signWith(generaChiave())
                .compact();
    }

    private Claims prendiClaimsDalToken(String token)
    {
        JwtParser parser = Jwts.parser()
                .verifyWith(generaChiave())
                .build();
        return parser.parseSignedClaims(token)
                .getPayload();
    }

    public String getSubject(String token) {return prendiClaimsDalToken(token).getSubject();}

    public User getUserFromToken(String token)
    {
        String username = getSubject(token);
        return service.findByEmail(username);
    }

    public LocalDate getDataNascita(String token)
    {
        String data = prendiClaimsDalToken(token).get("dataNascita",String.class);
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy"));
    }

    public Role getRuolo(String token) {return prendiClaimsDalToken(token).get("ruolo", Role.class);}

}
