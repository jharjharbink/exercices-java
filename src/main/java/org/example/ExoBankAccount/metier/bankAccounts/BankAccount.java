package org.example.ExoBankAccount.metier.bankAccounts;

import org.example.ExoBankAccount.metier.*;

import java.util.ArrayList;

import static org.example.ExoBankAccount.metier.Status.*;

public abstract class BankAccount {
    private Client client;
    private double balance = 0;
    private ArrayList<Operation> operationList;

    public BankAccount(Client client) {
        this.client = client;
        this.balance = 0;
        operationList = new ArrayList<>();
    }

    public abstract void operation(Status operationStatus, double amount);

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addToBalance(double amount){
        setBalance(getBalance() + amount);
    }

    public ArrayList<Operation> getOperationList() {
        return operationList;
    }

    public void addOperation(Operation currentOperation){
        operationList.add(currentOperation);
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "balance=" + balance +
                ", client=" + client.getSurname() + " " + client.getName() +
                ", operationList=" + operationList +
                '}';
    }
}
