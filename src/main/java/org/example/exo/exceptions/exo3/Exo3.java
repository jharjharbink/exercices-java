/*
03) Gestion d'un accès à un élément inexistant dans un tableau
Écrivez un programme Java qui crée un tableau d'entiers de taille 5 et
tente d'accéder au sixième élément. Assurez-vous de gérer ce cas en
lançant une exception appropriée.
 */


package org.example.exo.exceptions.exo3;

public class Exo3 {
    public static void main(String[] args) {
        int[] tableau = {1, 2, 3, 4, 5};
        try {
            int value = tableau[tableau.length];
        } catch (IndexOutOfBoundsException e){
            System.out.println("Mais enfin, ça va pas de demander un élement out of bound ?");
        }
    }
}
