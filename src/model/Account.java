package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Account implements Serializable, IsPaymentMethod {
    private String accountName;
    private String owner;
    private long accNumber;
    private static long nextAccNumber = 100000L;
    private double balance;
    private ArrayList<Operation> operations;

    public double getBalance() {return balance;}
    public long getAccNumber() {return accNumber;}
    public String getAccountName() {return accountName;}
    public ArrayList<Operation> getOperations() {return operations;}

    public Account(String owner, String accountName) {
        this.owner = owner;
        this.accountName = accountName;
        accNumber = nextAccNumber++;
        balance = 0.00;
        operations = new ArrayList<>();
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            operations.add(new Operation(LocalDateTime.now(), amount, Operation.OperationType.DEPOSIT));
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            operations.add(new Operation(LocalDateTime.now(), amount, Operation.OperationType.WITHDRAW));
            return true;
        }
        return false;
    }
}
