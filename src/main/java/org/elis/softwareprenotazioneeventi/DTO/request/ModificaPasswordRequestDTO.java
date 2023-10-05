package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModificaPasswordRequestDTO {

    @NotNull
    private long idUser;
    @NotBlank
    private String newPassword;


}
