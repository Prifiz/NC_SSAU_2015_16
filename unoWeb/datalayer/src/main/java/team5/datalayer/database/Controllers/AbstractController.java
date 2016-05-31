/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.database.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Дмитрий
 */
public abstract class AbstractController<E, K> {

    private Connection connection;

    public AbstractController(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ps;
    }

    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract List<E> getAll();

    public abstract List<E> getEntitiesByField(K field);

    public abstract boolean updateByField(E entity,K field);

    public abstract boolean deleteByField(K field);

    public abstract boolean insert(E entity,K field);

}
