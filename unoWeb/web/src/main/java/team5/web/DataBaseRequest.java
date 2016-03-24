/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.web;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dmitry
 */
@XmlRootElement
public class DataBaseRequest {

    public DataBaseRequest() {
    }

    private String request;
    private String result;

    public void setResult(String result) {
        this.result = result;
    }

    @XmlElement(name = "result")
    public String getResult() {
        return result;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public DataBaseRequest(String request) {
        this.request = request;
    }
    
}
