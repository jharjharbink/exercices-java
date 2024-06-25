package org.example.exo.bankaccount.metier;

import org.example.exo.bankaccount.metier.bankAccounts.*;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String surname;
    private int id;
    private List<BankAccount> accounts;
    private String phoneNumber;

    public Client(String name, String surname, int id, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.accounts = new ArrayList<BankAccount>();
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void addBankAccount(BankAccount bankAccount){
        accounts.add(bankAccount);
    }

    public BankAccount createAccount(AccountType accountType){
        BankAccount newAccount;
        switch(accountType){
            case CURRENT:
                newAccount = new CurrentAccount(this);
                break;
            case PAYEMENT:
                newAccount = new PayementAccount(this);
                break;
            case SAVING:
                newAccount = new SavingAccount(this);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + accountType);
        }
        addBankAccount(newAccount);
        return newAccount;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


}
