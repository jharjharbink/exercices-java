package org.example.ExoBankAccount;

import org.example.ExoBankAccount.metier.Operation;
import org.example.ExoBankAccount.metier.Status;
import org.example.ExoBankAccount.metier.bankAccounts.*;
import org.example.ExoBankAccount.metier.Client;
import org.example.ExoBankAccount.exceptions.ReturnToMainLoop;

import static org.example.ExoBankAccount.metier.Status.*;
import static org.example.ExoBankAccount.Menus.*;
import static org.example.ExoBankAccount.metier.bankAccounts.AccountType.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Ihm {
    Client client;
    Scanner scanner;

    public Ihm(Client client) {
        this.client = client;
        this.scanner = new Scanner(System.in);
    }

    public void mainLoop() throws Exception {

        boolean continueLoop = true;

        while(continueLoop){
            try{
                System.out.println(displayMainMenu());
                int userChoiceMainMenu = loopChoice(MAIN_MENU);

                switch(userChoiceMainMenu){
                    case 1: displayBankAccounts(); break;
                    case 2: bankAccountCreationTunnel(); break;
                    case 3: operationCreationTunnel(DEPOSIT); break;
                    case 4: operationCreationTunnel(WITHDRAWAL); break;
                    case 5: displayOperationAndBalance(); break;
                    case 0:
                        displayGoodBye();
                        continueLoop = false;
                        break;
                    default:
                        throw new Exception("Unknown bank Account type");
                }
            } catch (ReturnToMainLoop e){}
        }
    }

    private int loopChoice(Menus menuType) throws Exception {
        int userChoice;
        int upperLimit = defineUpperLimitForLoopChoice(menuType);

        while (true) {
            try {
                userChoice = Integer.parseInt(scanner.nextLine());

                if (userChoice < 0 || userChoice > upperLimit) {
                    throw new Exception("Vous devez rentrer un chiffre compris entre 0 et " + upperLimit + ".");
                }

            } catch (NumberFormatException e) {
                System.out.print("Saisie incorrecte ! (pas un entier) ");
                continue;

            } catch (Exception e) {
                System.out.print("Saisie incorrecte ! (pas dans les choix disponibles) ");
                continue;
            }
            break;
        }
        return userChoice;
    }

    private int defineUpperLimitForLoopChoice(Menus menuType) throws Exception {
        switch(menuType){
            case MAIN_MENU: return 5;
            case BANK_ACCOUNT_TYPE: return 3;
            case BANK_ACCOUNT: return client.getAccounts().size();
            case DEPOT_AMOUNT: return 1_000_000;
            default:
                throw new Exception("Unknown menu choice.");
        }
    }

    private void displayBankAccounts(){

        List<BankAccount> currentClientAccounts = client.getAccounts();
        if (currentClientAccounts.isEmpty()){
            System.out.println("Vous n'avez pas de comptes");
        } else {
            System.out.println("Voici la liste de vos comptes: ");

            int i = 1;
            for (BankAccount clientBankAccount : client.getAccounts()){
                System.out.println("\t Compte n°" + i + " " + clientBankAccount) ;
                i++;
            }
        }

    }

    private void bankAccountCreationTunnel() throws Exception, ReturnToMainLoop {

        System.out.println(displayBankAccountMenu());
        int userChoiceBankAccount = loopChoice(BANK_ACCOUNT_TYPE);
        AccountType accountType = defineBankAccountType(userChoiceBankAccount);

        BankAccount newBankAccount = client.createAccount(accountType);

        System.out.println("Compte " + getAccountTypeString(accountType) + " créer.");
        System.out.println("Vous devez faire une première opération.");
        performOperation(DEPOSIT, newBankAccount);
    }

    private AccountType defineBankAccountType(int userChoice) throws Exception, ReturnToMainLoop {
        switch (userChoice) {
            case 1: return CURRENT;
            case 2: return SAVING;
            case 3: return PAYEMENT;
            case 0: throw new ReturnToMainLoop();

            default:
                throw new Exception("Unknown bank Account type");
        }
    }

    private void operationCreationTunnel(Status operationStatus) throws Exception {
        BankAccount currentBankAccount = selectBankAccount();
        performOperation(operationStatus, currentBankAccount);
    }

    private BankAccount selectBankAccount() throws Exception {
        displayBankAccounts();

        System.out.println("Veuillez choisir un compte");
        int clientBankAccountNbr = loopChoice(BANK_ACCOUNT);

        BankAccount currentBankAccount = client.getAccounts().get(clientBankAccountNbr - 1);  //Bank account selection begin at 1 so we remove this offset
        System.out.println("Voici le compte sélectionné --> " + currentBankAccount);

        return currentBankAccount;
    }

    public void performOperation(Status operationStatus, BankAccount bankAccount) throws Exception {
        System.out.println("Veuillez choisir un montant de " + getOperationTypeString(operationStatus));
        double depotAmount = loopChoice(DEPOT_AMOUNT);
        bankAccount.operation(operationStatus, depotAmount);
    }

    private void displayOperationAndBalance() throws Exception {
        BankAccount currentBankAccount = selectBankAccount();

        System.out.println("Le montant sur votre compte est de: " + currentBankAccount.getBalance());
        System.out.println("Voici la liste des opérations: ");
        for (Operation currentBankAccountOperations : currentBankAccount.getOperationList()){
            System.out.println(currentBankAccountOperations);
        }
    }

    private void displayGoodBye(){
        System.out.println("Au revoir !");
    }

    private String displayMainMenu(){
        return headline("Menu Principal") +
                "1. Lister les comptes bancaire\n" +
                "2. Créer un compte bancaire\n" +
                "3. Effectuer un depot\n" +
                "4. Effectuer un retrait\n" +
                "5. Afficher les opérations\n" +
                "0. Quitter le programme";
    }

    private String displayBankAccountMenu(){
        return headline("Creattion de Compte") +
                "1. Créer un compte courant\n" +
                "2. Créer un compte épargne\n" +
                "3. Créer un compte payant\n" +
                "0. Annuler la création de compte";
    }

    private String headline(String headlineString){
        return "\n=== " + headlineString + " ===\n\n";
    }

    private String getOperationTypeString(Status operationStatus){  // TODO put this method in Enum
        if (operationStatus == DEPOSIT) {
            return "depot";
        }
        else {
            return "retrait";
        }
    }

    private String getAccountTypeString(AccountType accountType){   // TODO put this method in Enum
        if (accountType == CURRENT) {
            return "courant";
        }
        else if (accountType == PAYEMENT) {
            return "de payement";
        } else {
            return "d'épargne";
        }
    }

}
