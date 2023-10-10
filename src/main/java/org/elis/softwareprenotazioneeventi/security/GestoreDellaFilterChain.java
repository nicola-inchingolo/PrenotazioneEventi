package org.elis.softwareprenotazioneeventi.security;


import org.elis.softwareprenotazioneeventi.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class GestoreDellaFilterChain {

    private final FilterJwt filterJwt;

    private final AuthenticationProvider provider;

    public GestoreDellaFilterChain(FilterJwt filterJwt, AuthenticationProvider provider)
    {
        this.filterJwt = filterJwt;
        this.provider = provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/all/**").permitAll()// todo sostituisci url
                        .requestMatchers("/client/**").hasRole(Role.CLIENTE.toString())// todo sostituisci url
                        .requestMatchers("/seller/**").hasRole(Role.VENDITORE.toString())// todo sostituisci url
                        .requestMatchers("/admin/**").hasAnyRole(Role.ADMIN.toString(), Role.SUPERADMIN.toString())// todo sostituisci url
                        .anyRequest().permitAll()
                ).sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(AbstractHttpConfigurer::disable)
                .authenticationProvider(provider)
                .addFilterBefore(filterJwt, UsernamePasswordAuthenticationFilter.class);
                return httpSecurity.build();
    }
}
