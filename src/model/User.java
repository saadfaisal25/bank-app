package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String name;
    private Address address;
    private String age;
    private int creditScore;
    private ArrayList<Account> accounts;
    private ArrayList<CreditCard> creditCards;

    // getter and setter methods
    public String getUsername() {return username;}
    public String getName() {return name;}
    public Address getAddress() {return address;}
    public String getAge() {return age;}
    public int getCreditScore() {return creditScore;}
    public ArrayList<Account> getAccounts() {return accounts;}
    public ArrayList<CreditCard> getCreditCards() {return creditCards;}

    public void setName(String name) {this.name = name;}
    public void setAddress(Address address) {this.address = address;}
    public void setAge(String age) {this.age = age;}
    public void setCreditScore(int creditScore) {this.creditScore = creditScore;}

    public User(String username, String name, Address address, String age) {
        this.username = username;
        this.name = name;
        this.address = address;
        this.age = age;
        creditScore = -1;
        accounts = new ArrayList<>();
        creditCards = new ArrayList<>();
    }

    // return a boolean value whether the given account was added or removed successfully
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
