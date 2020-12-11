package model;

public class CashBackCard extends CreditCard {
    private double cashBackRate;
    private double totalSpent;

    public CashBackCard(String owner) {
        super(owner, "Cash Back Card");
        cashBackRate = 0.01;
        totalSpent = 0.00;
    }

    public boolean withdraw(double amount) {
        if (super.withdraw(amount)) {
            claimCashBack();
            return true;
        }
        return false;
    }

    public void claimCashBack() {
        super.deposit( ((Double) (totalSpent/100)).intValue() * (cashBackRate * 100) );
    }
}
