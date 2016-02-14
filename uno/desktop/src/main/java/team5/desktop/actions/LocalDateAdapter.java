/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.actions;


/**
 *
 * @author chanta
 */
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
 
public class LocalDateAdapter 
    extends XmlAdapter<String, LocalDate>{
 
    public LocalDate unmarshal(String v) throws Exception {
        //return LocalDate.of(year, month, dayOfMonth);
        System.out.println(v);
        return WorkUser.getWork().stringToLocalDate(v);
    }
 
    public String marshal(LocalDate v) throws Exception {
                System.out.println(v.toString());
        return v.toString();
    }
 
}
