package org.elis.softwareprenotazioneeventi.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModificaBigliettoDTO {

   @NotNull
   private long idBiglietto;

   @NotBlank
   private double newPrezzo;
}
