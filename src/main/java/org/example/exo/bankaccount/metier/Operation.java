package org.example.exo.bankaccount.metier;

public class Operation {
    private static int operationNbr;

    private int id;
    private double amount;
    private StatusOperation status;

    public Operation(double amount, StatusOperation status) {
        this.id = ++operationNbr;
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
