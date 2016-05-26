/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.card;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import org.hibernate.annotations.Entity;
/**
 *
 * @author Пользователь
 */
@XmlRootElement(name = "Card")
@XmlSeeAlso(NumericCard.class)
@Entity
@Table(name = "cards")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card")
    private Integer cardId;

    @Column(name = "icon_id")
    private Integer iconId;

    @OneToOne(cascade = {CascadeType.ALL})
    @Column(name = "id_color")
    private Integer colorId;
    
   
    // private CardType cardType;
    public Integer getCardId() {
        return cardId;
    }

//    public Card(Integer iconId, String color, CardType cardType) {
//        this.color = color;
//        this.iconId = iconId;
//        this.cardType = cardType;
//    }
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Card(int cardId, int colorId, int iconId) {
        this.cardId = cardId;
        this.colorId = colorId;
        this.iconId = iconId;

    }

    public Card() {
    }

    @XmlElement(name = "iconID")
    public Integer getIconId() {
        return iconId;
    }

//    public CardType getCardType() {
//        return cardType;
//    }
    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    @XmlElement(name = "colorID")
    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
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
