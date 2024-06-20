package org.example.ExoBankAccount.metier.bankAccounts;

import org.example.ExoBankAccount.metier.*;

public class PayementAccount extends BankAccount {
    double operationCost = 1;

    public PayementAccount(Client client) {
        super(client);
    }


}
