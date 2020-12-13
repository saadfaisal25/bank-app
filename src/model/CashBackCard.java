package model;

public class CashBackCard extends CreditCard {
    private double cashBackRate;
    private double totalSpent;

    public CashBackCard(String owner) {
        super(owner, "Cash Back Card");
        cashBackRate = 0.01;
        totalSpent = 0.00;
    }

    // if the amount can be withdrawn, check to see if eligible for a cash back
    public boolean withdraw(double amount) {
        if (super.withdraw(amount)) {
            claimCashBack();
            return true;
        }
        return false;
    }

    // for every $100 spent, deposit $1 as a cash back
    public void claimCashBack() {
        if (totalSpent >= 100) {
            super.deposit(( ((totalSpent - (totalSpent % 100))) / 100) * (cashBackRate * 100));
            totalSpent = totalSpent % 100;
        }
    }
}
