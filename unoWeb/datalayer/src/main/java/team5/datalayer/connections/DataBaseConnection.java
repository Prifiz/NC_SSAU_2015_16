/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dmitry
 */
public class DataBaseConnection {

    Connection connection;
    Statement statement;
    ResultSet result;
    String errMessage = null;

    public DataBaseConnection() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432", "postgres", "team5sql");
            String query = "select * from klass";
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            System.out.println(connection.isClosed());
            try {
                while (result.next()) {
                    System.out.println(result.getString(1)
                            + "   \t" + result.getString(2)
                            + "   \t" + result.getString(3));
                }
                result.close();
            } catch (SQLException e) {
                errMessage = e.getMessage();
                System.out.println(errMessage);
            }
        } catch (Exception e) {
            errMessage = e.getMessage();
            System.out.println(errMessage);
        }
    }
}
