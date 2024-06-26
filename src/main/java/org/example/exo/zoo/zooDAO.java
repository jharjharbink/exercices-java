package org.example.exo.zoo;

import javax.persistence.*;
import java.util.List;

public class zooDAO {
    private EntityManager entityManger;

    public zooDAO(EntityManagerFactory emf) {
        this.entityManger = emf.createEntityManager();
    }

    public void createAnimal(Animal animal){
        entityManger.getTransaction().begin();
        entityManger.persist(animal);
        entityManger.getTransaction().commit();
    }

    public <T> List<Animal> getAnimals(T animalParam, AnimalSearchPossibility searchChoice){
        TypedQuery query = buildTypedQuery(animalParam, searchChoice);
        return query.getResultList();
    }

    private <T> TypedQuery buildTypedQuery(T animalParam, AnimalSearchPossibility searchChoice){
        TypedQuery query = getTypedQuery(searchChoice);
        query = setParameter(animalParam, query, searchChoice);
        return query;
    }

    private TypedQuery getTypedQuery(AnimalSearchPossibility searchChoice){
        String queryString = AnimalQueryStringBuilder(searchChoice);
        return entityManger.createQuery(queryString, Animal.class);
    }

    private <T> TypedQuery setParameter(T animalAttribute, TypedQuery query, AnimalSearchPossibility searchChoice){
        String searchParam = getSearchParam(searchChoice);
        query.setParameter(searchParam, animalAttribute);
        return query;
    }

    private String AnimalQueryStringBuilder(AnimalSearchPossibility searchChoice){
        String query = "select a from Animal a WHERE ";
        switch (searchChoice){
            case ID -> query += "a.id = :id";
            case NAME -> query += "a.name = :name";
            case ALIMENTATION_REGIME -> query += "a.alimentationRegime = :alimentationRegime";
            default -> throw new WrongSearchChoiceException();
        }
        return query;
    }

    private String getSearchParam(AnimalSearchPossibility searchChoice){
        switch(searchChoice){
            case ID: return "id";
            case NAME: return "name";
            case ALIMENTATION_REGIME: return "alimentationRegime";
            default: throw new WrongSearchChoiceException();
        }
    }

    public EntityManager getEntityManger() {
        return entityManger;
    }
}
