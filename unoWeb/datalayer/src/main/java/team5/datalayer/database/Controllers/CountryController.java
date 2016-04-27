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
import team5.datamodel.user.adress.Address;
import team5.datamodel.user.adress.AddressInitializer;
import team5.datamodel.user.adress.SimpleAddressInitializer;

/**
 *
 * @author Дмитрий
 */
public class CountryController extends AbstractController<Address, Integer> {

    public static final String SELECT_ALL = "SELECT * FROM country";
    public static final String SELECT_BY_ID = "select * from country where id_country=? ";
    public static final String UPDATE_BY_ID = "update country "
            + "set country_name=? where id_country=? ";
    public static final String DELETE_BY_ID = "delete from country where id_country=? ";
    public static final String INSERT_BY_ID = "insert into country (id_country, country_name) values (?,?) ";

    private Logger logger;

    public CountryController(Connection connection) {
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
              //  address.setCountry(resultSet.getString("country_name"));//FIXME
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
    public List<Address> getEntitiesByField(Integer idCountry) {
        PreparedStatement preparedStatement = getPrepareStatement(SELECT_BY_ID);
        List<Address> list = new ArrayList<Address>();
        try {
            preparedStatement.setString(1, idCountry.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AddressInitializer addressInitializer = new SimpleAddressInitializer();
                Address address = addressInitializer.initDefaultAddress();
                //address.setCountry(resultSet.getString("country_name"));//FIXME
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
    public boolean updateByField(Address address, Integer idCountry) {
        PreparedStatement preparedStatement = getPrepareStatement(UPDATE_BY_ID);
        try {
            //preparedStatement.setString(1, address.getCountry());//FIXME
            preparedStatement.setString(2, idCountry.toString());
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
    public boolean deleteByField(Integer idCountry) {
        PreparedStatement preparedStatement = getPrepareStatement(DELETE_BY_ID);
        try {
            preparedStatement.setString(1, idCountry.toString());
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
    public boolean insert(Address address, Integer idCountry) {

        PreparedStatement preparedStatement = getPrepareStatement(INSERT_BY_ID);
        try {
            preparedStatement.setString(1, idCountry.toString());
           // preparedStatement.setString(2, address.getCountry());//FIXME
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
