package org.elis.softwareprenotazioneeventi.DTO.request;

import lombok.Data;

import java.util.Map;

@Data
public class FiltroAvanzato {

    private Map<String, String> filtroEvento;
    private Map<String, String> filtroCategoria;


}
