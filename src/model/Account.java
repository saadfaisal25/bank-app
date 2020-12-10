package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {
    private int accNumber;
    private static int nextAccNumber = 100000;
    private double balance;
    private ArrayList<Operation> operations;

    public Account() {
        accNumber = nextAccNumber++;
        balance = 0.00;
        operations = new ArrayList<>();
    }

}
