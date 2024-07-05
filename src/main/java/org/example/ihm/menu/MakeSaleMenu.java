package org.example.ihm.menu;

import org.example.exceptions.ReturnToSaleGestionMenu;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.BackMenuOptions;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.BackMenuOptions.RETURN_TO_SALE_GESTION_MENU;


public class MakeSaleMenu extends BaseMenu {
    private final static List<String> menuElements = Arrays.asList("Vente",
            "Enregistrer nouvel Article dans la vente");
    private final static BackMenuOptions backMenuOption = RETURN_TO_SALE_GESTION_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public MakeSaleMenu() {
        super(menuElements, backMenuOption);
    }

    public static void menuOptions(int userMenuChoice, Scanner scanner){
        switch (userMenuChoice) {
            case 1 -> System.out.println("enregistrement article et quantité");
            case 0 -> throw new ReturnToSaleGestionMenu();
            default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + MainMenu.class.getSimpleName());
        }
    }

}
