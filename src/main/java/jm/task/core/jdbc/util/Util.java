package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static Connection connection = null;

    private static Util instance = null;

    private Util() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/katappfirst";
                String user = "root";
                String password = "CRImE20242024";
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
