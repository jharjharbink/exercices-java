package org.example.exo.student;

import org.example.exo.student.model.Requests;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Ihm {
    Scanner scanner;
    Connection connection;

    public Ihm() {
        this.scanner = new Scanner(System.in);
        this.connection =  new ConnectionDealer().getConnection();
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mainLoop() throws Exception {

        while(true){
            try{

                System.out.println(displayMainMenu());
                int userChoiceMainMenu = loopChoice();

                switch(userChoiceMainMenu){
                    case 1: displayCreateStudent(); break;
                    case 2: displayListStudent(-1); break;
                    case 3: displayListStudentByClass(); break;
                    case 4: displayDeleteStudent(); break;
                    case 0:
                        displayGoodBye();
                        closeConnection();
                        break;
                    default:
                        throw new Exception("Unknown choice by user");
                }
            } catch (Exception e){}
        }
    }

    private int loopChoice() throws Exception {
        int userChoice;

        while (true) {
            try {
                userChoice = Integer.parseInt(scanner.nextLine());

                if (userChoice < 0 || userChoice > 4) {
                    throw new Exception("Vous devez rentrer un chiffre compris entre 0 et 4.");
                }

            } catch (NumberFormatException e) {
                System.out.print("Saisie incorrecte ! (pas un entier) ");
                continue;

            } catch (Exception e) {
                System.out.print("Saisie incorrecte ! (pas dans les choix disponibles) ");
                continue;
            }
            break;
        }
        return userChoice;
    }

    public void displayCreateStudent(){
        Requests requestsBuilder = new Requests(connection);

        // ask for student creation
        System.out.println("nous allons enregistrer un etudiant");

        System.out.println("quel est son nom ?");
        String studentName = scanner.nextLine();

        System.out.println("quel est son prenom ?");
        String studentSurname = scanner.nextLine();

        System.out.println("quel est l'identifiant de sa classe ?");
        int studentClassId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("quel est sa date d'obtention de diplome ?  (date au format jj/mm/yy");
        String studentDiplomeDate = scanner.nextLine();

        requestsBuilder.createStudent(studentName, studentSurname,studentClassId, studentDiplomeDate);
    }

    private void displayListStudent(int classId){
        Requests requestsBuilder = new Requests(connection);
        ResultSet resultSet;

        try{
            resultSet = requestsBuilder.getStudents(connection, classId);

            System.out.println("Liste des étudiants");

            while(resultSet.next()){
                System.out.println(
                        resultSet.getInt("id")
                                + " " + resultSet.getString("name")
                                + " " + resultSet.getString("surname")
                                + " " + resultSet.getString("classNbr")
                                + " " + resultSet.getString("diplomeDate")
                );
            }

            resultSet.getStatement().close();
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void displayListStudentByClass() {
        System.out.println("Saisissez l'id de la classe");
        int classId = scanner.nextInt();
        displayListStudent(classId);
    }


    private void displayDeleteStudent() {
        Requests requestsBuilder = new Requests(connection);

        System.out.println("Veuillez sélectionné un étudiant à supprimer dans la liste ci-dessous");
        displayListStudent(-1);
        int studentId = scanner.nextInt();
        scanner.nextLine();

        try{
            ResultSet resultSet = requestsBuilder.deleteStudent(connection, studentId);
            resultSet.getStatement().close();
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayGoodBye(){
        System.out.println("Au revoir !");
    }

    private String displayMainMenu(){
        return headline("Menu Principal") +
                "1. Créer un étudiant.\n" +
                "2. Afficher les étudiants.\n" +
                "3. Afficher les étudiants d'une classe.\n" +
                "4. Supprimer un étudiant.\n" +
                "0. Quitter le programme";
    }

    private String headline(String headlineString){
        return "\n=== " + headlineString + " ===\n\n";
    }

}
