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

    public Colors(){
        this.colors = new ArrayList<>();
        colors.add(new Color(0,"red"));
        colors.add(new Color(1,"green"));
        colors.add(new Color(2,"blue"));
        colors.add(new Color(3,"yellow"));
    }

   public void addColor(Color color){
       colors.add(color);
   }
   
   public Color getColorById(Integer id){
       Color color2 = null;
       for (Color color : colors){
           if(color.getColorId() == id){
               color2 = color;
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
