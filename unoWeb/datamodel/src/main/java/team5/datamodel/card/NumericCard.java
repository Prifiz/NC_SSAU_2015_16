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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Entity;

/**
 *
 * @author Пользователь
 */
@XmlRootElement(name = "Numeric Card")
@Entity
@Table(name = "cards")
//@Inheritance(strategy = InheritanceType.JOINED)
public class NumericCard extends Card implements Serializable {

//    public NumericCard(Integer iconId, String color) {
//        super(iconId, color, CardType.NUMERIC);
//    }
//    @JoinColumn(name = "id_color", referencedColumnName = "id_color")
//    @ManyToOne(optional = false)
//    private Integer colorId;
    
    public NumericCard(Integer cardId, Integer colorId, Integer iconId) {
        super(cardId, colorId, iconId);
    }

    @Override
    public void setCardId(Integer cardId) {
        super.setCardId(cardId);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card")
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

    @Column(name = "icon_id")
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
    @OneToMany(cascade = {CascadeType.ALL})
    @Column(name = "id_color")
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
            if ((((NumericCard) obj).getColorId() == this.getColorId()) && ((NumericCard) obj).getIconId() == this.getIconId()
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
