/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.card;

/**
 *
 * @author Дмитрий
 */
public class Color {
    private Integer colorId;
    private String colorName;

    public Color() {
    }

    public Color(Integer colorId, String colorName) {
        this.colorId = colorId;
        this.colorName = colorName;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
    
    @Override
    public String toString() {
        return getColorId() + " " + getColorName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() == obj.getClass()) {
            if ((((Color) obj).getColorName().equals(this.getColorName())) && ((Color) obj).getColorId() == this.getColorId()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getColorId() ^ this.getColorName().length();
    }

    
}
