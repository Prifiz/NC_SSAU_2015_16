/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.card;

import java.util.ArrayList;

/**
 *
 * @author андрей
 */
public class Colors {
    private ArrayList<Color> colors;

   
    
    public Colors(ArrayList<Color> colors){
        this.colors = colors;
    }

   public void addColor(Color color){
       colors.add(color);
   }
   
   public Color getColorById(Integer id){
       Color color2= null;
       for(Color color:colors){
           if(color.getColorId().equals(id)){
               color2=color;
           }
       }
       return color2;
   }
    public ArrayList<Color> getColors() {
        return colors;
    }

    public void setColors(ArrayList<Color> colors) {
        this.colors = colors;
    }
}
