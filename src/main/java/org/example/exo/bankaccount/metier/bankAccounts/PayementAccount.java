package org.example.exo.bankaccount.metier.bankAccounts;

import org.example.exo.bankaccount.metier.Client;
import org.example.exo.bankaccount.metier.Operation;
import org.example.exo.bankaccount.metier.StatusOperation;

public class PayementAccount extends BankAccount {
    int operationCost = 1;

    public PayementAccount(Client client) {
        super(client);
    }

    @Override
    public void operation(StatusOperation operationStatus, double amount){

        amount -= operationCost;

        Operation currentOperation = new Operation(amount, operationStatus);
        addOperation(currentOperation);

        if (operationStatus == StatusOperation.WITHDRAWAL){
            amount = -amount;
        }
        addToBalance(amount);
    }
}
