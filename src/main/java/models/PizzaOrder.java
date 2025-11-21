package models;

import java.util.ArrayList;
import java.util.List;

public class PizzaOrder {
    private String crustType;
    private String crustSize;
    private String sauce;
    private List<String> toppings = new ArrayList<>();
    private String beverage;
    private String dessert;

    public void setCrustType(String crustType) { this.crustType = crustType; }
    public void setCrustSize(String crustSize) { this.crustSize = crustSize; }
    public void setSauce(String sauce) { this.sauce = sauce; }
    public void setToppings(List<String> toppings) { this.toppings = toppings; }
    public void setBeverage(String beverage) { this.beverage = beverage; }
    public void setDessert(String dessert) { this.dessert = dessert; }

    public String getCrustType() { return crustType; }
    public String getCrustSize() { return crustSize; }
    public String getSauce() { return sauce; }
    public List<String> getToppings() { return toppings; }
    public String getBeverage() { return beverage; }
    public String getDessert() { return dessert; }

    @Override
    public String toString() {
        return "\tCrust Type: " + crustType +
                "\n\tCrust Size: " + crustSize +
                "\n\tPizza Sauce: " + sauce +
                "\n\tToppings: " + (toppings.isEmpty() ? "No Toppings" : String.join(", ", toppings)) +
                "\n\tBeverage: " + beverage +
                "\n\tDessert: " + dessert;
    }

}
