package org.example.ihm.menu;

import org.example.exceptions.ReturnToClientGestionMenuException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.BackMenuOptions;
import org.example.ihm.ClientUpdateChoices;

import java.util.Arrays;
import java.util.List;

import static org.example.ihm.BackMenuOptions.RETURN_TO_CLIENT_GESTION_MENU;

public class ClientUpdateMenu extends BaseMenu implements Menus {

    private final static List<String> menuElements = Arrays.asList("MAJ client",
            "Nom",
            "Mail");
    private final static BackMenuOptions backMenuOption = RETURN_TO_CLIENT_GESTION_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public ClientUpdateMenu() {
        super(menuElements, backMenuOption);
    }

    public static ClientUpdateChoices menuOptions(int userMenuChoice){
        return switch (userMenuChoice) {
            case 1 -> ClientUpdateChoices.NAME;
            case 2 -> ClientUpdateChoices.EMAIL;
            case 0 -> throw new ReturnToClientGestionMenuException();
            default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + CreateArticleMenu.class.getSimpleName());
        };
    }
}
