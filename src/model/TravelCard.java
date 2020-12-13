package model;

public class TravelCard extends CreditCard {
    private final double annualFee;
    private double airlineDollars;
    private final double dollarRate;
    private double totalSpent;

    public double getAirlineDollars() {return airlineDollars;}

    public TravelCard(String owner) {
        super(owner, "Travel Card");
        annualFee = 100.00;
        airlineDollars = 0.00;
        dollarRate = 0.015;
        totalSpent = 0.00;
    }

    // withdraw and add airline dollars using the airline dollar rate
    public boolean withdraw(double amount) {
        if (super.withdraw(amount)) {
            airlineDollars += amount * dollarRate;
            totalSpent += amount;
            claimDollars();
            return true;
        }
        return false;
    }

    // for every $5000 spent, add 400 bonus airline dollars
    public void claimDollars() {
        if (totalSpent >= 5000) {
            airlineDollars += ( ((totalSpent - (totalSpent % 5000)) / 5000) * 400);
            totalSpent = totalSpent % 5000;
        }
    }

    // withdraw the annual fee
    public void annualProcess() {
        super.withdraw(annualFee);
    }
}
