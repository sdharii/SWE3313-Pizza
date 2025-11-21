package models;

public class CartItem {

    private String category;   // "Large Pepperoni, Thin Crust"
    private int quantity;
    private double unitPrice;
    private MenuItem item;

    /*public CartItem(String description, int quantity, double unitPrice) {
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }*/
    public CartItem(MenuItem item, String category, int quantity, double unitPrice){
        this.item = item;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    public MenuItem getItem(){
        return item;
    }
    public String getCategory() {
        return category;
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
    public void updateQuantity( int newQuantity){
        this.quantity = newQuantity;
    }
}
