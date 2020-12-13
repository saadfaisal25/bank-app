package model;

public interface IsPaymentMethod {
    // necessary behavior of payment methods
    boolean withdraw(double amount);
    boolean deposit(double amount);
    boolean monthlyProcess();
}
