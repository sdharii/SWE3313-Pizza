package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    private int itemID;
    private String name;
    private String description;
    private double price;
    private String category;

    public MenuItem( int itemID, String name, String category, double price){
        this.itemID = itemID;
        this.name = name;
        this.category = category;
        this.price = price;
    }
    public int getItemID(){ return itemID;}
    public String getName(){ return name;}
    public String getDescription(){ return description;}
    public double getPrice(){ return price;}
    public String getCategory(){ return category;}

    public static List<MenuItem> loadAll(Connection conn) throws SQLException{
        String sql = "SELECT * FROM Menu_Items";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<MenuItem> items = new ArrayList<>();
         while (rs.next()){
             MenuItem item = new MenuItem( rs.getInt("\"Item ID\""),
                     rs.getString("name"),
                     rs.getString("category"),
                     rs.getDouble("price")
                     );
             items.add(item);
         }
         return items;
    }
}


