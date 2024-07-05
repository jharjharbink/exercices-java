package org.example.ihm.input;

import org.example.db.model.Client;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.ClientUpdateChoices;
import org.example.ihm.Ihm;
import org.example.ihm.menu.ClientUpdateMenu;
import org.example.service.ClientService;

import java.util.List;
import java.util.Scanner;

import static org.example.ihm.ClientUpdateChoices.NAME;
import static org.example.ihm.MenuType.CLIENT_UPDATE_MENU;

public class ClientUserInput extends BaseUserInput{

    private ClientService clientService;

    public ClientUserInput(Scanner scanner) {
        super(scanner);
        this.clientService = new ClientService();
    }

    public void askClientCreation(){
        String name = askString("Quel est son nom ?");
        String mail = askString("Quel est son email ?");

        boolean isCreated = clientService.create(name, mail);

        if (isCreated)
            System.out.println("Client Créer");
        else
            System.out.println("Problème à la création");
    }

    public void askClientUpdate() throws NotFoundException {
        ClientService clientService = new ClientService();

        long clientId = askLong("Quel est l'id du client que vous voulez changer ?");

        int userChoice = Ihm.askUserMenuChoice(CLIENT_UPDATE_MENU);
        ClientUpdateChoices itemToChange = ClientUpdateMenu.menuOptions(userChoice);

        String itemToChangeValue;
        switch (itemToChange){
            case NAME -> itemToChangeValue = askString("Quel est son nouveau nom ?");
            case EMAIL -> itemToChangeValue = askString("Quel est son nouveau email ?");
            default -> throw new WrongSearchChoiceException("Wrong search choice selection in askClientUpdate()");
        }
        boolean isUpdated = clientService.update(clientId, itemToChange, itemToChangeValue);

        if (isUpdated)
            System.out.println("Client Mis à jour");
        else
            System.out.println("Problème à la mise à jour");
    }

    public void askClientDelete() throws NotFoundException {
        long clientId = askLong("Quel est l'id du client que vous voulez supprimer ?");
        boolean isDeleted = clientService.delete(clientId);

        if (isDeleted)
            System.out.println("Client Supprimer");
        else
            System.out.println("Problème à la suppression");
    }

    public void askClientById() throws NotFoundException {
        long clientId = askLong("Quel est l'id du client que vous voulez consulter ?");
        Client client = clientService.selectById(clientId);

        System.out.println(client);
    }

    public void askAllClient(){
        List<Client> clients = clientService.selectAll();

        for (Client client : clients)
            System.out.println(client);
    }

    public void askClientByName(){
        String clientName = askString("Quel est le nom du client que vous voulez consulter ?");

        List<Client> clients = clientService.selectByName(clientName);

        for (Client client : clients)
            System.out.println(client);

    }
}
