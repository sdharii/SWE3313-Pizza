package models;

import java.util.List;
import java.util.ArrayList;

public class Cart {
    private List<CartItem> items = new ArrayList<>();
    public void addItem(MenuItem menuItem, int quantity){
        for (CartItem cartItem: items){
            if (cartItem.getItem().getItemID()== menuItem.getItemID()){
                cartItem.updateQuantity(cartItem.getQuantity()+ quantity);
                return;
            }
        }
        CartItem newItem = new CartItem( menuItem, menuItem.getCategory(), quantity, menuItem.getPrice());
        items.add(newItem);
    }
    public void removeItem (int itemID){
        items.removeIf(cartItem -> cartItem.getItem().getItemID() == itemID);
    }
    public void clear(){
        items.clear();
    }
    public List <CartItem> getItems(){
        return items;
    }
    public double getSubtotal(){
        double sum = 0;
        for(CartItem item: items){
            sum += item.getLineTotal();
        }
        return sum;
    }
    public double getTax(){
        return getSubtotal()* 0.03 ;// a 3% tax
    }
    public  double getTotal(){
        return getSubtotal() +getTax();
    }
}
