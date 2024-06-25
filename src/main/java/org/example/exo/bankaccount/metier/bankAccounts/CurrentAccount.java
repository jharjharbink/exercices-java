package org.example.exo.bankaccount.metier.bankAccounts;

import org.example.exo.bankaccount.metier.Client;
import org.example.exo.bankaccount.metier.Operation;
import org.example.exo.bankaccount.metier.StatusOperation;

public class CurrentAccount extends BankAccount{

    public CurrentAccount(Client client) {
        super(client);
    }

    @Override
    public void operation(StatusOperation operationStatus, double amount){

        Operation currentOperation = new Operation(amount, operationStatus);
        addOperation(currentOperation);

        if (operationStatus == StatusOperation.WITHDRAWAL){
            amount = -amount;
        }
        addToBalance(amount);
    }

}
