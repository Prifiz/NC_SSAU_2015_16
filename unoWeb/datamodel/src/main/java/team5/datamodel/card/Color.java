/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.card;

import javax.persistence.*;

/**
 *
 * @author Дмитрий
 */
@Entity
@Table(name = "color")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = " id_color")
    private int colorId;

    @Column(name = "color_name")
    private String colorName;

    public Color() {
    }

    public Color(int colorId, String colorName) {
        this.colorId = colorId;
        this.colorName = colorName;
    }

    public int getColorId() {
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
        return "Color: " + colorId + ", " + colorName + " ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Color color = (Color) o;

        if (colorId != color.colorId) return false;
        return colorName != null ? colorName.equals(color.colorName) : color.colorName == null;
    }

    @Override
    public int hashCode() {
        int result = colorId;
        result = 31 * result + (colorName != null ? colorName.hashCode() : 0);
        return result;
    }
}
