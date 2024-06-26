package org.example.exo.zoo;

import java.util.List;
import java.util.Scanner;

import static org.example.exo.zoo.AnimalSearchPossibility.*;

public class IHM {
    Scanner scanner;
    zooDAO databaseDealer;

    public IHM(zooDAO databaseDealer) {
        this.scanner = new Scanner(System.in);
        this.databaseDealer = databaseDealer;
    }

    public void mainLoop() {

        boolean continueLoop = true;
        while(continueLoop){
            try{

                System.out.println(displayMainMenu());
                int userChoiceMainMenu = loopChoice(4);

                switch(userChoiceMainMenu){
                    case 1 -> displayCreateAnimal();
                    case 2 -> displayAnimal(ID);
                    case 3 -> displayAnimal(NAME);
                    case 4 -> displayAnimal(ALIMENTATION_REGIME);
                    case 0 -> continueLoop = goodBye();
                    default -> throw new Exception("Unknown choice by user");
                }
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    private int loopChoice(int choiceNbr){
        int userChoice;

        while (true) {
            try {
                userChoice = Integer.parseInt(scanner.nextLine());

                if (userChoice < 0 || userChoice > choiceNbr) {
                    throw new Exception("Vous devez rentrer un chiffre compris entre 0 et " + choiceNbr + ".");
                }

            } catch (NumberFormatException e) {
                System.out.print("Saisie incorrecte ! (pas un entier)");
                continue;

            } catch (Exception e) {
                System.out.print("Saisie incorrecte ! (pas dans les choix disponibles)");
                continue;
            }
            break;
        }
        return userChoice;
    }

    private void displayCreateAnimal(){
        System.out.println("Son nom SVP");
        String animalName = scanner.nextLine();

        System.out.println("Son age");
        int animalAge = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Son Régime Alimentaire");
        String animalAlimentationRegime = scanner.nextLine();

        System.out.println("Sa date d'arrivée");
        String animalArrivalDate = scanner.nextLine();

        databaseDealer.createAnimal(new Animal(animalName, animalAge, animalAlimentationRegime, animalArrivalDate));
    }

    private void displayAnimal(AnimalSearchPossibility searchChoice){

        String parameterChoiceString = buildParameterChoiceString(searchChoice);
        System.out.println(parameterChoiceString);

        Object animalSearchParameter;
        if (searchChoice == ID) {
            animalSearchParameter = scanner.nextLong();
            scanner.nextLine();
        } else
            animalSearchParameter = scanner.nextLine();

        List<Animal> animals = databaseDealer.getAnimals(animalSearchParameter, searchChoice);

        for (Animal animal : animals)
            System.out.println(animal);
    }


    private String buildParameterChoiceString(AnimalSearchPossibility searchChoice){

        String displayString = "Quel est ";
        String suffixString =  "que vous voulez trouver ?";

        switch (searchChoice){
            case ID -> displayString += "l'id de l'animal ";
            case NAME -> displayString += "le nom des animaux ";
            case ALIMENTATION_REGIME -> displayString += "le régime alimentaire des animaux ";
            default -> throw new WrongSearchChoiceException();
        }

        displayString += suffixString;
        return displayString;
    }

    private boolean goodBye(){
        databaseDealer.getEntityManger().close();
        System.out.println("Au revoir !");
        return false;
    }

    private String displayMainMenu(){
        return headline("Menu Principal") +
                "1. Créer un animal.\n" +
                "2. Chercher un animal par identifiant.\n" +
                "3. Chercher des animaux par nom.\n" +
                "4. Chercher des animaux par régime alimentaire.\n" +
                "0. Quitter le programme";
    }

    private String headline(String headlineString){
        return "\n=== " + headlineString + " ===\n\n";
    }
}
