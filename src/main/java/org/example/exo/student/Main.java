package org.example.exo.student;

public class Main {

    public static void main(String[] args) {
        Ihm currentIHM = new Ihm();
        try{
            currentIHM.mainLoop();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}