package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Connects DB to logic
public class DatabaseConnection {

    private static final String dbPath= "src/main/java/database/database.db";

    public static Connection getConnection() throws SQLException {
        System.out.println("Trying to connect to database...");
        return DriverManager.getConnection("jdbc:sqlite:"+dbPath);
    }

}
