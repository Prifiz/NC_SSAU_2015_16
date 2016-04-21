/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import javax.ejb.Stateless;
import team5.datamodel.user.newmodel.User;


/**
 *
 * @author chanta
 */
@Stateless
public class UnoStatelessSessionBean implements UnoStatelessSessionBeanRemote {

    @Override
    public void addUser(User user) {
        
    }
    
    @Override
    public String getUser(User user) {
        return null;
    }
    
}
