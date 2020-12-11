package model;

public class SavingsAccount extends Account {
    private double withdrawFee;
    private double interestRate;

    public SavingsAccount(String owner) {
        super(owner, "Savings Account");
        withdrawFee = 3.00;
        interestRate = 0.01;
    }

    public boolean withdraw(double amount) {
        return super.withdraw(amount + withdrawFee);
    }

    public boolean monthlyProcess() {
        return super.deposit(getBalance() * interestRate);
    }
}
