package models;

import database.DatabaseConnection;
import java.sql.*;

public class User {
    private int customerID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String password;

    public User(int customerID, String firstName, String lastName, String phone, String password, String address) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phone;
        this.password = password;
        this.address = address;
    }
}

