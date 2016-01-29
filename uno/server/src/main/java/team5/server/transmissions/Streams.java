/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.transmissions;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Includes in/out streams
 * @author Dmitry
 */
public class Streams {
    
    private  InputStream in;
    private  OutputStream out;
    
    public Streams (InputStream in,OutputStream out){
        this.in=in;
        this.out=out;
    }
    
    public  InputStream getInputStream(){
        return in;
    }
    
     public  OutputStream getOutputStream(){
        return out;
    }
}
