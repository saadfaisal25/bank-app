package model;

public class RewardsCard extends CreditCard {
    private int points;

    public int getPoints() {return points;}

    public RewardsCard(String owner) {
        super(owner, "Rewards Card");
        points = 0;
    }

    // for every 1 dollar spent, add 1 reward point
    public boolean withdraw(double amount) {
        if (super.withdraw(amount)) {
            points += ((Double) amount).intValue();
            return true;
        }
        return false;
    }
}
