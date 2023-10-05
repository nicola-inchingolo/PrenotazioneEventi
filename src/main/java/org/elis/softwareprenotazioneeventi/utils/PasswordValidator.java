package org.elis.softwareprenotazioneeventi.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {


    public static boolean isValidPassword(String password)
    {
        if(password.length() > 8)
        {
            if (containsUppercaseLetter(password)) {
                if(containsSpecialCharacter(password))
                {
                    return true;
                }
                else
                {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la password deve contenere almeno un carattere speciale");
                }
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la password deve contenere almeno una lettere maiuscola");
            }
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la password deve essere lunga almeno 8 caratteri");
        }
    }

    private static boolean containsUppercaseLetter(String password) {
        // Utilizza l'espressione regolare per cercare almeno una lettera maiuscola
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    private static boolean containsSpecialCharacter(String password) {
        // Utilizza l'espressione regolare per cercare almeno un carattere speciale
        Pattern pattern = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }






}
