package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "email obbligatoria")
    private String email;
    @NotBlank(message = "password obbligatoria")
    private String password;
}
