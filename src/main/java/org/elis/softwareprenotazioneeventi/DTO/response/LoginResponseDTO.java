package org.elis.softwareprenotazioneeventi.DTO.response;

import lombok.Data;

@Data
public class LoginResponseDTO {

    private long id;
    private String email;
    private String ruolo;
    private int anni;

}
