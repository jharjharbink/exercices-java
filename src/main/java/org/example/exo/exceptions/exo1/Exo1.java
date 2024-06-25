/*
01) Gestion d'une conversion de type incorrecte
Écrivez un programme Java qui tente de convertir une chaîne de
caractères saisie par l'utilisateur en entier. Assurez-vous de gérer le
cas où la chaîne de caractères ne représente pas un entier en traitant
une exception appropriée.
Bonus :
Le programme demandera à nouveau à l'utilisateur de saisir
quelque chose s'il entre une valeur qui ne peut être convertie en
entier
 */
package org.example.exo.exceptions.exo1;

import java.util.Scanner;

public class Exo1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        String digitOrNumber;

        while(true) {

            System.out.println("Veuillez saisir une chaîne d'entier");

            try{
                userInput = Integer.parseInt(scanner.nextLine());
                break;
            }  catch (NumberFormatException e){
                System.out.println("Saisie incorrect");
            }
        }

        if (userInput < 9 && userInput > -9){
            digitOrNumber = "chiffre";
        } else {
            digitOrNumber = "nombre";
        }

        System.out.println("\nVous avec saisie le " + digitOrNumber + ": " + userInput);
    }
}
