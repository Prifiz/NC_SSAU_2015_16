/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.card;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Пользователь
 */
@XmlRootElement(name = "Numeric Card")
public class NumericCard extends Card implements Serializable {

//    public NumericCard(Integer iconId, String color) {
//        super(iconId, color, CardType.NUMERIC);
//    }
    public NumericCard(Integer cardId, Integer colorId, Integer iconId) {
        super(cardId, colorId, iconId);
    }

    @Override
    public void setCardId(Integer cardId) {
        super.setCardId(cardId);
    }

    @Override
    public Integer getCardId() {
        return super.getCardId();
    }

    public NumericCard() {
    }

    @Override
    public void setColorId(Integer colorId) {
        super.setColorId(colorId);
    }

    @XmlElement(name = "color")
    @Override
    public Integer getColorId() {
        return super.getColorId();
    }

    @Override
    public void setIconId(Integer iconId) {
        super.setIconId(iconId);
    }

    @XmlElement(name = "iconID")
    @Override
    public Integer getIconId() {
        return super.getIconId();
    }
//    @Override
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    @XmlElement(name = "color")
//    @Override
//    public String getColor() {
//        return color;
//    }
//
//    @Override
//    public void setIconId(Integer iconId) {
//        this.iconId = iconId;
//    }
//
//    @XmlElement(name = "iconID")
//    @Override
//    public Integer getIconId() {
//        return iconId;
//    }

    @Override
    public String toString() {
        return getCardId() + " " + getColorId() + " " + getIconId();
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() == obj.getClass()) {
            if ((((NumericCard) obj).getColorId()==this.getColorId()) && ((NumericCard) obj).getIconId() == this.getIconId()
                    && ((NumericCard) obj).getCardId() == this.getCardId()) {
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
        return this.getIconId() ^ this.getColorId() ^ this.getCardId();
    }

//    @Override
//    public CardType getCardType() {
//        return super.getCardType();
//    }
}
