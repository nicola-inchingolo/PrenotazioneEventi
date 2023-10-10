package org.elis.softwareprenotazioneeventi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.elis.softwareprenotazioneeventi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

public class FilterJwt extends OncePerRequestFilter {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String authCode = request.getHeader("Authorization");
        if(authCode!=null && authCode.startsWith("Bearer")){
            String token = authCode.substring(7); //Bearer " viene tolto
            User u = tokenUtil.getUserFromToken(token);
            UsernamePasswordAuthenticationToken upat =
                    new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
            upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(upat);
        }
        filterChain.doFilter(request,response);






    }
















}
