package models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance;
    private static  List<CartItem> cartItems = new ArrayList<>();

    private Cart() {}

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public static void addItem(CartItem item) {
        cartItems.add(item);
    }

    public static List<CartItem> getItems() {
        return cartItems;
    }
    public static void clear() {
        cartItems.clear();
    }

    public static double getTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice();
        }
        return total;
    }
}


//    private static Cart instance;
//
//    private List<Pizza> items = new ArrayList<>();
//
//    private Cart() {}
//
//    public static Cart getInstance() {
//        if (instance == null) {
//            instance = new Cart();
//        }
//        return instance;
//    }
//
//    public void add(Pizza pizza) {
//        items.add(pizza);
//    }
//
//    public void remove(Pizza pizza) {
//        items.remove(pizza);
//    }
//
//    public List<Pizza> getItems() {
//        return items;
//    }
//
//    public double getTotal() {
//        double total = 0;
//        for (Pizza p : items) {
//            total += p.getPrice();
//        }
//        return total;
//    }
//
//    public void clear() {
//        items.clear();
//    }
//}
