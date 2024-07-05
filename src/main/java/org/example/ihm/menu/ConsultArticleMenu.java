package org.example.ihm.menu;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.ReturnToInventoryGestionMenuException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.BackMenuOptions;
import org.example.ihm.input.ArticleUserInput;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.BackMenuOptions.RETURN_TO_INVENTORY_GESTION_MENU;


public class ConsultArticleMenu extends BaseMenu{

    private final static List<String> menuElements = Arrays.asList("Consultation d'Article",
            "Afficher tout",
            "Afficher par id",
            "Afficher par nom");
    private final static BackMenuOptions backMenuOption = RETURN_TO_INVENTORY_GESTION_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public ConsultArticleMenu() {
        super(menuElements, backMenuOption);
    }

    public static void menuOptions(int userMenuChoice, Scanner scanner){
        try{
            switch (userMenuChoice) {
                case 1 -> new ArticleUserInput(scanner).askAllArticle();
                case 2 -> new ArticleUserInput(scanner).askArticleById();
                case 3 -> new ArticleUserInput(scanner).askArticleByDescription();
                case 0 -> throw new ReturnToInventoryGestionMenuException();
                default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + CreateArticleMenu.class.getSimpleName());
            }
        } catch (NotFoundException e) {
            System.out.println(e);
        }
    }
}
