package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class CreditCard implements Serializable, IsPaymentMethod {
    private String cardName;
    private long cardNumber;
    private static long nextCardNumber = 7615987627810000L;
    private String owner;
    private double creditLimit;
    private double balance;
    protected double monthlyAPR;
    private ArrayList<Operation> operations;

    public double getBalance() {return balance;}
    public long getCardNumber() {return cardNumber;}
    public String getCardName() {return cardName;}
    public ArrayList<Operation> getOperations() {return operations;}

    public CreditCard(String owner, String cardName) {
        this.owner = owner;
        this.cardName = cardName;
        cardNumber = nextCardNumber++;
        creditLimit = 20000.00;
        balance = 0.00;
        monthlyAPR = 0.0125;
        operations = new ArrayList<>();
    }

    // subtract amount from balance and add to operations
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance -= amount;
            operations.add(new Operation(LocalDateTime.now(), amount, Operation.OperationType.DEPOSIT));
            return true;
        }
        return false;
    }

    // add amount to balance and add to operations
    public boolean withdraw(double amount) {
        if (amount > 0) {
            balance += amount;
            operations.add(new Operation(LocalDateTime.now(), amount, Operation.OperationType.WITHDRAW));
            return true;
        }
        return false;
    }

    // any outstanding balance at the end of the month is charged interest
    public boolean monthlyProcess() {
        return withdraw(getBalance() * monthlyAPR);
    }

    public String toString() {
        return cardName + " with a balance of $" + balance + "\nCard Number: " + cardNumber;
    }
}
