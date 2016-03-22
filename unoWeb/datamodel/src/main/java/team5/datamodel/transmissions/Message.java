package team5.datamodel.transmissions;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import team5.datamodel.card.Card;
//import team5.datamodel.card.Card.CardType;
import team5.datamodel.card.NumericCard;
import team5.datamodel.user.User;

/**
 * Use for request transmission from client to server
 *
 * @author Dmitry
 */
@XmlRootElement(name = "Message")

public class Message implements Serializable {

    private String command;
    private User user;
    private boolean confirmation;
    private String choice;
    private int value;
    @XmlElement
    private NumericCard numericCard;
    //private CardType cardType;
    // private String cardType;

    public Message(String command) {
        this.command = command;
    }

    public Message(User user){
        this.user = user;
    }
    public Message(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public Message(int value) {
        this.value = value;
    }

//    public Message(Card card) {
//        cardType = card.getCardType();
//        switch (cardType) {
//            case NUMERIC:
//                this.numericCard = (NumericCard) card;
////            case SPECIAL:
////                break;
//            default:
//                this.numericCard = (NumericCard) card;
//
//        }
//    }
    public Message(Card card) {

        this.numericCard = (NumericCard) card;

    }

    public Message() {
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @XmlElement(name = "command")
    public String getCommand() {
        return command;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public boolean getConfirmation() {
        return confirmation;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    @XmlElement(name = "choice")
    public String getChoice() {
        return choice;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public NumericCard getCard() {

        return numericCard;//заглушка временная

    }

//    public NumericCard getCard() {
//        switch (cardType) {
//            case NUMERIC:
//                return numericCard;
////            case SPECIAL:
////                break;
//            default:
//                return numericCard;//заглушка временная
//        }
//
//    }
    public void setCard(Card card) {

        this.numericCard = (NumericCard) card;//TODO заглушка

    }

//    public void setCard(Card card) {
//        cardType = card.getCardType();
//        switch (cardType) {
//            case NUMERIC:
//                this.numericCard = (NumericCard) card;
////            case SPECIAL:
////                break;
//            default:
//                this.numericCard = (NumericCard) card;
//
//        }
//    }
//    public void setCardType(String cardType) {
//        this.cardType = cardType;
//    }
//
//    @XmlElement(name = "card type")
//    public String getCardType() {
//        return cardType;
//    }
//    private enum CardType {
//        NUMERIC,
//        SPECIAL;
//    }
}
