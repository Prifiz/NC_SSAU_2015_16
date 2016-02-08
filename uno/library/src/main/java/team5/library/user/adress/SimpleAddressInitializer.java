/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.library.user.adress;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chanta
 */
@XmlRootElement
public class SimpleAddressInitializer implements AddressInitializer, Serializable {

    private static final SimpleAddressInitializer simpleAddressInitializer = new SimpleAddressInitializer();

    public SimpleAddressInitializer() {
    }

    @Override
    public Address initDefaultAddress() {
        return new Address("", "");
    }

    public static SimpleAddressInitializer getSimpleAddressInitializer() {
        return simpleAddressInitializer;
    }

}
