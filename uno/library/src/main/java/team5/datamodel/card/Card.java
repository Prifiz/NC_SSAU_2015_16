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
public abstract class Card implements Serializable {

    private Integer iconId;
    private String color;
   // private CardType cardType;

//    public Card(Integer iconId, String color, CardType cardType) {
//        this.color = color;
//        this.iconId = iconId;
//        this.cardType = cardType;
//    }
    public Card(Integer iconId, String color) {
        this.color = color;
        this.iconId = iconId;
    }

    public Card() {

    }

    public Integer getIconId() {
        return iconId;
    }

//    public CardType getCardType() {
//        return cardType;
//    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

//    public void setCardType(CardType cardType) {
//        this.cardType = cardType;
//    }
//TODO не понятно атк вообще можно делать или делать отдельную оболочку?
//    public enum CardType {
//        NUMERIC,
//        SPECIAL;
//    }

}
