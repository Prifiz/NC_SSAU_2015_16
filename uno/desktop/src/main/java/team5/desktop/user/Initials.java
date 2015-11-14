/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.user;


/**
 *
 * @author chanta
 */
public class Initials {
    private String name;
    private String surname;

    public Initials(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object object) {
        if (object ==null)
            return false;
         
        if (object == this) {
            return true;
        }
        if (!(object instanceof Initials)) {
            return false;
        }
        Initials initials =(Initials)object;
        if ((this.getName().compareTo(initials.getName())==0)&&(this.getSurname().compareTo(initials.getSurname())==0)){
            return true;
        }
          return false;
    }

    @Override
    public String toString() {
        StringBuilder buffer=new StringBuilder();
        buffer.append(" ").append(this.getName()).append(", ").append(this.getSurname()).append(" ");
        return buffer.toString();    
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + this.getName().hashCode()+this.getSurname().hashCode();
        return result;
    }
    
    
    
}

