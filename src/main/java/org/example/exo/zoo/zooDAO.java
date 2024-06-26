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
        String searchParam;
        String queryString = "select a from Animal a WHERE ";

        switch (searchChoice){
            case ID:
                searchParam = "id";
                queryString += "a.id = :";
                break;
            case NAME:
                searchParam = "name";
                queryString += "a.name = :";
                break;
            case ALIMENTATION_REGIME:
                searchParam = "alimentationRegime";
                queryString += "a.alimentationRegime = :";
                break;
            default:
                throw new WrongSearchChoiceException();
        }
        queryString += searchParam;

        TypedQuery query = entityManger.createQuery(queryString, Animal.class);

        query.setParameter(searchParam, animalParam);

        return query.getResultList();
    }


    public EntityManager getEntityManger() {
        return entityManger;
    }
}
