import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<User> users;

    public String getName() {return name;}
    public ArrayList<User> getUsers() {return users;}
    public User[] getUsersArray() {return users.toArray(new User[users.size()]);}

    public Bank(String name) {
        this.name = name;
        users = new ArrayList<>();
    }

    public boolean createUser() {
        return true;
    }

    public boolean deleteUser() {
        return true;
    }

}
