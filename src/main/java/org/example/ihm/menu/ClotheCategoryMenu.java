package org.example.ihm.menu;

import org.example.db.ClotheCategory;
import org.example.exceptions.ReturnToInventoryGestionMenuException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.BackMenuOptions;

import java.util.Arrays;
import java.util.List;

import static org.example.ihm.BackMenuOptions.RETURN_TO_INVENTORY_GESTION_MENU;
import static org.example.ihm.menu.BaseMenu.setMenuLength;

public class ClotheCategoryMenu extends BaseMenu implements Menus{

    private final static List<String> menuElements = Arrays.asList("Choix Catégorie de Vêtement",
            "Femme",
            "Homme",
            "Enfant");
    private final static BackMenuOptions backMenuOption = RETURN_TO_INVENTORY_GESTION_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public ClotheCategoryMenu() {
        super(menuElements, backMenuOption);
    }

    public static ClotheCategory menuOptions(int userMenuChoice){
        return switch (userMenuChoice) {
            case 1 -> ClotheCategory.FEMALE;
            case 2 -> ClotheCategory.MALE;
            case 3 -> ClotheCategory.CHILD;
            case 0 -> throw new ReturnToInventoryGestionMenuException();
            default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + CreateArticleMenu.class.getSimpleName());
        };
    }
}

