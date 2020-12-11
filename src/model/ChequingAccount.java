package model;

public class ChequingAccount extends Account {
    private double monthlyFee;

    public ChequingAccount(String owner) {
        super(owner, "Chequing Account");
        monthlyFee = 10.00;
    }

    public boolean monthlyProcess() {
        return super.withdraw(monthlyFee);
    }
}
