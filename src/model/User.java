package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
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

    public void setName(String name) {this.name = name;}
    public void setAddress(Address address) {this.address = address;}
    public void setAge(int age) {this.age = age;}
    public void setCreditScore(int creditScore) {this.creditScore = creditScore;}

    public User(String name, Address address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.creditScore = -1;
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
