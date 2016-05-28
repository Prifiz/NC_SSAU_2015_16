/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.card;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    private Integer colorId;

    @Column(name = "color_name")
    private String colorName;

    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_color")
//    private List<NumericCard> listOfCards;
////    @OneToOne(cascade = {CascadeType.ALL})
////    @JoinColumn(name = "card_col")
////    private Card cardColomn;
//
//    public List<NumericCard> getListOfCards() {
//        return listOfCards;
//    }
//
//    public void setListOfCards(List<NumericCard> listOfCards) {
//        this.listOfCards = listOfCards;
//    }

   
    public Color() {
    }

    public Color(Integer colorId, String colorName) {
        this.colorId = colorId;
        this.colorName = colorName;
    }

    public Integer getColorId() {
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

//    public Card getCardColomn() {
//        return cardColomn;
//    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(10);
        builder.append(" Color: ").append(colorId).append(", ").append(colorName).append(" ");
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() == obj.getClass()) {
            if ((((Color) obj).getColorName().equals(this.getColorName())) && ((Color) obj).getColorId() == this.getColorId()) {
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
        return this.getColorId() ^ this.getColorName().length();
    }

}
