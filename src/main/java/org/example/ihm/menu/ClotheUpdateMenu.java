package org.example.ihm.menu;

import org.example.exceptions.ReturnToClientGestionMenuException;
import org.example.exceptions.ReturnToInventoryGestionMenuException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.BackMenuOptions;
import org.example.ihm.ClientUpdateChoices;
import org.example.ihm.ClotheUpdateChoices;

import java.util.Arrays;
import java.util.List;

import static org.example.ihm.BackMenuOptions.RETURN_TO_CLIENT_GESTION_MENU;
import static org.example.ihm.BackMenuOptions.RETURN_TO_INVENTORY_GESTION_MENU;

public class ClotheUpdateMenu extends BaseMenu implements Menus {

    private final static List<String> menuElements = Arrays.asList("MAJ Vêtement",
            "Description",
            "Prix",
            "Quantité en stock",
            "Catégorie",
            "Taille");
    private final static BackMenuOptions backMenuOption = RETURN_TO_INVENTORY_GESTION_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public ClotheUpdateMenu() {
        super(menuElements, backMenuOption);
    }

    public static ClotheUpdateChoices menuOptions(int userMenuChoice) {
        return switch (userMenuChoice) {
            case 1 -> ClotheUpdateChoices.DESCRIPTION;
            case 2 -> ClotheUpdateChoices.PRICE;
            case 3 -> ClotheUpdateChoices.STOCK_QUANTITY;
            case 4 -> ClotheUpdateChoices.CATEGORY;
            case 5 -> ClotheUpdateChoices.SIZE;

            case 0 -> throw new ReturnToInventoryGestionMenuException();
            default ->
                    throw new WrongSearchChoiceException("Unknown choice by user with menuType " + CreateArticleMenu.class.getSimpleName());
        };
    }
}