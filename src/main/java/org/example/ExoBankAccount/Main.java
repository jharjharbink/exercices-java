package org.example.ExoBankAccount;

import org.example.ExoBankAccount.metier.Client;

public class Main {
    public static void main(String[] args){

        String name = "Dos Santos";
        String surname = "José";
        int id = 0;
        String phoneNumber = "0667777201";

        Client client = new Client(name, surname, id, phoneNumber);

        Ihm currentIHM = new Ihm(client);
        try{
            currentIHM.mainLoop();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
