/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

/**
 *
 * @author андрей
 */
public class SignIn {

    /**
     *
     * @param login
     * @param password
     * @return
     */
    //На данный момент использований не обнаружено
    public boolean sign(String login, String password) {
        //Searcher searcher = new Searcher();
        //ServiceInfoDao dao = new ServiceInfoDao();
        //ServiceInfo info = dao.getServiceInfoByLogin(login);
        boolean b = false;
        //if (!"".equals(info.getPassword())) {
            if (password.equals("1q")) {
                //открытие фрейма с комнатами
                b = true;
            } else {
                b = false;
            }
        //}
        return b;
        /*catch (NotFoundException e) {
            return false;
            //Надо написать обработчик некорректного логина, пароля!!!!!
        }*/
    }
}
