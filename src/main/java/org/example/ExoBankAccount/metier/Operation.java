package org.example.ExoBankAccount.metier;

public class Operation {
    private int operationNbr;
    private double amount;
    private Status status;

    public Operation(int operationNbr, double amount, Status status) {
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
