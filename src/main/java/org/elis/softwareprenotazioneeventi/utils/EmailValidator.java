package org.elis.softwareprenotazioneeventi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static boolean isValidEmail(String email) {
        // Crea un oggetto Pattern per la regex
        Pattern pattern = Pattern.compile(EMAIL_REGEX);

        // Crea un oggetto Matcher per confrontare l'input con la regex
        Matcher matcher = pattern.matcher(email);

        // Esegue la corrispondenza e restituisce true se l'email è valida
        return matcher.matches();
    }
}
