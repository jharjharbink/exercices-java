package org.example.exo.demoJPA;

import javax.persistence.*;
import java.util.List;

import static org.example.exo.demoJPA.AnimalSearchPossibility.*;

public class DatabaseDealer {
    private EntityManager entityManger;

    public DatabaseDealer(EntityManagerFactory emf) {
        this.entityManger = emf.createEntityManager();
    }

    public void createAnimal(Animal animal){
        entityManger.getTransaction().begin();
        entityManger.persist(animal);
        entityManger.getTransaction().commit();
    }


    public List<Animal> getAnimal(int animalID){
        String queryString = AnimalQuerryBuilder(ID);
        queryString += animalID;

        Query query = entityManger.createQuery(queryString ,Animal.class);
        return query.getResultList();
    }

    public List<Animal> getAnimals(String animalAttribute, AnimalSearchPossibility searchChoice){
        String queryString = AnimalQuerryBuilder(searchChoice);
        TypedQuery<Animal> query = this.entityManger.createQuery(queryString, Animal.class);

        String searchParam = getSearchParam(searchChoice);
        query.setParameter(searchParam, animalAttribute);

        return query.getResultList();
    }

    private String AnimalQuerryBuilder(AnimalSearchPossibility searchChoice){
        String query = "select a from Animal a WHERE ";
        switch (searchChoice){
            case ID -> query += "a.id = ";
            case NAME -> query += "a.name = :name";
            case ALIMENTATION_REGIME -> query += "a.alimentationRegime = :alimentationRegime";
            default -> throw new WrongSearchChoiceException();
        }
        return query;
    }

    private String getSearchParam(AnimalSearchPossibility searchChoice){
        if (searchChoice == NAME)
            return "name";
        else if (searchChoice == ALIMENTATION_REGIME)
            return "alimentationRegime";
        else
            throw new WrongSearchChoiceException();
    }

    public EntityManager getEntityManger() {
        return entityManger;
    }
}
