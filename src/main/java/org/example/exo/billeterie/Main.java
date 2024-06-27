package org.example.exo.billeterie;

import org.example.exo.billeterie.db.BilleterieDAO;
import org.example.exo.billeterie.utill.IHM;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.example.exo.billeterie.utill.MenuType.MAIN_MENU;

public class Main {
    public static void main(String[] args) {
        String dbName = "exerciceBilleterie";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(dbName);
        BilleterieDAO dbDealer = new BilleterieDAO(emf);

        IHM ihm = new IHM(dbDealer);
        ihm.loop(MAIN_MENU);
        emf.close();    }
}
