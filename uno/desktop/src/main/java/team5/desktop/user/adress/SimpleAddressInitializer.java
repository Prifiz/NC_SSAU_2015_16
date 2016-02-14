/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.user.adress;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chanta
 */
@XmlRootElement
public class SimpleAddressInitializer implements AddressInitializer, Serializable {

    public SimpleAddressInitializer() {
    }

    public Address initDefaultAddress() {

        return new Address("", "");
    }

}
