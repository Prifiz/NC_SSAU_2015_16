/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.library.card;
import java.io.Serializable;

/**
 *
 * @author Пользователь
 */
public class NumericCard extends Card implements Serializable{

    public NumericCard(Integer icon, String color) {
        super(icon, color);
    }

    @Override
    public void setColor(String color) {
        super.setColor(color);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public void setIcon(Integer icon) {
        super.setIcon(icon);
    }

    @Override
    public Integer getIcon() {
        return super.getIcon();
    }

    @Override
    public String toString() {
        return getColor() + " " + getIcon();
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() == obj.getClass()) {
            if ((((NumericCard) obj).getColor().equals(this.getColor())) && ((NumericCard) obj).getIcon() == this.getIcon()) {
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
        return this.getIcon() ^ this.getColor().length();
    }

}
