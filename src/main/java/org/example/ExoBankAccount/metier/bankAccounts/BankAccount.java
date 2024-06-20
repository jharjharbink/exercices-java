package org.example.ExoBankAccount.metier.bankAccounts;

import org.example.ExoBankAccount.metier.*;

import java.util.ArrayList;

import static org.example.ExoBankAccount.metier.Status.*;

public class BankAccount {
    private Client client;
    private double balance = 0;
    private ArrayList<Operation> operationList;

    public BankAccount(Client client) {
        this.client = client;
        this.balance = 0;
        operationList = new ArrayList<>();
    }

    public void operation(Status operationStatus, int amount){
        int operationNbr = operationList.size();

        Operation currentOperation = new Operation(operationNbr, amount, operationStatus);
        addOperation(currentOperation);

        if (operationStatus == WITHDRAWAL){
            amount = -amount;
        }
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Operation> getOperationList() {
        return operationList;
    }

    public void addOperation(Operation currentOperation){
        operationList.add(currentOperation);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                ", client=" + client +
                ", operationList=" + operationList +
                '}';
    }
}
