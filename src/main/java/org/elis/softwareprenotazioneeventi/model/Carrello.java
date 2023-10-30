package org.elis.softwareprenotazioneeventi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Carrello {

    private long id;

    private  User user;

    private Biglietto biglietto;




}
