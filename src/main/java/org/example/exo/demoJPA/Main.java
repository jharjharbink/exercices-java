package org.example.exo.demoJPA;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        String dbName = "exerciceZoo";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(dbName);
        DatabaseDealer dbDealer = new DatabaseDealer(emf);

        IHM ihm = new IHM(dbDealer);
        ihm.mainLoop();
        emf.close();
    }
}
