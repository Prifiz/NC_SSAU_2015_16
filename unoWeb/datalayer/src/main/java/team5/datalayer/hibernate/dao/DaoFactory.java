/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.hibernate.dao;

/**
 *
 * @author Дмитрий
 */
public class DaoFactory {
    
    private static UserDao userDao;
    private static PrivateInformationDao privateInformationDao;
    
    public static UserDao getUserDao(){
        if (userDao==null) {
            userDao=new UserDao();
        }
        return userDao;
    }

    public static PrivateInformationDao getPrivateInformationDao() {
        if (privateInformationDao==null) {
            privateInformationDao=new PrivateInformationDao();
        }
        return privateInformationDao;
    }
    
}
