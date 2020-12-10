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

    //
    public boolean authenticate(String username, String password) {
        if (users.containsKey(username)) {
            return users.get(username).equals(password);
        } else {
            return false;
        }
    }

    public boolean addUser(PrintWriter out, String username, String password, String name,
                           Address address, int age, int creditScore) throws IOException {
        if (users.containsKey(username)) {
            return false;
        }

        users.put(username, password);

        //PrintWriter out = new PrintWriter(new FileWriter(directory+"users.txt", true));
        out.println(username+":"+password);

        File file = new File(directory+username+".txt");
        file.createNewFile();
        ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(file));
        objOut.writeObject(new User(name, address, age, creditScore));
        objOut.close();

        System.out.println(users);

        return true;
    }

    public User getUserFromData(ObjectInputStream objIn) throws IOException, ClassNotFoundException {
        //File file = new File(directory+username+".txt");
        //BufferedReader in = new BufferedReader(new FileReader(file));

        User user = (User) objIn.readObject();
        return user;
    }
}
