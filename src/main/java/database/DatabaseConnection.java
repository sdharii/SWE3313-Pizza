package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Connects DB to logic
public class DatabaseConnection {

    private static final String dbPath= "src/main/java/database/database.db";

    public static Connection getConnection() throws SQLException {
        try {
            // Ensure the SQLite driver is registered
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found. Make sure the sqlite-jdbc jar is on the classpath.", e);
        }
        System.out.println("Trying to connect to database...");
        return DriverManager.getConnection("jdbc:sqlite:"+dbPath);
    }

}
