/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.card;
import java.io.Serializable;

/**
 *
 * @author Пользователь
 */
public abstract class Card implements Serializable{

    private Integer icon;
    private String color;

    public Card(Integer icon, String color) {
        this.color = color;
        this.icon = icon;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
