package models;

public class CartItem {

    private String description;   // "Large Pepperoni, Thin Crust"
    private int quantity;
    private double unitPrice;

    public CartItem(String description, int quantity, double unitPrice) {
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getLineTotal() {
        return unitPrice * quantity;
    }
}
