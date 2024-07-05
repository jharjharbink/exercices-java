package org.example.ihm.menu;

import org.example.exceptions.ExitProgramException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.BackMenuOptions;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.BackMenuOptions.CLOSE_PROGRAM;
import static org.example.ihm.Ihm.start;
import static org.example.ihm.MenuType.*;

public class MainMenu extends BaseMenu {

    private final static List<String> menuElements = Arrays.asList("Principal",
            "Gestion de l'inventaire",
            "Gestion des ventes",
            "Gestion des clients",
            "Rapports et analyses de Ventes");
    private final static BackMenuOptions backMenuOption = CLOSE_PROGRAM;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice

    public MainMenu() {
        super(menuElements, backMenuOption);
    }

    public static void menuOptions(int userMenuChoice){
        switch (userMenuChoice) {
            case 1 -> start(INVENTORY_GESTION_MENU);
            case 2 -> start(SALE_GESTION_MENU);
            case 3 -> start(CLIENT_GESTION_MENU);
            case 4 -> start(SALE_REPORT_AND_ANALYSIS_MENU);
            case 0 -> throw new ExitProgramException();
            default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + MainMenu.class.getSimpleName());
        }
    }
}
