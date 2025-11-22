//package models;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Cart {
//
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
