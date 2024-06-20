package org.example;
import java.time.LocalDate;
import java.util.*;


public class Main {
    public static void main(String[] args){
        mainFilm();
    }

    public static void mainFilm(){

        List<Film> films = new ArrayList<Film>();

        LocalDate dateFilm1 = LocalDate.parse("1995-05-31");
        films.add(new Film("La Haine", "Mathieu Kassovitz", dateFilm1, "Drame"));

        LocalDate dateFilm2 = LocalDate.parse("2022-12-14");
        films.add(new Film("Avatar 2", "James Cameron", dateFilm2, "Action"));

        for(int i = 0; i < films.size(); i++){
            System.out.println(films.get(i).toString());
        }
    }

    public void mainChair(){
        List<Chair> chairs = Arrays.asList(
                new Chair(4, "chaine", "bleu", 14.4F),
                new Chair(3, "bambou", "gris", 130.99F),
                new Chair(1, "métal", "orange", 76.25F)
        );

        for(int i = 0; i < chairs.size(); i++){
            System.out.println(chairs.get(i).toString());
        }
    }
}
