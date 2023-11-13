package lk.ijse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private static dbConnection dbConnection;
    private Connection connection;

    private dbConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/pepper",
                "root",
                "Ijse@1234"
        );
    }

    public static dbConnection getInstance() throws SQLException {
        return (null == dbConnection) ? dbConnection = new dbConnection() : dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
