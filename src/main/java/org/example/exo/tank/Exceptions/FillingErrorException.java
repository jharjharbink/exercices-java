package org.example.exo.tank.Exceptions;

public class FillingErrorException extends Exception{
    public FillingErrorException(String errorMessage) {
        super(errorMessage);
    }
}
