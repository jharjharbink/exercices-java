package org.example.exo.exceptions.exo2;

public class SquareRootIncampatibleNumberException extends Exception{
    public SquareRootIncampatibleNumberException() {
        super("Une opération de racine carré se fait avec des nombres strictement positifs.");
    }
}
