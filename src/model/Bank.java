package model;

import java.io.IOException;
import java.io.ObjectInputStream;
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

    public User createNewUser(PrintWriter out, String username, String password, String name,
                              Address address, int age, int credit) throws IOException {
        db.addUser(out, username, password, name, address, age, credit);
        return new User(name, address, age, credit);
    }

    public boolean authenticate(String username, String password) {
        return db.authenticate(username, password);
    }

    public void setUserFromData(ObjectInputStream objIn) throws IOException, ClassNotFoundException {
        this.user = db.getUserFromData(objIn);
    }
}
