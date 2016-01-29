/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.transmissions;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Includes in/out streams
 * @author Dmitry
 */
public class Streams {
    
    private static InputStream in;
    private static OutputStream out;
    
    //TODO сделать по паттерну Singleton
    public Streams (InputStream in,OutputStream out){
        this.in=in;
        this.out=out;
    }
    
    public static InputStream getInputStream(){
        return in;
    }
    
     public static OutputStream getOutputStream(){
        return out;
    }
}
