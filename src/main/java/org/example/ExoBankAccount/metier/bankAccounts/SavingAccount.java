package org.example.ExoBankAccount.metier.bankAccounts;

import org.example.ExoBankAccount.metier.*;

import static org.example.ExoBankAccount.metier.Status.DEPOSIT;
import static org.example.ExoBankAccount.metier.Status.WITHDRAWAL;

public class SavingAccount extends BankAccount {
    double interestRate = 0.05;

    public SavingAccount(Client client) {
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
    
    public void applyInterest(){
        double amount = getBalance() * (1 + interestRate);
        int operationNbr = getClient().getAccounts().size();
        Operation currentOperation = new Operation(operationNbr, amount, DEPOSIT);
    }
    
}
