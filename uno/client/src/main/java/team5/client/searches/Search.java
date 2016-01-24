/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.searches;

import java.util.List;
import team5.client.exceptions.UserNotFoundException;

/**
 *
 * @author Dmitry
 */
public interface Search {

    public List regularSearch(String request) throws UserNotFoundException;

    public List substringSearch(String request)throws UserNotFoundException;
    
}
