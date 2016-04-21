/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import javax.ejb.Remote;
import team5.datamodel.user.newmodel.User;

/**
 *
 * @author chanta
 */
@Remote
public interface UnoStatelessSessionBeanRemote {

    void addUser(User user);

    String getUser(User user);
    
}
