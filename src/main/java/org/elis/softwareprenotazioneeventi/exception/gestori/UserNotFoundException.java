package org.elis.softwareprenotazioneeventi.exception.gestori;

public class UserNotFoundException extends  RuntimeException{

    public UserNotFoundException() {super("nessun utente attivo trovato con questa username e/o password");}
}
