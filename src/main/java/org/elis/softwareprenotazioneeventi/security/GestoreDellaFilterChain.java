package org.elis.softwareprenotazioneeventi.security;


import org.elis.softwareprenotazioneeventi.model.Role;
import org.elis.softwareprenotazioneeventi.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class GestoreDellaFilterChain {

    private final FilterJwt filterJwt;

    private final AuthenticationProvider provider;

    public GestoreDellaFilterChain(FilterJwt filterJwt, AuthenticationProvider provider)
    {
        this.filterJwt = filterJwt;
        this.provider = provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {
//abilita h2
        MvcRequestMatcher.Builder builder=new MvcRequestMatcher.Builder(introspector);

        // qunadso vuole divider la chiamata in frame permettilo
        //authorize httprequest autorizza le chiamata in base a quello che lo sviluppatore prevede
        //chiamata cors si mette per convenzione ma non funziona
        //setto un authentication provider
        //passa per un filtro --> username and password filter controllerà i path
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .headers(cust->cust.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/all/**").permitAll()
                        .requestMatchers("/cliente/**").hasRole(Role.CLIENTE.toString())
                        .requestMatchers("/venditore/**").hasAnyRole(Role.VENDITORE.toString(),Role.ADMIN.toString(),Role.SUPERADMIN.toString())
                        .requestMatchers("/admin/**").hasAnyRole(Role.ADMIN.toString(),Role.SUPERADMIN.toString())
                        .requestMatchers("/superadmin/**").hasRole(Role.SUPERADMIN.toString())
                        .anyRequest().permitAll()
                ).sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(AbstractHttpConfigurer::disable)
                .authenticationProvider(provider)
                .addFilterBefore(filterJwt, UsernamePasswordAuthenticationFilter.class);
                return httpSecurity.build();
    }
}
