package org.example.Tank.Exceptions;

public class LessThanEmptyException extends Exception{
    public LessThanEmptyException(String errorMessage) {
        super(errorMessage);
    }
}
