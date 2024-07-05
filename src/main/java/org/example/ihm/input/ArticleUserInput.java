package org.example.ihm.input;

import org.example.db.ClotheCategory;
import org.example.db.Size;
import org.example.db.model.article.Article;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.ClotheUpdateChoices;
import org.example.ihm.ElectronicUpdateChoice;
import org.example.ihm.FoodUpdateChoice;
import org.example.ihm.Ihm;
import org.example.ihm.menu.ClotheUpdateMenu;
import org.example.ihm.menu.ElectronicUpdateMenu;
import org.example.ihm.menu.FoodUpdateMenu;
import org.example.service.ArticleService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.MenuType.*;

public class ArticleUserInput extends BaseUserInput{

    ArticleService articleService;

    public ArticleUserInput(Scanner scanner) {
        super(scanner);
        this.articleService = new ArticleService();

    }

    public void askElectronicCreation(){

        String description = askString("Quel est sa description ?");
        Double price = askDouble("Quel est son price ?");
        int stockQuantity = askInt("Quel est sa quantité en stock ?");
        int batteryMaxCapacity = askInt("Quel est sa capacité en batterie ? (en Ampère heure)");

        boolean isCreated = articleService.createElectronic(description, price,stockQuantity, batteryMaxCapacity);

        if (isCreated)
            System.out.println("Article d'électronique Créer");
        else
            System.out.println("Problème à la création");
    }

    public void askClotheCreation(){
        String description = askString("Quel est sa description ?");
        Double price = askDouble("Quel est son price ?");
        int stockQuantity = askInt("Quel est sa quantité en stock ?");
        ClotheCategory clotheCategory = askClotheCategory();
        Size clotheSize = askSize();

        boolean isCreated = articleService.createClothe(description, price, stockQuantity, clotheCategory, clotheSize);

        if (isCreated)
            System.out.println("Vêtement Créer");
        else
            System.out.println("Problème à la création");
    }

    public void askFoodCreation(){
        String description = askString("Quel est sa description ?");
        Double price = askDouble("Quel est son price ?");
        int stockQuantity = askInt("Quel est sa quantité en stock ?");
        Date expirationDate = askDate("Quel est sa date de péremption ?");

        boolean isCreated = articleService.createFood(description, price, stockQuantity, expirationDate);

        if (isCreated)
            System.out.println("Vêtement Créer");
        else
            System.out.println("Problème à la création");
    }

    public void askUpdateClothe() throws WrongSearchChoiceException {
        long articleId = askLong("Quel est l'id du vêtement à mettre à jour");

        int userChoice = Ihm.askUserMenuChoice(CLOTHE_UPDATE_MENU);
        ClotheUpdateChoices itemToChange = ClotheUpdateMenu.menuOptions(userChoice);

        boolean isUpdated;
        switch (itemToChange){
            case ClotheUpdateChoices.DESCRIPTION:
                String newDescription = askString("Quel est sa description ?");
                isUpdated = articleService.updateClothe(articleId, ClotheUpdateChoices.DESCRIPTION, newDescription);
                break;
            case ClotheUpdateChoices.PRICE:
                double newPrice = askDouble("Quel est son prix ?");
                isUpdated = articleService.updateClothe(articleId, ClotheUpdateChoices.PRICE, newPrice);
                break;
            case ClotheUpdateChoices.STOCK_QUANTITY:
                int newStockQuantity = askInt("Quel est sa quantité en stock ?");
                isUpdated = articleService.updateClothe(articleId, ClotheUpdateChoices.STOCK_QUANTITY, newStockQuantity);
                break;
            case ClotheUpdateChoices.CATEGORY:
                ClotheCategory newClotheCategory = askClotheCategory();
                isUpdated = articleService.updateClothe(articleId, ClotheUpdateChoices.CATEGORY, newClotheCategory);
                break;
            case ClotheUpdateChoices.SIZE:
                Size newSize = askSize();
                isUpdated = articleService.updateClothe(articleId, ClotheUpdateChoices.SIZE, newSize);
                break;
            default:
                throw new WrongSearchChoiceException("Wrong search choice selection in askClientUpdate()");
        }

        if (isUpdated)
            System.out.println("Client Mis à jour");
        else
            System.out.println("Problème à la mise à jour");
    }

    public void askUpdateElectronic(){
        long articleId = askLong("Quel est l'id du matériel electronique à mettre à jour");

        int userChoice = Ihm.askUserMenuChoice(ELECTRONIC_UPDATE_MENU);
        ElectronicUpdateChoice itemToChange = ElectronicUpdateMenu.menuOptions(userChoice);

        boolean isUpdated;
        switch (itemToChange){
            case ElectronicUpdateChoice.DESCRIPTION:
                String newDescription = askString("Quel est sa description ?");
                isUpdated = articleService.updateElectronic(articleId, ElectronicUpdateChoice.DESCRIPTION, newDescription);
                break;
            case ElectronicUpdateChoice.PRICE:
                double newPrice = askDouble("Quel est son prix ?");
                isUpdated = articleService.updateElectronic(articleId, ElectronicUpdateChoice.PRICE, newPrice);
                break;
            case ElectronicUpdateChoice.STOCK_QUANTITY:
                int newStockQuantity = askInt("Quel est sa quantité en stock ?");
                isUpdated = articleService.updateElectronic(articleId, ElectronicUpdateChoice.STOCK_QUANTITY, newStockQuantity);
                break;
            case ElectronicUpdateChoice.BATTERY_MAX_CAPACITY:
                int newMaxBatteryCapacity = askInt("Quel est sa capacité de batterie");
                isUpdated = articleService.updateElectronic(articleId, ElectronicUpdateChoice.BATTERY_MAX_CAPACITY, newMaxBatteryCapacity);
                break;
            default:
                throw new WrongSearchChoiceException("Wrong search choice selection in askClientUpdate()");
        }

        if (isUpdated)
            System.out.println("Client Mis à jour");
        else
            System.out.println("Problème à la mise à jour");
    }

    public void askUpdateFood(){
        long articleId = askLong("Quel est l'id du vêtement à mettre à jour");

        int userChoice = Ihm.askUserMenuChoice(FOOD_UPDATE_MENU);
        FoodUpdateChoice itemToChange = FoodUpdateMenu.menuOptions(userChoice);

        boolean isUpdated;
        switch (itemToChange){
            case FoodUpdateChoice.DESCRIPTION:
                String newDescription = askString("Quel est sa description ?");
                isUpdated = articleService.updateFood(articleId, FoodUpdateChoice.DESCRIPTION, newDescription);
                break;
            case FoodUpdateChoice.PRICE:
                double newPrice = askDouble("Quel est son prix ?");
                isUpdated = articleService.updateFood(articleId, FoodUpdateChoice.PRICE, newPrice);
                break;
            case FoodUpdateChoice.STOCK_QUANTITY:
                int newStockQuantity = askInt("Quel est sa quantité en stock ?");
                isUpdated = articleService.updateFood(articleId, FoodUpdateChoice.STOCK_QUANTITY, newStockQuantity);
                break;
            case FoodUpdateChoice.EXPIRATION_DATE:
                int newMaxBatteryCapacity = askInt("Quel est sa capacité de batterie");
                isUpdated = articleService.updateFood(articleId, FoodUpdateChoice.EXPIRATION_DATE, newMaxBatteryCapacity);
                break;
            default:
                throw new WrongSearchChoiceException("Wrong search choice selection in askClientUpdate()");
        }

        if (isUpdated)
            System.out.println("Client Mis à jour");
        else
            System.out.println("Problème à la mise à jour");
    }

    public void askDeleteArticle() throws NotFoundException {
        long articleId = askLong("Quel est l'id de l'article que vous voulez supprimer ?");
        boolean isDeleted = articleService.delete(articleId);

        if (isDeleted)
            System.out.println("Article Supprimer");
        else
            System.out.println("Problème à la suppression");
    }


    public void askArticleById() throws NotFoundException {
        long articleId = askLong("Quel est l'id de l'article que vous voulez consulter ?");
        Article article = articleService.selectById(articleId);

        System.out.println(article);
    }

    public void askAllArticle(){
        List<Article> articles = articleService.selectAll();

        for (Article article : articles)
            System.out.println(article);
    }

    public void askArticleByDescription(){
        String articleDescription = askString("Quel est la Description de l'article que vous voulez consulter ?");

        List<Article> articles = articleService.selectByName(articleDescription);

        for (Article article : articles)
            System.out.println(article);
    }

    public void askRestock(){
        long articleId = askLong("Quel est l'id de l'article que vous voulez resotcker ?");
        int addedQuantity = askInt("De combien d'élement allez vous restocker ?");
        boolean isRestock = articleService.updateStock(articleId, addedQuantity);

        if (isRestock)
            System.out.println("Article restocker");
        else
            System.out.println("Problème à la restockation");
    }
}
