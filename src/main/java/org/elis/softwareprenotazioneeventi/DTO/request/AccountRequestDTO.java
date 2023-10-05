package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.elis.softwareprenotazioneeventi.model.User;

@Data
public class AccountRequestDTO {

    @NotNull()
    private long idUser;

    @NotNull
    private long idUserRichiesta;


}
