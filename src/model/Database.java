package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

public class Database {
    private String directory;
    private String userDirectory;
    private HashMap<String, String> users;

    public Database(String directory) {
        this.directory = directory;
        userDirectory = directory+"users.txt";
        users = new HashMap<>();

        File file = new File(userDirectory);
        // if file doesn't exist, create it, otherwise read and load file contents
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error: IO Exception when creating user file");
            }
        } else {
            // load usernames and passwords into users hashmap;
            try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                while (in.ready()) {
                    String[] line = in.readLine().split(":");
                    users.put(line[0], line[1]);
                }
            } catch (IOException e) {
                System.out.println("Error: IO Exception when initializing database");
            }
        }
    }

    // return boolean whether or not the username and password combination exists in the users hashmap
    public boolean authenticate(String username, String password) {
        if (users.containsKey(username)) {
            return users.get(username).equals(password);
        } else {
            return false;
        }
    }

    // add the username and password combination to the users hashmap
    // write the username + password to the users.txt file
    // create a new file with the username and write the user object to that file
    public boolean addUser(PrintWriter out, String username, String password, String name,
                           Address address, String age) throws IOException {
        if (users.containsKey(username)) {
            return false;
        }

        users.put(username, password);

        out.println(username+":"+password);

        File file = new File(directory+username+".txt");
        file.createNewFile();
        ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(file));
        objOut.writeObject(new User(username, name, address, age));
        objOut.close();

        System.out.println(users);

        return true;
    }

    // read the user object from the given file and return it
    public User getUserFromData(ObjectInputStream objIn) throws IOException, ClassNotFoundException {
        User user = (User) objIn.readObject();
        return user;
    }

    // take a user object and write it to the given file
    public void rewriteUser(ObjectOutputStream objOut, User user) throws IOException {
        objOut.writeObject(user);
    }
}
