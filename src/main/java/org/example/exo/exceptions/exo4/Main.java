/*
Écrivez un programme Java pour simuler la gestion d'une liste d'étudiants.
Chaque étudiant est représenté par une classe Student avec les attributs
name (nom) et age (âge). Le programme doit permettre à l'utilisateur
d'ajouter des étudiants à la liste et d'afficher la liste complète des
étudiants.
Cependant, vous devez créer une exception personnalisée appelée
InvalidAgeException, qui sera levée si l'utilisateur tente d'ajouter un
étudiant avec un âge négatif. Lorsque cette exception est levée, le
programme doit afficher un message d'erreur approprié et demander à
l'utilisateur de saisir à nouveau l'âge de l'étudiant.
 */


package org.example.exo.exceptions.exo4;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> studentlist = new ArrayList<>();
        System.out.print("Bonjour, ");

        while(true){

            System.out.println("veuillez donner le nom d'un étudiant");
            String nameCurrentStudent = scanner.nextLine();

            boolean continueAskingAgeLoop = true;
            while(continueAskingAgeLoop){

                try{
                    System.out.println("Veuillez rentrer son age");
                    int ageCurrentStudent = scanner.nextInt();

                    studentlist.add(new Student(nameCurrentStudent, ageCurrentStudent));
                    continueAskingAgeLoop = false;
                } catch (InvalidAgeException e){
                    System.out.println(e);
                } catch (InputMismatchException e){
                    System.out.println("Saisie incorrect");
                } catch (NoSuchElementException e) {
                    System.out.println("Allez, saisie stp...");
                } catch (IllegalStateException e) {
                    System.out.println("Le développeur à codé avec ses pieds");
                } catch (UnsupportedOperationException  e) {
                    System.out.println("Qui c'est qui a changé l'API collection de Java ?!? ô_Ô");
                } catch (ClassCastException  e) {
                    System.out.println("Létudiant n'est pas un étudiant");
                } catch (NullPointerException  e) {
                    System.out.println("l'étudiant est null.");
                } finally {
                    scanner.nextLine();
                }
            }
        }
    }
}
