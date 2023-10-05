package org.elis.softwareprenotazioneeventi.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodiceFicaleValidator {


    public static boolean isCodiceFiscaleValid(String codiceFiscale)
    {

        // Verifica la lunghezza del codice fiscale
        if (codiceFiscale.length() == 16)
        {
            if(containsValidCharacters(codiceFiscale))
            {
                return true;
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "il codice fiscale contiene caratteri non validi");
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "il codice fiscale deve essere di 16 caratteri");
        }
    }

    private static boolean containsValidCharacters(String codiceFiscale) {
        // Utilizza l'espressione regolare per verificare che il codice fiscale contenga solo caratteri validi
        Pattern pattern = Pattern.compile("[A-Z0-9]+");
        Matcher matcher = pattern.matcher(codiceFiscale);
        return matcher.matches();
    }
}
