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

    private static User instance;

    public static User getInstance() {
        return instance;
    }

    public static void setInstance(User user) {
        instance = user;
    }

    public User(int customerID, String firstName, String lastName, String phone, String password, String address) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phone;
        this.password = password;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public int getCustomerID() {
        return customerID;
    }
}

