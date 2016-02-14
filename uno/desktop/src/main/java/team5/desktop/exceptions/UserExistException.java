/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.exceptions;

/**
 *
 * @author Dmitry
 */
public class UserExistException extends Exception {

    public UserExistException() {

    }

    public UserExistException(String msg) {
        super(msg);
    }
}
