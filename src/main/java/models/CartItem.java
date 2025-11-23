package models;

import java.util.List;

public class CartItem {

    private String category;   // "Large Pepperoni, Thin Crust"
    private String name;
    private int quantity;
    private double price; // Final Price
//    private MenuItem item;

    // Pizza customizations
    private String size;
    private String crust;
    private String sauce;
    private List<String> toppings;

    // Pizza Constructor
    public CartItem(String name, String category, int quantity, double price, String size, String crust, String sauce, List<String> toppings){
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.size = size;
        this.crust = crust;
        this.sauce = sauce;
        this.toppings = toppings;
    }

    // Beverage/Dessert Constructor
    public CartItem(String name, String category, int quantity, double price) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }
    public String getName() {return name;}
    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {this.price = price;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public void setSize(String size) {this.size = size;}
    public void setCrust(String crust) {this.crust = crust;}
    public void setSauce(String sauce) {this.sauce = sauce;}
    public void setToppings(List<String> toppings) {this.toppings = toppings;}
}
