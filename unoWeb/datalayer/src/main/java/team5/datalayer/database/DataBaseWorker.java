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
    private ResultSet result;
    private String errMessage = null;
    private Logger logger;

    /**
     * create database connection
     */
    public DataBaseWorker() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432", "postgres", "team5sql");
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            logger.debug(ex.getMessage());
        }
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

    public void executeQuery(String query) {
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            result.close();
        } catch (SQLException ex) {
            logger.debug(ex.getMessage());
        }

    }

}
