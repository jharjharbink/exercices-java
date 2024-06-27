package org.example.exo.billeterie.utill.exceptions;

public class WrongSearchChoiceException extends RuntimeException{
    public WrongSearchChoiceException(String message) {
        System.out.println(message);
    }
}
