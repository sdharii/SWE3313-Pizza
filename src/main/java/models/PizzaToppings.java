package models;

import java.util.ArrayList;

public class PizzaToppings {
    private ArrayList<String> toppings = new ArrayList<>();

    public void addTopping(String topping) {
        this.toppings.add(topping);
    }

    public ArrayList<String> getToppings() {
        return this.toppings;
    }

    public void setToppings(ArrayList<String> toppings) {
        this.toppings = toppings;
    }

    public void clear() {
        toppings.clear();
    }

}
