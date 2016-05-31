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
import team5.datamodel.user.ServiceInfo;

/**
 *
 * @author Дмитрий
 */
public class ServiceInfoController extends AbstractController<ServiceInfo, String> {

    public static final String SELECT_ALL = "SELECT * FROM service_info";
    public static final String SELECT_BY_LOGIN = "select * from service_info where login=? ";
    public static final String UPDATE_BY_LOGIN = "update private_information "
            + "set password=?, data_of_regist=? where login=? ";
    public static final String DELETE_BY_LOGIN = "delete from service_info where login=? ";
    public static final String INSERT_BY_LOGIN = "insert into service_info (login, password, data_of_regist) values (?,?,?) ";

    private Logger logger;

    public ServiceInfoController(Connection connection) {
        super(connection);
    }

    @Override
    public List<ServiceInfo> getAll() {
        List<ServiceInfo> list = new ArrayList<ServiceInfo>();
        PreparedStatement preparedStatement = getPrepareStatement(SELECT_ALL);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ServiceInfo serviceInfo = new ServiceInfo();
                serviceInfo.setLogin(resultSet.getString("login"));
                serviceInfo.setPassword(resultSet.getString("password"));
                //FIXME непонятно что делать с датой регистрации.в оригинальном классе сетера на нее нет.
                list.add(serviceInfo);
            }
        } catch (SQLException ex) {
            logger.debug(ex.getMessage());
        } finally {
            closePrepareStatement(preparedStatement);
        }

        return list;
    }

    @Override
    public List<ServiceInfo> getEntitiesByField(String login) {
        PreparedStatement preparedStatement = getPrepareStatement(SELECT_BY_LOGIN);
        List<ServiceInfo> list = new ArrayList<ServiceInfo>();
        try {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ServiceInfo serviceInfo = new ServiceInfo();
                serviceInfo.setLogin(resultSet.getString("login"));
                serviceInfo.setPassword(resultSet.getString("password"));
                //FIXME непонятно что делать с датой регистрации.в оригинальном классе сетера на нее нет.
                list.add(serviceInfo);
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
    public boolean updateByField(ServiceInfo serviceInfo, String login) {
        PreparedStatement preparedStatement = getPrepareStatement(UPDATE_BY_LOGIN);
        try {
            preparedStatement.setString(1, serviceInfo.getPassword());
            preparedStatement.setString(2, serviceInfo.getDateOfRegistration().toString());
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
    public boolean insert(ServiceInfo serviceInfo, String login) {

        PreparedStatement preparedStatement = getPrepareStatement(INSERT_BY_LOGIN);
        try {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, serviceInfo.getPassword());
            preparedStatement.setString(3, serviceInfo.getDateOfRegistration().toString());
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
