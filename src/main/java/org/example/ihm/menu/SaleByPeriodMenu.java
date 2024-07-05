package org.example.ihm.menu;


import org.example.exceptions.ReturnToSaleReportAndAnalysisMenu;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.BackMenuOptions;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.BackMenuOptions.RETURN_TO_SALE_REPORT_AND_ANALYSIS_MENU;

public class SaleByPeriodMenu extends BaseMenu{

    private final static List<String> menuElements = Arrays.asList("Ventes par dates",
            "depuis une date",
            "entre deux dates");
    private final static BackMenuOptions backMenuOption = RETURN_TO_SALE_REPORT_AND_ANALYSIS_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public SaleByPeriodMenu() {
        super(menuElements, backMenuOption);
    }

    public static void menuOptions(int userMenuChoice, Scanner scanner){
        switch (userMenuChoice) {
            case 1 -> System.out.println("afficher la saisie d'une date");
            case 2 -> System.out.println("afficher la saisie de deux dates");
            case 0 -> throw new ReturnToSaleReportAndAnalysisMenu();
            default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + CreateArticleMenu.class.getSimpleName());
        }
    }

}
