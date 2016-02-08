package team5.datamodel.searches;

import java.util.List;
import team5.datamodel.exceptions.NotFoundException;

/**
 *
 * @author Dmitry
 */
public interface Search {

    public List regularSearch(String request) throws NotFoundException;

    public List substringSearch(String request) throws NotFoundException;

    public List fieldSearch(String request, String field) throws NotFoundException;

}
