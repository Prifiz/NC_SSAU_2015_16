/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author Dmitry
 */
public class Searcher {

    private Logger logger;
    private DataBaseWorker dbworker;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private String stringResult="";

    public String search(String request) {

        dbworker = new DataBaseWorker();
        this.connection = dbworker.openConnectionDataBase();
        try {
            preparedStatement = connection.prepareStatement("select * from private_information where login=? ");
            preparedStatement.setString(1, request);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                stringResult+=("Номер в выборке #" + result.getRow()
                        + "\t "+ "| Login:"+result.getString("login")
                        + "\t" + "| Name:"+result.getString("name")
                        + "\t" + "| Surname:"+result.getString("surname"));
            }
            dbworker.closeConnectionDataBase();
            result.close();
            return stringResult;
        } catch (SQLException ex) {
            logger.debug(ex.getMessage());
             return "Пусто";
        }
        
        

    }
}
