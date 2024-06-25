package org.example.exo.bankaccount.metier.bankAccounts;

import org.example.exo.bankaccount.metier.Client;
import org.example.exo.bankaccount.metier.Operation;
import org.example.exo.bankaccount.metier.StatusOperation;

public class SavingAccount extends BankAccount {
    double interestRate = 0.05;

    public SavingAccount(Client client) {
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
    
    public void applyInterest(){
        double amount = getBalance() * (1 + interestRate);
        addOperation(new Operation(amount, StatusOperation.DEPOSIT));
    }
    
}
