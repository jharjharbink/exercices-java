package org.example.exo.exceptions.exo4;

public class InvalidAgeException extends Exception {
    public InvalidAgeException(){
        System.out.println("Un étudiant doit être né pour entrer à l'université");
    }
}
