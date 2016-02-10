package team5.datamodel.transmissions;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import team5.datamodel.card.Card;
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
    private Card card;

    

    public Message(String command) {
        this.command = command;
    }

    public Message(boolean confirmation) {
        this.confirmation = confirmation;
    }
    
    public Message(int value){
        this.value = value;
    }
    
    public Message(Card card){
        this.card = card;
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
    
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
