/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 *
 * @author Dmitry
 */
public class DataBaseWorker {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private String errMessage = null;
    private Logger logger;

    /**
     * create database connection
     */
    public DataBaseWorker() {

    }

    public String createQueryfromScript(String filename)
            throws FileNotFoundException {
        Scanner scanner;
        String result = "";

        scanner = new Scanner(new File(filename));
        while (scanner.hasNext()) {
            result += scanner.nextLine() + "\r\n";
        }
        scanner.close();
        return result;
    }

    public void execute(String query) {
        try {
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException ex) {
            logger.debug(ex.getMessage());
        }

    }

    public void executeQuery(String query) {
        try {
            statement = connection.createStatement();
            statement.execute(query);
            result = statement.executeQuery(query);
            result.close();
        } catch (SQLException ex) {
            logger.debug(ex.getMessage());
        }

    }

    public Connection openConnectionDataBase() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "team5sql");
            return connection;
        } catch (SQLException | ClassNotFoundException ex) {
            logger.debug(ex.getMessage());
            return null;
        }
    }

    public void closeConnectionDataBase() {
        try {
            connection.close();
        } catch (SQLException ex) {
            logger.debug(ex.getMessage());
        }

    }

}
