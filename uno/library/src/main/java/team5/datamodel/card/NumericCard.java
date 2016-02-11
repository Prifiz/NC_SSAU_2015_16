/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.card;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Пользователь
 */
@XmlRootElement
public class NumericCard extends Card implements Serializable {

//    public NumericCard(Integer iconId, String color) {
//        super(iconId, color, CardType.NUMERIC);
//    }
    
    public NumericCard(Integer iconId, String color) {
        super(iconId, color);
    }

    public NumericCard() {
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
    public void setIconId(Integer iconId) {
        super.setIconId(iconId);
    }

    @Override
    public Integer getIconId() {
        return super.getIconId();
    }

    @Override
    public String toString() {
        return getColor() + " " + getIconId();
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() == obj.getClass()) {
            if ((((NumericCard) obj).getColor().equals(this.getColor())) && ((NumericCard) obj).getIconId() == this.getIconId()) {
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
        return this.getIconId() ^ this.getColor().length();
    }

//    @Override
//    public CardType getCardType() {
//        return super.getCardType();
//    }
}
