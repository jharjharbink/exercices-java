package org.example.exo.tank.Exceptions;

public class LessThanEmptyException extends Exception{
    public LessThanEmptyException(String errorMessage) {
        super(errorMessage);
    }
}
