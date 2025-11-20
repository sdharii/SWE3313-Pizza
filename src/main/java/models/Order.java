package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Order {

    private static final Order INSTANCE = new Order();

    private final ObservableList<CartItem> items =
            FXCollections.observableArrayList();

    private Order() {}

    public static Order getInstance() {
        return INSTANCE;
    }

    public ObservableList<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void clear() {
        items.clear();
    }
}
