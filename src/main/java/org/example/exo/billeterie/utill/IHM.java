package org.example.exo.billeterie.utill;

import org.example.exo.billeterie.db.BilleterieDAO;
import org.example.exo.billeterie.db.DatabaseAction;
import org.example.exo.billeterie.db.model.Address;
import org.example.exo.billeterie.db.model.Client;
import org.example.exo.billeterie.db.model.Event;
import org.example.exo.billeterie.db.model.Ticket;
import org.example.exo.billeterie.utill.exceptions.ExitProgramException;
import org.example.exo.billeterie.utill.exceptions.ReturnToMainMenuException;
import org.example.exo.billeterie.utill.exceptions.WrongSearchChoiceException;


import static org.example.exo.billeterie.db.DatabaseAction.*;
import static org.example.exo.billeterie.utill.MenuType.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;




// TODO create enum for DatabaseAction and use it with DatabaseObject for update & delete
// TODO make menu for update choice in displayUpdateClient()
// TODO create abstract classe BaseRepository<T>  et des repos TicketRepo qui extends BaseRepo<Ticket>. Mettre les classes génériques ds Base Repo.

public class IHM {
    Scanner scanner;
    BilleterieDAO billeterieDAO;

    public IHM(BilleterieDAO billeterieDAO) {
        this.scanner = new Scanner(System.in);
        this.billeterieDAO = billeterieDAO;
    }

    public void loop(MenuType menuType) {
        while (true) {
            try {
                getMenuChoice(menuType);
            } catch (ReturnToMainMenuException | WrongSearchChoiceException e) {
            } catch (ExitProgramException e) {
                scanner.close();
                billeterieDAO.getEm().close();
                break;
            } catch (Exception e) {
                System.out.println(e);
                scanner.close();
                billeterieDAO.getEm().close();
                break;
            } finally {
                menuType = MAIN_MENU;
            }
        }
    }

    private int loopChoice(int choiceNbr) {
        int userChoice;

        while (true) {
            try {
                userChoice = Integer.parseInt(scanner.nextLine());

                if (userChoice < 0 || userChoice > choiceNbr) {
                    throw new Exception("Vous devez rentrer un chiffre compris entre 0 et " + choiceNbr + ".");
                }

            } catch (NumberFormatException e) {
                System.out.print("Saisie incorrecte ! (pas un entier)");
                continue;

            } catch (Exception e) {
                System.out.print("Saisie incorrecte ! (pas dans les choix disponibles)");
                continue;
            }
            break;
        }
        return userChoice;
    }

    private void getMenuChoice(MenuType menuType) throws ReturnToMainMenuException, WrongSearchChoiceException, ExitProgramException {

        int choiceLimitNbr;
        switch (menuType){
            case MAIN_MENU -> choiceLimitNbr = 4;
            case READ_MENU -> choiceLimitNbr = 2;
            case CREATE_MENU, DELETE_MENU, READ_ALL_MENU, READ_BY_ID_MENU, UPDATE_MENU -> choiceLimitNbr = 3;
            default -> throw new WrongSearchChoiceException("Wrong menuType in GetMenuChoice");
        }

        System.out.println(displayMenu(menuType));
        int userChoiceMainMenu = loopChoice(choiceLimitNbr);

        switch (menuType){
            case MAIN_MENU:
                switch (userChoiceMainMenu) {
                    case 1 -> loop(CREATE_MENU);
                    case 2 -> loop(READ_MENU);
                    case 3 -> loop(UPDATE_MENU);
                    case 4 -> loop(DELETE_MENU);
                    case 0 -> throw new ExitProgramException();
                    default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + menuType);
                }
                break;
            case READ_MENU:
                switch (userChoiceMainMenu){
                    case 1 -> loop(READ_ALL_MENU);
                    case 2 -> loop(READ_BY_ID_MENU);
                    case 0 -> throw new ReturnToMainMenuException();
                    default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + menuType);
                }
                break;
            case CREATE_MENU, READ_ALL_MENU, READ_BY_ID_MENU, UPDATE_MENU, DELETE_MENU:
                switch (userChoiceMainMenu) {
                    case 1 -> clientDealer(menuType);
                    case 2 -> eventDealer(menuType);
                    case 3 -> ticketDealer(menuType);
                    case 0 -> throw new ReturnToMainMenuException();
                    default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + menuType);
                }
                break;
        }
    }

    // CLIENTS
    private void clientDealer(MenuType menuType) {
        switch (menuType){
            case CREATE_MENU -> displayCreateClient();
            case READ_ALL_MENU -> displayGetAllClients();
            case READ_BY_ID_MENU -> displaygetClientByID(READ_BY_ID_ACTION);
            case UPDATE_MENU -> displayUpdateClient();
            case DELETE_MENU -> displayDeleteClient();
        }
    }

    private void displayCreateClient() {
        System.out.println("Quel est son nom ?");
        String clientName = scanner.nextLine();

        System.out.println("Quel est son prénom ?");
        String clientSurname = scanner.nextLine();

        System.out.println("Quel est son age ?");
        int clientAge = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Quel est son numéro de tel ?");
        String clientPhoneNbr = scanner.nextLine();

        Address clientAddress = createAddress();

        Client client = new Client(clientName, clientSurname, clientAge, clientPhoneNbr, clientAddress);

        billeterieDAO.create(client);
    }

    private void displayGetAllClients() {
        List<Client> clientList = billeterieDAO.getAllClients();
        for (Client client : clientList)
            System.out.println(client);
    }

    private Client displaygetClientByID(DatabaseAction action) {
        String actionString = getActionString(action);
        System.out.println("Quel est l'id du client que vous voulez " + actionString + " ?");
        long clientId = scanner.nextLong();
        scanner.nextLine();

        Client client = billeterieDAO.getClientByID(clientId);
        System.out.println(client);

        return client;
    }

    private void displayUpdateClient() {
        Client client = displaygetClientByID(UPDATE_ACTION);

        System.out.println("Quel est l'élement que vous voulez changer ? (nom, prenom, age, tel adresse)");
        String columnChoice = scanner.nextLine();

        System.out.println("saisissez son " + columnChoice);
        switch(columnChoice){
            case "nom":
                String newName = scanner.nextLine();
                client.setName(newName);
                break;
            case "prenom":
                String newSurname = scanner.nextLine();
                client.setSurname(newSurname);
                break;
            case "age":
                int newAge = scanner.nextInt();
                client.setAge(newAge);
                break;
            case "tel":
                String newTel = scanner.nextLine();
                client.setPhoneNbr(newTel);
                break;
            case "adresse":
                Address newAddress = createAddress();
                client.setAddress(newAddress);
        }
        billeterieDAO.update(client);
    }

    private void displayDeleteClient() {
        System.out.println("Quel l'id du client que vous voulez supprimer ?");
        long clientId = scanner.nextLong();
        scanner.nextLine();

        Client client = billeterieDAO.getClientByID(clientId);
        billeterieDAO.delete(client);
    }

    // EVENTS
    private void eventDealer(MenuType menuType) {
        switch (menuType){
            case CREATE_MENU -> displayCreateEvent();
            case READ_ALL_MENU -> displayGetAllEvent();
            case READ_BY_ID_MENU -> displayGetEventByID(READ_BY_ID_ACTION);
            case UPDATE_MENU -> displayUpdateEvent();
            case DELETE_MENU -> displayDeleteEvent();
        }
    }

    private void displayCreateEvent() {

        System.out.println("Quel est son nom ?");
        String name = scanner.nextLine();

        System.out.println("Quel est son nombre de place ?");
        int placeNbr = scanner.nextInt();

        Date date = createDate();

        System.out.println("à quel heure a t'il lieu ? (format 13h45)");
        String heureString = scanner.nextLine();


        Address address = createAddress();

        Event event = new Event(name, placeNbr, date, heureString, address);
        billeterieDAO.create(event);
    }

    private void displayGetAllEvent() {
        List<Event> clientList = billeterieDAO.getAllEvent();
        for (Event event : clientList)
            System.out.println(event);
    }

    private Event displayGetEventByID(DatabaseAction databaseAction) {
        String actionString = getActionString(databaseAction);
        System.out.println("Quel est l'id de l'event que vous voulez " + actionString + " ?");
        long eventId = scanner.nextLong();
        scanner.nextLine();

        Event event = billeterieDAO.getEventByID(eventId);
        System.out.println(event);

        return event;
    }

    private void displayUpdateEvent() {
        Event event = displayGetEventByID(UPDATE_ACTION);

        System.out.println("Quel est l'élement que vous voulez changer ? (nom, places, date, heure, adresse)");
        String columnChoice = scanner.nextLine();

        System.out.println("saisissez son " + columnChoice);
        switch(columnChoice){
            case "nom":
                String newName = scanner.nextLine();
                event.setName(newName);
                break;
            case "places":
                int newSurname = scanner.nextInt();
                scanner.nextLine();
                event.setPlaceNbr(newSurname);
                break;
            case "date":
                Date date = createDate();
                event.setDate(date);
                break;
            case "heure":
                String heure = scanner.nextLine();
                event.setHeure(heure);
                break;
            case "adresse":
                Address newAdress = createAddress();
                event.setAdress(newAdress);
        }
        billeterieDAO.update(event);
    }

    private void displayDeleteEvent() {
        System.out.println("Quel l'id du client que vous voulez supprimer ?");
        long clientId = scanner.nextLong();
        scanner.nextLine();

        Client client = billeterieDAO.getClientByID(clientId);
        billeterieDAO.delete(client);
    }

    // TICKETS
    private void ticketDealer(MenuType menuType){
        switch (menuType){
            case CREATE_MENU -> displayCreateTicket();
            case READ_ALL_MENU -> displayGetAllTicket();
            case READ_BY_ID_MENU -> displayGetTicketByID(READ_BY_ID_ACTION);
            case UPDATE_MENU -> displayUpdateTicket();
            case DELETE_MENU -> displayDeleteTicket();
        }
    }

    private void displayCreateTicket() {
        System.out.println("Quel est l'id de l'évent ?");
        long eventId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Quel est l'id de l'évent ?");
        long clientId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("quel est son type ? (standard, gold ou VIP)");
        String type = scanner.nextLine();

        int userPlaceNumber = billeterieDAO.listTicketsFromEvent(eventId).size();
        Event event = billeterieDAO.getEventByID(eventId);
        Client client = billeterieDAO.getClientByID(clientId);

        Ticket ticket = new Ticket(userPlaceNumber, type, client, event);
        billeterieDAO.create(ticket);
    }

    private void displayGetAllTicket() {
        List<Ticket> ticketList = billeterieDAO.getAllTickets();
        for (Ticket ticket : ticketList)
            System.out.println(ticket);
    }

    private Ticket displayGetTicketByID(DatabaseAction databaseAction) {
        String actionString = getActionString(databaseAction);
        System.out.println("Quel est l'id du ticket que vous voulez " + actionString + " ?");
        long ticketId = scanner.nextLong();
        scanner.nextLine();

        Ticket ticket = billeterieDAO.getTicketByID(ticketId);
        System.out.println(ticket);
        return ticket;
    }

    private void displayUpdateTicket() {
        Ticket ticket = displayGetTicketByID(UPDATE_ACTION);

        System.out.println("Quel est l'élement que vous voulez changer ? (place, type, client, event)");
        String columnChoice = scanner.nextLine();

        System.out.print("saisissez son " + columnChoice);
        switch(columnChoice){
            case "nom":
                int newClientPlaceNumber = scanner.nextInt();
                scanner.nextLine();
                ticket.setUserPlaceNumber(newClientPlaceNumber);
                break;
            case "type":
                String newType = scanner.nextLine();  //TODO make menu for type
                scanner.nextLine();
                ticket.setType(newType);
                break;
            case "client":
                Client client = displaygetClientByID(READ_BY_ID_ACTION);
                ticket.setClient(client);  // TODO remove old_client-ticket link
                break;
            case "event":
                Event event = displayGetEventByID(READ_BY_ID_ACTION); // TODO remove old_event-ticket link
                ticket.setEvent(event);
                break;
        }
        System.out.println();
        billeterieDAO.update(ticket);
    }

    private void displayDeleteTicket() {
        System.out.println("Quel l'id du ticket que vous voulez supprimer ?");
        long ticketId = scanner.nextLong();
        scanner.nextLine();

        Ticket ticket = billeterieDAO.getTicketByID(ticketId);
        billeterieDAO.delete(ticket);
    }

    private Address createAddress(){
        System.out.println("Quel est son addresse ?");
        System.out.println("ville");
        String city = scanner.nextLine();

        System.out.println("Voie");
        String street = scanner.nextLine();

        System.out.println("numéro de rue");
        int streetNbr = scanner.nextInt();
        scanner.nextLine();
        return new Address(streetNbr, street, city);
    }

    private Date createDate(){
        System.out.println("Quel est sa date ? (format 13/01/2001)");
        String dateString = scanner.nextLine();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = formatter.parse(dateString);
            System.out.println("Converted Date: " + date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format MM/dd/yyyy.");
            throw new RuntimeException(e);
        }
        return date;
    }

    private String displayMainMenu(){
        return headline("Principal") +
                "\nVeuillez sélectionner un menu\n" +
                "1. Créer.\n" +
                "2. Lire.\n" +
                "3. Mettre à jour.\n" +
                "4. Delete.\n" +
                "0. Quitter le programme";
    }

    private String displayMenu(MenuType menuType){
        if (menuType == MAIN_MENU)
            return displayMainMenu();

        if (menuType == READ_MENU)
            return headline("lire") +
                    "1. Tout récupérer.\n" +
                    "2. Récupérer par id.\n" +
                    "0. Retour au menu principal";

        String actionToDisplay;
        switch (menuType){
            case CREATE_MENU -> actionToDisplay = "créer";
            case READ_ALL_MENU -> actionToDisplay = "tout récupérer";
            case READ_BY_ID_MENU -> actionToDisplay = "sélectionner";
            case UPDATE_MENU -> actionToDisplay = "mise à jour";
            case DELETE_MENU -> actionToDisplay = "supprimer";
            default -> throw new WrongSearchChoiceException("Unknown choice by user");
        }
        return headline(actionToDisplay) +
                "1. Client.\n" +
                "2. Evènement.\n" +
                "3. Ticket.\n" +
                "0. Retour au menu principal";
    }

    private String headline(String headlineString){
        return "\n=== Menu " + headlineString + " ===\n\n";
    }

    private String getActionString(DatabaseAction action){
        switch (action){
            case CREATE_ACTION -> {return "créer";}
            case READ_ACTION -> {return "lire";}
            case READ_ALL_ACTION -> {return "tout lire";}
            case READ_BY_ID_ACTION -> {return "lire par ID";}
            case UPDATE_ACTION -> {return "changer";}
            case DELETE_ACTION -> {return "supprimer";}
            default -> throw new WrongSearchChoiceException("Unknown choice in getActionString: " + action);
        }
    }

}