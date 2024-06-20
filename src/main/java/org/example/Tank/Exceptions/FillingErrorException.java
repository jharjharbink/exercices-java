package org.example.Tank.Exceptions;

public class FillingErrorException extends Exception{
    public FillingErrorException(String errorMessage) {
        super(errorMessage);
    }
}
