package org.example.exo.billeterie;

import org.example.exo.billeterie.ihm.IHM;

import static org.example.exo.billeterie.ihm.MenuType.MAIN_MENU;

public class Main {
    public static void main(String[] args) {

        IHM ihm = new IHM();
        ihm.start(MAIN_MENU);
    }
}
