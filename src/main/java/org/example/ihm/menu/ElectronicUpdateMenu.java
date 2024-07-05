package org.example.ihm.menu;

import org.example.exceptions.ReturnToInventoryGestionMenuException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.BackMenuOptions;
import org.example.ihm.ClotheUpdateChoices;
import org.example.ihm.ElectronicUpdateChoice;

import java.util.Arrays;
import java.util.List;

import static org.example.ihm.BackMenuOptions.RETURN_TO_INVENTORY_GESTION_MENU;

public class ElectronicUpdateMenu extends BaseMenu implements Menus {
    private final static List<String> menuElements = Arrays.asList("MAJ Electronique",
            "Description",
            "Prix",
            "Quantité en stock",
            "Capacité batterie");
    private final static BackMenuOptions backMenuOption = RETURN_TO_INVENTORY_GESTION_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public ElectronicUpdateMenu() {
        super(menuElements, backMenuOption);
    }

    public static ElectronicUpdateChoice menuOptions(int userMenuChoice) {
        return switch (userMenuChoice) {
            case 1 -> ElectronicUpdateChoice.DESCRIPTION;
            case 2 -> ElectronicUpdateChoice.PRICE;
            case 3 -> ElectronicUpdateChoice.STOCK_QUANTITY;
            case 4 -> ElectronicUpdateChoice.BATTERY_MAX_CAPACITY;

            case 0 -> throw new ReturnToInventoryGestionMenuException();
            default ->
                    throw new WrongSearchChoiceException("Unknown choice by user with menuType " + CreateArticleMenu.class.getSimpleName());
        };
    }

}
