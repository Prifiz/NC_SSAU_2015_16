/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.user;



import team5.desktop.user.AddressInitializer;

/**
 *
 * @author chanta
 */
public class SimpleAddressInitializer implements AddressInitializer{

    private Address address;

    public SimpleAddressInitializer() {
        this.address = new Address();
    }

    public SimpleAddressInitializer(Address address) {
        this.address = address;
    }
    
    public void initDefaultAddress(){
        address.setCity("");
        address.setCountry("");
     }
    
}
