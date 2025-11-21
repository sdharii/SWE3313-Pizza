package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import models.PizzaOrder;
import models.OrdersList;
import models.PizzaToppings;

public class MenuPageController {
    private OrdersList orderList = new OrdersList();
    private PizzaToppings pizzaToppings = new PizzaToppings();

    @FXML private ListView<String> ordersListView;
    @FXML private ChoiceBox<String> crustTypeChoice;
    @FXML private ChoiceBox<String> crustSizeChoice;
    @FXML private ChoiceBox<String> sauceChoice;
    @FXML private ChoiceBox<String> beverageChoice;
    @FXML private ChoiceBox<String> dessertChoice;
    @FXML private TextField toppingInput;

    @FXML
    private void addOrder(ActionEvent event) {
        PizzaOrder order = new PizzaOrder();
        order.setCrustType(crustTypeChoice.getValue());
        order.setCrustSize(crustSizeChoice.getValue());
        order.setSauce(sauceChoice.getValue());
        order.setBeverage(beverageChoice.getValue());
        order.setDessert(dessertChoice.getValue());

        if (!toppingInput.getText().isEmpty()) {
            pizzaToppings.addTopping(toppingInput.getText());
            order.setToppings(pizzaToppings.getToppings());
        }

        orderList.addOrder(order);
        ordersListView.getItems().add(order.toString());
        pizzaToppings.clear();
    }

    @FXML
    private void editOrder(ActionEvent event) {
        int selectedIndex = ordersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            PizzaOrder order = orderList.getOrder(selectedIndex);
            order.setCrustType(crustTypeChoice.getValue()); // example edit
            ordersListView.getItems().set(selectedIndex, order.toString());
        }
    }

    @FXML
    private void deleteOrder(ActionEvent event) {
        int selectedIndex = ordersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            orderList.getOrders().remove(selectedIndex);
            ordersListView.getItems().remove(selectedIndex);
        }
    }
}