package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectMySql {
    public static Connection getConnection() {
        String jdbcURL = "jdbc:mysql://localhost:3306/thuthuchanh_module3";
        String jdbcUsername = "root";
        String jdbcPassword = "Manh@0811";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
