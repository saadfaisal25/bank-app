package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Operation implements Serializable {
    private LocalDateTime localDateTime;
    private double amount;
    private OperationType type;

    public LocalDateTime getLocalDateTime() {return localDateTime;}
    public double getAmount() {return amount;}
    public OperationType getType() {return type;}

    public Operation(LocalDateTime localDateTime, double amount, OperationType type) {
        this.localDateTime = localDateTime;
        this.amount = amount;
        this.type = type;
    }

    public String toString() {
        if (type.equals(OperationType.DEPOSIT)) {
            return "Deposit of amount $" + amount + " at " + localDateTime;
        }
        return "Withdrawal of amount $" + amount + " at " + localDateTime;
    }

    enum OperationType {
        DEPOSIT,
        WITHDRAW
    }
}
