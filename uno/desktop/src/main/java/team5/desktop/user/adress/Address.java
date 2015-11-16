/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.user.adress;


import team5.desktop.user.adress.AddressInitializer;


/**
 *
 * @author chanta
 */
public class Address {
    private String country;
    private String city;

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }
    public Address(){
        city = new String();
        country = new String();
        AddressInitializer addInit =new SimpleAddressInitializer(this);
        addInit.initDefaultAddress();  
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object object) {
        if (object ==null)
            return false;
         
        if (object == this) {
            return true;
        }
        if (!(object instanceof Address)) {
            return false;
        }
        Address address = (Address)object;
        if ((city.equals(address.getCity()))&&(country.equals(address.getCountry()))){
            return true;
        }
          return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result += 37 * result + city.hashCode()+country.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder(10);
        builder.append(" Address: ").append(city).append(", ").append(country).append(" ");
        return builder.toString();
    }
    
    
    
}
