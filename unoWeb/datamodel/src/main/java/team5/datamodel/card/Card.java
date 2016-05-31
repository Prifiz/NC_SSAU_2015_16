/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.card;

import javax.persistence.*;
/**
 *
 * @author Пользователь
 */
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card")
    private Integer cardId;

    @Column(name = "icon_id")
    private Integer iconId;

    @Column(name = "id_color")
    private Integer colorId;

    public Card() { }

    public Card(int cardId, int colorId, int iconId) {
        this.cardId = cardId;
        this.colorId = colorId;
        this.iconId = iconId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getIconId() {
        return iconId;
    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    @Override
    public String toString() {
        return getCardId() + " " + getColorId() + " " + getIconId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (!cardId.equals(card.cardId)) return false;
        if (iconId != null ? !iconId.equals(card.iconId) : card.iconId != null) return false;
        return colorId != null ? colorId.equals(card.colorId) : card.colorId == null;
    }

    @Override
    public int hashCode() {
        int result = cardId.hashCode();
        result = 31 * result + (iconId != null ? iconId.hashCode() : 0);
        result = 31 * result + (colorId != null ? colorId.hashCode() : 0);
        return result;
    }
}
