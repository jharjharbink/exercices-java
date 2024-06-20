package org.example.ExoBankAccount.metier.bankAccounts;

import org.example.ExoBankAccount.metier.*;

import static org.example.ExoBankAccount.metier.Status.WITHDRAWAL;

public class CurrentAccount extends BankAccount{

    public CurrentAccount(Client client) {
        super(client);
    }

    @Override
    public void operation(Status operationStatus, double amount){
        int operationNbr = this.getOperationList().size();

        Operation currentOperation = new Operation(operationNbr, amount, operationStatus);
        addOperation(currentOperation);

        if (operationStatus == WITHDRAWAL){
            amount = -amount;
        }
        addToBalance(amount);
    }

}
