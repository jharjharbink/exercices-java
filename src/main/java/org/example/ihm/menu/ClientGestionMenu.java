package org.example.ihm.menu;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.ReturnToMainMenuException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.BackMenuOptions;
import org.example.ihm.input.ClientUserInput;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.BackMenuOptions.RETURN_TO_MAIN_MENU;
import static org.example.ihm.Ihm.start;
import static org.example.ihm.MenuType.CLIENT_CONSULT_MENU;

public class ClientGestionMenu extends BaseMenu{
    private final static List<String> menuElements = Arrays.asList("Gestion Client",
            "Créer",
            "Modifier",
            "Supprimer",
            "Consulter");
    private final static BackMenuOptions backMenuOption = RETURN_TO_MAIN_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public ClientGestionMenu() {
        super(menuElements, backMenuOption);
    }

    public static void menuOptions(int userMenuChoice, Scanner scanner){
        try{
            switch (userMenuChoice) {
                case 1 -> new ClientUserInput(scanner).askClientCreation();
                case 2 -> new ClientUserInput(scanner).askClientUpdate();
                case 3 -> new ClientUserInput(scanner).askClientDelete();
                case 4 -> start(CLIENT_CONSULT_MENU);
                case 0 -> throw new ReturnToMainMenuException();
                default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + MainMenu.class.getSimpleName());
            }
        } catch (NotFoundException e){
            System.out.println("client inconnus");
        }

    }
}
