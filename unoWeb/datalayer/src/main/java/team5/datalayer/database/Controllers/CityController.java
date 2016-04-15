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
import team5.datamodel.user.adress.Address;
import team5.datamodel.user.adress.AddressInitializer;
import team5.datamodel.user.adress.SimpleAddressInitializer;

/**
 *
 * @author Дмитрий
 */
public class CityController extends AbstractController<Address, Integer>{

    public static final String SELECT_ALL = "SELECT * FROM city";
    public static final String SELECT_BY_ID = "select * from city where id_city=? ";
    public static final String UPDATE_BY_ID = "update city "
            + "set city_name=?, id_country=? where id_city=? ";
    public static final String DELETE_BY_ID = "delete from city where id_city=? ";
    public static final String INSERT_BY_ID = "insert into city (id_city,city_name,id_country) values (?,?,?) ";

    private Logger logger;

    public CityController(Connection connection) {
        super(connection);
    }

    @Override
    public List<Address> getAll() {
        List<Address> list = new ArrayList<Address>();
        PreparedStatement preparedStatement = getPrepareStatement(SELECT_ALL);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AddressInitializer addressInitializer = new SimpleAddressInitializer();
                Address address = addressInitializer.initDefaultAddress();
                address.setCity(resultSet.getString("city_name"));
                list.add(address);
            }
        } catch (SQLException ex) {
            logger.debug(ex.getMessage());
        } finally {
            closePrepareStatement(preparedStatement);
        }

        return list;
    }

    @Override
    public List<Address> getEntitiesByField(Integer idCity) {
        PreparedStatement preparedStatement = getPrepareStatement(SELECT_BY_ID);
        List<Address> list = new ArrayList<Address>();
        try {
            preparedStatement.setString(1, idCity.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AddressInitializer addressInitializer = new SimpleAddressInitializer();
                Address address = addressInitializer.initDefaultAddress();
                address.setCity(resultSet.getString("city_name"));
                list.add(address);
            }
        } catch (SQLException ex) {
            logger.debug(ex.getMessage());
        } finally {
            closePrepareStatement(preparedStatement);
        }

        return list;
    }

    /**
     * Update DB by ID field
     *
     * @param privateInformation
     * @param login
     * @return
     */
    @Override
    public boolean updateByField(Address address, Integer idCity) {
        PreparedStatement preparedStatement = getPrepareStatement(UPDATE_BY_ID);
        try {
            preparedStatement.setString(1, address.getCity());
           // preparedStatement.setString(2, address.getCity()); 
           //FIXME что-то надо делать с айдишниками стран.мю стоит их загнать в саму модель данных?
            preparedStatement.setString(3, idCity.toString());
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
    public boolean deleteByField(Integer idCity) {
        PreparedStatement preparedStatement = getPrepareStatement(DELETE_BY_ID);
        try {
            preparedStatement.setString(1, idCity.toString());
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
    public boolean insert(Address address, Integer idCity) {

        PreparedStatement preparedStatement = getPrepareStatement(INSERT_BY_ID);
        try {
            preparedStatement.setString(1, idCity.toString());
            preparedStatement.setString(2, address.getCity());
           /// preparedStatement.setString(3, idCountry.toString());
           //FIXME та же проблема с айдишниками стран
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
