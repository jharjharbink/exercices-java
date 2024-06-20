package org.example.Tank;

import org.example.Tank.WaterTank;

public class Main {
    public static void main(String[] args) {
        WaterTank watertank1 = new WaterTank(10, 20, 10);
        WaterTank watertank2 = new WaterTank(5, 10, 10);
        System.out.println("Poids total de la citerne 1 : " + watertank1.getTotalWeight());
        System.out.println("Poids total de la citerne 2 : " + watertank2.getTotalWeight());
        System.out.println("----------------------");
        System.out.println("Quantité d'eau dans la citerne 1 : " + watertank1.getFillLevel());
        // watertank1.affichageCiterne();
        System.out.println("Quantité d'eau dans la citerne 2 : " + watertank2.getFillLevel());
        // watertank2.affichageCiterne();
        System.out.println("Quantité d'eau dans toutes les citernes : " + WaterTank.getTotalFillLevels());
        System.out.println("----------------------");
        double excessWater = watertank1.fill(11);
        System.out.println("Quantité d'eau dans la citerne 1 après ajout de 11 litres: " + watertank1.getFillLevel() + "/" + watertank1.getMaxCapacity());
        System.out.println("Excès d'eau récupéré : " + excessWater);
        double waterTaken = watertank2.empty(11);
        System.out.println("Quantité d'eau dans la citerne 2 après tentative de retrait de 11 litres: " + watertank2.getFillLevel() + "/" + watertank2.getMaxCapacity());
        System.out.println("Quantité d'eau récupérée : " + waterTaken);
        System.out.println("----------------------");
        System.out.println("Quantité d'eau dans la citerne 1 : " + watertank1.getFillLevel());
        // watertank1.affichageCiterne();
        System.out.println("Quantité d'eau dans la citerne 2 : " + watertank2.getFillLevel());
        // watertank2.affichageCiterne();
        System.out.println("Quantité d'eau dans toutes les citernes : " + WaterTank.getTotalFillLevels());
    }
}
