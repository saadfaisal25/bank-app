import java.util.ArrayList;

public class User {
    private String name;
    private Address address;
    private int age;
    private int creditScore;
    private ArrayList<Account> accounts;
    private ArrayList<CreditCard> creditCards;

    public String getName() {return name;}
    public Address getAddress() {return address;}
    public int getAge() {return age;}
    public int getCreditScore() {return creditScore;}

    public User(String name, Address address, int age, int creditScore) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.creditScore = creditScore;
    }

    public boolean openAccount(Account account) {
        return accounts.add(account);
    }

    public boolean closeAccount(Account account) {
        return accounts.remove(account);
    }

    public boolean openCreditCard(CreditCard card) {
        return creditCards.add(card);
    }

    public boolean closeCreditCard(CreditCard card) {
        return creditCards.remove(card);
    }
}
