/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import java.time.LocalDate;
import javax.ejb.Remote;
import team5.datamodel.user.User;

/**
 *
 * @author chanta
 */
@Remote
public interface UnoStatelessSessionBeanRemote {

    void  addUser(String name, String surname,  LocalDate BDay, String login, String password, String email, String city, String Country);

    User getUser(String login);
    
}
