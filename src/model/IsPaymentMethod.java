package model;

public interface IsPaymentMethod {
    boolean withdraw(double amount);
    boolean deposit(double amount);
    boolean monthlyProcess();
}
