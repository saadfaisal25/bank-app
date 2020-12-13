package model;

public class SavingsAccount extends Account {
    private double withdrawFee;
    private double interestRate;

    public SavingsAccount(String owner) {
        super(owner, "Savings Account");
        withdrawFee = 3.00;
        interestRate = 0.01;
    }

    // add withdraw fee to the amount
    public boolean withdraw(double amount) {
        return super.withdraw(amount + withdrawFee);
    }

    // add interest rate to balance
    public boolean monthlyProcess() {
        return super.deposit(getBalance() * interestRate);
    }
}
