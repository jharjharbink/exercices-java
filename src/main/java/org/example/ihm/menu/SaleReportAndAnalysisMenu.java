package org.example.ihm.menu;

import org.example.exceptions.ReturnToMainMenuException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.BackMenuOptions;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.BackMenuOptions.RETURN_TO_MAIN_MENU;
import static org.example.ihm.Ihm.start;
import static org.example.ihm.MenuType.SALE_BY_PERIOD_MENU;

public class SaleReportAndAnalysisMenu extends BaseMenu {

    private final static List<String> menuElements = Arrays.asList("Rapports et Analyses de ventes",
            "Ventes par produit",
            "Ventes par période",
            "Ventes par Client");
    private final static BackMenuOptions backMenuOption = RETURN_TO_MAIN_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public SaleReportAndAnalysisMenu() {
        super(menuElements, backMenuOption);
    }

    public static void menuOptions(int userMenuChoice, Scanner scanner){
        switch (userMenuChoice) {
            case 1 -> System.out.println("Ventes par produit");
            case 2 -> start(SALE_BY_PERIOD_MENU);
            case 3 -> System.out.println("Ventes par  Client");
            case 0 -> throw new ReturnToMainMenuException();
            default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + MainMenu.class.getSimpleName());
        }
    }
}
