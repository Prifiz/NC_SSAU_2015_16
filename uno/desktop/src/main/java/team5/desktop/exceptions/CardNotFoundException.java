/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.exceptions;

/**
 *
 * @author chanta
 */
public class CardNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>CardNotFoundException</code> without
     * detail message.
     */
    public CardNotFoundException() {
    }

    /**
     * Constructs an instance of <code>CardNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CardNotFoundException(String msg) {
        super(msg);
    }
}
