package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class Bank {
    private String name;
    private String directory;
    private Database db;
    private User user;

    public String getName() {return name;}
    public User getUser() {return user;}

    public Bank(String name, String directory) {
        this.name = name;
        this.directory = directory;
        db = new Database(this.directory);
        user = null;
    }

    // create a new user in the database with the given parameters
    public boolean createNewUser(PrintWriter out, String username, String password, String name,
                              Address address, String age) throws IOException {
        return db.addUser(out, username, password, name, address, age);
    }

    // return a boolean whether that username and password combination exists in the database
    public boolean authenticate(String username, String password) {
        return db.authenticate(username, password);
    }

    public void setUserFromData(ObjectInputStream objIn) throws IOException, ClassNotFoundException {
        this.user = db.getUserFromData(objIn);
    }

    // reset user value to null
    public void unsetUser() {
        this.user = null;
    }

    // save the user data to the database
    public void saveUserToData(ObjectOutputStream objOut) throws IOException {
        db.rewriteUser(objOut, this.user);
    }

    // open and close accounts and credit cards for the current user
    public boolean openChequingAccount() {
        if (user != null) {
            Account account = new ChequingAccount(user.getName());
            return user.openAccount(account);
        }
        return false;
    }

    public boolean openSavingsAccount() {
        if (user != null) {
            Account account = new SavingsAccount(user.getName());
            return user.openAccount(account);
        }
        return false;
    }

    public boolean openTravelCard(int creditScore) {
        if (user != null) {
            user.setCreditScore(creditScore);
            if (user.getCreditScore() >= 750) {
                CreditCard card = new TravelCard(user.getName());
                return user.openCreditCard(card);
            }
        }
        return false;
    }

    public boolean openRewardsCard(int creditScore) {
        if (user != null) {
            user.setCreditScore(creditScore);
            if (user.getCreditScore() >= 650) {
                CreditCard card = new RewardsCard(user.getName());
                return user.openCreditCard(card);
            }
        }
        return false;
    }

    public boolean openCashBackCard(int creditScore) {
        if (user != null) {
            user.setCreditScore(creditScore);
            if (user.getCreditScore() >= 700) {
                CreditCard card = new CashBackCard(user.getName());
                return user.openCreditCard(card);
            }
        }
        return false;
    }

    public boolean closeAccount(Account account) {
        return user.closeAccount(account);
    }

    public boolean closeCard(CreditCard card) {
        return user.closeCreditCard(card);
    }
}
