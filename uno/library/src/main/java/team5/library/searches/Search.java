package team5.library.searches;

import java.util.List;
import team5.library.exceptions.NotFoundException;

/**
 *
 * @author Dmitry
 */
public interface Search {

    public List regularSearch(String request) throws NotFoundException;

    public List substringSearch(String request) throws NotFoundException;

    public List fieldSearch(String request, String field) throws NotFoundException;

}
