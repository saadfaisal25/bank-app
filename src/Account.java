public class Account {
    private int accNumber;
    private static int nextAccNumber = 100000;
    private double balance;

    public Account() {
        accNumber = nextAccNumber++;
    }
}
