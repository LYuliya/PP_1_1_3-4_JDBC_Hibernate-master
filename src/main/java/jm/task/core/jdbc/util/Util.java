package jm.task.core.jdbc.util;

import com.mysql.cj.exceptions.CJOperationNotSupportedException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://:3306/tabletest";
    public static final String DB_Driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Connection to DB");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error Connection");
        }
        return connection;
    }





}
