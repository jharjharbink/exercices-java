package org.example.ExoBankAccount.metier;

public class Operation {
    private int operationNbr;
    private int amount;
    private Status status;

    public Operation(int operationNbr, int amount, Status status) {
        this.operationNbr = operationNbr;
        this.amount = amount;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operationNbr=" + operationNbr +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
