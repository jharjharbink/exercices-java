package org.example.service;

import org.example.db.ClotheCategory;
import org.example.db.Size;
import org.example.db.model.Client;
import org.example.db.model.article.Article;
import org.example.db.model.article.Clothe;
import org.example.db.model.article.Electronic;
import org.example.db.model.article.Food;
import org.example.db.repository.ArticleRepository;
import org.example.exceptions.NotFoundException;
import org.example.ihm.ClotheUpdateChoices;
import org.example.ihm.ElectronicUpdateChoice;
import org.example.ihm.FoodUpdateChoice;

import java.util.Date;
import java.util.List;

public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService() {
        this.articleRepository = new ArticleRepository();
    }

    public boolean createElectronic(String description,double price, int stockQuantity,int  batteryMaxCapacity) {
        Electronic electronic = Electronic.builder()
                .description(description)
                .price(price)
                .stockQuantity(stockQuantity)
                .batteryMaxCapacity(batteryMaxCapacity)
                .build();

        return articleRepository.create(electronic);
    }

    public boolean createClothe(String description, double price, int stockQuantity, ClotheCategory category, Size size) {
        Clothe clothe = Clothe.builder()
                .description(description)
                .price(price)
                .stockQuantity(stockQuantity)
                .category(category)
                .size(size)
                .build();

        return articleRepository.create(clothe);
    }

    public boolean createFood(String description, double price, int stockQuantity, Date expirationDate) {
        Food food = Food.builder()
                .description(description)
                .price(price)
                .stockQuantity(stockQuantity)
                .expirationDate(expirationDate)
                .build();

        return articleRepository.create(food);
    }

    public <T> boolean updateClothe(long articleId, ClotheUpdateChoices itemToChange, T valueToChange){
        Article article =  articleRepository.selectById(Article.class, articleId);
        if (article instanceof Clothe clothe){
            switch(itemToChange){
                case ClotheUpdateChoices.DESCRIPTION -> clothe.setDescription((String) valueToChange);
                case ClotheUpdateChoices.PRICE -> clothe.setPrice((double) valueToChange);
                case ClotheUpdateChoices.STOCK_QUANTITY -> clothe.setStockQuantity((int) valueToChange);
                case ClotheUpdateChoices.CATEGORY -> clothe.setCategory((ClotheCategory) valueToChange);
                case ClotheUpdateChoices.SIZE -> clothe.setSize((Size) valueToChange);
            }
            return articleRepository.update(clothe);
        }
        return false;  //TODO make exception and catch it in UserInput

    }

    public <T> boolean updateElectronic(long articleId, ElectronicUpdateChoice itemToChange, T valueToChange){
        Article article = articleRepository.selectById(Article.class, articleId);

        if (article instanceof Electronic electronic) {
            switch (itemToChange) {
                case ElectronicUpdateChoice.DESCRIPTION -> electronic.setDescription((String) valueToChange);
                case ElectronicUpdateChoice.PRICE -> electronic.setPrice((double) valueToChange);
                case ElectronicUpdateChoice.STOCK_QUANTITY -> electronic.setStockQuantity((int) valueToChange);
                case ElectronicUpdateChoice.BATTERY_MAX_CAPACITY -> electronic.setBatteryMaxCapacity((int) valueToChange);
            }
            return articleRepository.update(electronic);
        }
        return false; //TODO make exception and catch it in UserInput
    }

    public <T> boolean updateFood(long articleId, FoodUpdateChoice itemToChange, T valueToChange){
        Article article = articleRepository.selectById(Article.class, articleId);

        if (article instanceof Food food) {
            switch (itemToChange) {
                case FoodUpdateChoice.DESCRIPTION -> food.setDescription((String) valueToChange);
                case FoodUpdateChoice.PRICE -> food.setPrice((double) valueToChange);
                case FoodUpdateChoice.STOCK_QUANTITY -> food.setStockQuantity((int) valueToChange);
                case FoodUpdateChoice.EXPIRATION_DATE -> food.setExpirationDate((Date) valueToChange);
            }
            return articleRepository.update(food);
        }
        return false; //TODO make exception and catch it in UserInput
    }

    public boolean updateStock(long articleId, int restockQuantity){
        Article article = articleRepository.selectById(Article.class, articleId);
        int newStockQuantity = article.getStockQuantity() + restockQuantity;
        article.setStockQuantity(newStockQuantity);
        return true;
    }

    public boolean delete (long id) throws NotFoundException {
        Article article = articleRepository.selectById(Article.class, id);
        if(article != null){
            return articleRepository.delete(article);
        }
        throw new NotFoundException("can't find client with id: " + id);
    }


    public Article selectById (long id) throws NotFoundException {
        Article article = articleRepository.selectById(Article.class, id);
        if(article != null){
            return article;
        }
        throw new NotFoundException("can't find client with id: " + id);
    }

    public List<Article> selectAll (){
        return articleRepository.selectAll(Article.class);
    }

    public List<Article> selectByName(String name){
        return articleRepository.selectArticleByName(name);
    }
}
