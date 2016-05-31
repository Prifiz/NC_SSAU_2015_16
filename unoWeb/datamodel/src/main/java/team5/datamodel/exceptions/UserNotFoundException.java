/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.exceptions;

/**
 *
 * @author Dmitry
 */
public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {

    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
