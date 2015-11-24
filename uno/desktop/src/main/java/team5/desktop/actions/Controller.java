/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.actions;

import team5.desktop.gui.Rules;
import team5.desktop.gui.StartFrame;
import java.util.ArrayList;
import team5.desktop.user.User;

/**
 *
 * @author андрей
 */
public class Controller {

    ArrayList<User> users;

    public Controller(ArrayList<User> users) {
        this.users = users;
    }

    public void startFrameButtonRulesClick(java.awt.event.ActionEvent evt) {
        StartFrame start = new StartFrame();
        Rules rule = new Rules();
        rule.setVisible(true);
        start.setVisible(false);
    }

}
