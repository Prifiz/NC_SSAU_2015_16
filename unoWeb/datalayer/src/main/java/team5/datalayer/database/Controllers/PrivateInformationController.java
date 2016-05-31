/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.database.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import team5.datamodel.user.PrivateInformation;

/**
 *
 * @author Дмитрий
 */
public class PrivateInformationController extends AbstractController<PrivateInformation, String> {

    public static final String SELECT_ALL = "SELECT * FROM Private_information";
    public static final String SELECT_BY_LOGIN = "select * from private_information where login=? ";
    public static final String UPDATE_BY_LOGIN = "update private_information "
            + "set name=?, surname=? where login=? ";
    public static final String DELETE_BY_LOGIN = "delete from private_information where login=? ";
    public static final String INSERT_BY_LOGIN = "insert into private_information (login, name, surname) values (?,?,?) ";

    private Logger logger;

    public PrivateInformationController(Connection connection) {
        super(connection);
    }

    @Override
    public List<PrivateInformation> getAll() {
        List<PrivateInformation> list = new ArrayList<PrivateInformation>();
        PreparedStatement preparedStatement = getPrepareStatement(SELECT_ALL);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PrivateInformation privateInformation = new PrivateInformation();
                privateInformation.setName(resultSet.getString("name"));
                privateInformation.setSurname(resultSet.getString("surname"));
                list.add(privateInformation);
            }
        } catch (SQLException ex) {
            logger.debug(ex.getMessage());
        } finally {
            closePrepareStatement(preparedStatement);
        }

        return list;
    }

    @Override
    public List<PrivateInformation> getEntitiesByField(String login) {
        PreparedStatement preparedStatement = getPrepareStatement(SELECT_BY_LOGIN);
        List<PrivateInformation> list = new ArrayList<PrivateInformation>();
        try {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PrivateInformation privateInformation = new PrivateInformation();
                privateInformation.setName(resultSet.getString("name"));
                privateInformation.setSurname(resultSet.getString("surname"));
                list.add(privateInformation);
            }
        } catch (SQLException ex) {
            logger.debug(ex.getMessage());
        } finally {
            closePrepareStatement(preparedStatement);
        }

        return list;
    }

    /**
     * Update DB by login field
     *
     * @param privateInformation
     * @param login
     * @return
     */
    @Override
    public boolean updateByField(PrivateInformation privateInformation, String login) {
        PreparedStatement preparedStatement = getPrepareStatement(UPDATE_BY_LOGIN);
        try {
            preparedStatement.setString(1, privateInformation.getName());
            preparedStatement.setString(2, privateInformation.getSurname());
            preparedStatement.setString(3, login);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            logger.debug(ex.getMessage());
            return false;
        } finally {
            closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public boolean deleteByField(String login) {
        PreparedStatement preparedStatement = getPrepareStatement(DELETE_BY_LOGIN);
        try {
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            logger.debug(ex.getMessage());
            return false;
        } finally {
            closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public boolean insert(PrivateInformation privateInformation, String login) {

        PreparedStatement preparedStatement = getPrepareStatement(INSERT_BY_LOGIN);
        try {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, privateInformation.getName());
            preparedStatement.setString(3, privateInformation.getSurname());
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            logger.debug(ex.getMessage());
            return false;
        } finally {
            closePrepareStatement(preparedStatement);
        }

    }
}
