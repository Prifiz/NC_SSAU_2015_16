package team5.datamodel.searches;

import java.util.List;
import team5.datamodel.exceptions.NotFoundException;

/**
 *
 * @author Dmitry
 */
public interface Search {

    public List searchByRegexp(String request) throws NotFoundException;

    public List searchBySubstring(String request) throws NotFoundException;

    public List searchByField(String request, String field) throws NotFoundException;

}
