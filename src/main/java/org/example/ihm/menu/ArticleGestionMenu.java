package org.example.ihm.menu;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.ReturnToMainMenuException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.BackMenuOptions;
import org.example.ihm.input.ArticleUserInput;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.BackMenuOptions.RETURN_TO_MAIN_MENU;
import static org.example.ihm.Ihm.start;
import static org.example.ihm.MenuType.*;


public class ArticleGestionMenu extends BaseMenu {
    private final static List<String> menuElements = Arrays.asList("Gestion Inventaire",
            "Créer",
            "Modifier",
            "Supprimer",
            "Consulter",
            "Restocker");
    private final static BackMenuOptions backMenuOption = RETURN_TO_MAIN_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public ArticleGestionMenu() {
        super(menuElements, backMenuOption);
    }

    public static void menuOptions(int userMenuChoice, Scanner scanner){
        try{
            switch (userMenuChoice) {
                case 1 -> start(ARTICLE_CREATION_MENU);
                case 2 -> start(ARTICLE_UPDATE_MENU);
                case 3 -> new ArticleUserInput(scanner).askDeleteArticle();
                case 4 -> start(ARTICLE_CONSULT_MENU);
                case 5 -> new ArticleUserInput(scanner).askRestock();
                case 0 -> throw new ReturnToMainMenuException();
                default ->
                        throw new WrongSearchChoiceException("Unknown choice by user with menuType " + MainMenu.class.getSimpleName());
            }
        } catch (NotFoundException e){
            System.out.println(userMenuChoice);
        }
    }
}
