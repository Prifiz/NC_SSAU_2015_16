/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.user.adress;



/**
 *
 * @author chanta
 */
public class SimpleAddressInitializer implements AddressInitializer, java.io.Serializable{
     

    public SimpleAddressInitializer() {
    }

    public Address initDefaultAddress(){
        
        return new Address("", "");
     }
    
}
