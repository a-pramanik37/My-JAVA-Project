/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
/**
 *
 * @author a_pramanik
 */
public class ConnectionManager {
    public static Connection getConnection() {
        Connection connection = null;
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("db.properties");
            properties.load(fileInputStream);

            String driverClass = properties.getProperty("DB_DRIVER_CLASS");
            String dbUrl = properties.getProperty("DB_URL");
            String dbUser = properties.getProperty("DB_USERNAME");
            String dbPass = properties.getProperty("DB_PASSWORD");

            Class.forName(driverClass);
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement prearedStatement) {
        if (prearedStatement != null) {
            try {
                prearedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
