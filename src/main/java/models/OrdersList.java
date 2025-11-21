package models;

import java.util.ArrayList;

public class OrdersList {
    private ArrayList<PizzaOrder> orders = new ArrayList<>();

    public void addOrder(PizzaOrder order) {
        orders.add(order);
    }

    public ArrayList<PizzaOrder> getOrders() {
        return orders;
    }

    public PizzaOrder getOrder(int index) {
        return orders.get(index);
    }

    public void setOrders(ArrayList<PizzaOrder> orders) {
        this.orders = orders;
    }
}
