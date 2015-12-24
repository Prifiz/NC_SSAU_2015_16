/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.card;
import java.io.Serializable;

/**
 *
 * @author Пользователь
 */
public abstract class Card implements Serializable{

    private int icon;
    private String color;

    public Card(int icon, String color) {
        this.color = color;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
