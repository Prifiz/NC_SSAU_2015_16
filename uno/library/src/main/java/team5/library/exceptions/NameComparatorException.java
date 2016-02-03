/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.library.exceptions;

/**
 *
 * @author chanta
 */
public class NameComparatorException extends Throwable {

    /**
     * Creates a new instance of <code>NameComparatorExeption</code> without
     * detail message.
     */
    public NameComparatorException() {
    }

    /**
     * Constructs an instance of <code>NameComparatorExeption</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NameComparatorException(String msg) {
        super(msg);
    }
}
