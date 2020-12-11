package model;

public class TravelCard extends CreditCard {
    private double annualFee;
    private double airlineDollars;
    private double dollarRate;
    private double totalSpent;

    public double getAirlineDollars() {return airlineDollars;}

    public TravelCard(String owner) {
        super(owner, "Travel Card");
        annualFee = 100.00;
        airlineDollars = 0.00;
        dollarRate = 0.015;
        totalSpent = 0.00;
    }

    public boolean withdraw(double amount) {
        if (super.withdraw(amount)) {
            airlineDollars += amount * dollarRate;
            totalSpent += amount;
            claimDollars();
            return true;
        }
        return false;
    }

    public void claimDollars() {
        airlineDollars += ((Double) (totalSpent/5000)).intValue() * 400;
    }

    public void annualProcess() {
        super.withdraw(annualFee);
    }
}
