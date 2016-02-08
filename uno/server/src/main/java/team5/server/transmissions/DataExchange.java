/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.transmissions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;

/**
 *
 * @author андрей
 */
public class DataExchange {
    private InputStream in;
    private OutputStream out;
    public DataExchange(InputStream in, OutputStream out)
    {
        this.in = in;
        this.out = out;
    }
    
    public void write(String command) throws IOException
    {
        DataOutputStream dout = new DataOutputStream(out);
        dout.writeUTF(command);
        dout.flush();
    }
    
     public void write(StringWriter writer) throws IOException
    {
        DataOutputStream dout = new DataOutputStream(out);
        dout.writeUTF(writer.toString());
        dout.flush();
    }
    public void write(int command) throws IOException
    {
        DataOutputStream dout = new DataOutputStream(out);
        dout.writeInt(command);
        dout.flush();
    }
    public void write(boolean command) throws IOException
    {
        DataOutputStream dout = new DataOutputStream(out);
        dout.writeBoolean(command);
        dout.flush();
    }
    public int readInt() throws IOException
    {
        DataInputStream din = new DataInputStream(in);
        return din.readInt();
    }
    public String readString() throws IOException
    {
        DataInputStream din = new DataInputStream(in);
        return din.readUTF();
    }
    public boolean readBool() throws IOException
    {
        DataInputStream din = new DataInputStream(in);
        return din.readBoolean();
    }
    public StringReader readStringReader() throws IOException
    {
        DataInputStream din = new DataInputStream(in);
        String string=din.readUTF();
        return new StringReader(string);
    }
    
    
}
