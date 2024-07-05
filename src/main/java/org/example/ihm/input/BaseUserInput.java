package org.example.ihm.input;

import org.example.db.ClotheCategory;
import org.example.db.Size;
import org.example.ihm.Ihm;
import org.example.ihm.menu.ClotheCategoryMenu;
import org.example.ihm.menu.ClotheSizeMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static org.example.ihm.MenuType.CLOTHE_CATEGORY_MENU;
import static org.example.ihm.MenuType.CLOTHE_SIZE_MENU;

public abstract class BaseUserInput {

    Scanner scanner;

    public BaseUserInput(Scanner scanner) {
        this.scanner = scanner;
    }

    protected String askString(String message){
        System.out.println(message);
        return scanner.nextLine();
    }

    protected ClotheCategory askClotheCategory(){
        int userChoice1 = Ihm.askUserMenuChoice(CLOTHE_CATEGORY_MENU);
        return ClotheCategoryMenu.menuOptions(userChoice1);
    }

    protected Size askSize(){
        int userChoice2 = Ihm.askUserMenuChoice(CLOTHE_SIZE_MENU);
        return ClotheSizeMenu.menuOptions(userChoice2);
    }

    protected int askInt(String message){
        System.out.println(message);
        int userInput = scanner.nextInt();
        scanner.nextLine();
        return userInput;
    }

    protected long askLong(String message){
        System.out.println(message);
        long userInput = scanner.nextLong();
        scanner.nextLine();
        return userInput;
    }

    protected double askDouble(String message){
        System.out.println(message);
        double userInput = scanner.nextDouble();
        scanner.nextLine();
        return userInput;
    }

    protected Date askDate(String message){
        String dateString = askString(message + "(format 13/01/2001)");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = formatter.parse(dateString);
            System.out.println("Converted Date: " + date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format MM/dd/yyyy.");
            throw new RuntimeException(e);
        }
        return date;
    }
}
