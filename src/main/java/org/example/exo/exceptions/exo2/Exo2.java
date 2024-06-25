/*
Écrivez un programme Java qui demande à l'utilisateur de saisir un nombre entier
positif et calcule sa racine carrée. Le programme doit gérer les exceptions pour les
cas où l'utilisateur saisit un nombre négatif ou entre une entrée non numérique.
Votre tâche consiste à implémenter la logique nécessaire pour gérer ces situations
exceptionnelles en utilisant les mécanismes appropriés de gestion des exceptions en
Java

Bonus :
Le programme demandera à nouveau à l'utilisateur de saisir un nombre s'il entre
une valeur incorrecte.
 */
package org.example.exo.exceptions.exo2;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exo2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        double squareRootNbr = 0;

        while(true) {

            System.out.println("Veuillez saisir un entier strictement postif");

            try {
                userInput = scanner.nextInt();
                if (userInput < 1)
                    throw new SquareRootIncampatibleNumberException();
                squareRootNbr = Math.sqrt(userInput);
                break;

            } catch (InputMismatchException e){
                System.out.println("Saisie incorrect");
            } catch (SquareRootIncampatibleNumberException e){
                System.out.println(e);
            } catch (NoSuchElementException e) {
                System.out.println("Allez, saisie stp...");
            } catch (IllegalStateException e) {
                System.out.println("Le développeur à codé avec ses pieds");
            } finally {
                scanner.nextLine();
            }
        }

        System.out.println("\nLa racine carrée de " + userInput + " est " + squareRootNbr);
    }
}
