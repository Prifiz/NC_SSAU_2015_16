
package team5.client.searches;

import java.util.List;
import team5.client.exceptions.NotFoundException;

/**
 *
 * @author Dmitry
 */
public interface Search {

    public List regularSearch(String request) throws NotFoundException;

    public List substringSearch(String request)throws NotFoundException;
    
}
