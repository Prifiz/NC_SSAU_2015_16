/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.actions;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import team5.datamodel.card.Card;
import team5.datamodel.card.NumericCard;
import team5.datamodel.transmissions.Message;
import team5.datamodel.transmissions.MessageHandler;

/**
 *
 * @author андрей
 */
public class GameThread extends Thread {

    private final Counter turnIndex;
    private final Counter gamerIndex;
    private DataExchanger dataE;
    private final JLabel lastCardLabel;
    private final Counter gamerCount;
    private Logger logger;
    private final JTextArea text;
    private final String[] logins;
    private final MessageHandler messageHandler;

    public GameThread(Counter enabledPane, Counter gamerIndex, MessageHandler messageHandler, JLabel lastCardLabel, Counter gamerCount, JTextArea text, String[] logins) {
        this.messageHandler = messageHandler;
        this.turnIndex = enabledPane;
        this.gamerCount = gamerCount;
        this.gamerIndex = gamerIndex;
        this.lastCardLabel = lastCardLabel;
        this.text = text;
        this.logins = logins;
    }

    @Override
    public synchronized void run() {
        while (true) {
            if (turnIndex.getCount() < gamerIndex.getCount()) {
                for (; turnIndex.getCount() < gamerIndex.getCount(); turnIndex.inc()) {
                    try {
                        Message message = messageHandler.receiveMessage();;
                        String command = message.getCommand();
                        switch (command) {
                            case "Pass":
                                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": Pass");
                                break;
                            case "TakeCard":
                                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": Take cad");
                                turnIndex.dec();
                                break;
                            case "END TURN":
                                Card card = message.getCard();
                                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": End turn " + card.getIcon() + " " + card.getColor());
                                lastCardLabel.setText(card.toString());
                                lastCardLabel.setForeground(isCardColor(card.getColor()));//color
                                boolean win = message.getConfirmation();
                                if (win == true) {
                                    text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": WIN!!!");
                                }
                                break;
                            case "Exit":
                                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": Out of the room");
                                gamerCount.dec();
                                gamerIndex.dec();
                                turnIndex.dec();
                                break;
                        }
                    } catch (IOException ex) {
                        logger.debug(ex.getMessage());
                    } catch (JAXBException ex) {
                        logger.debug(ex.getMessage());
                    }

                }
            }
            if (turnIndex.getCount() > gamerIndex.getCount()) {
                for (; turnIndex.getCount() < gamerCount.getCount(); turnIndex.inc()) {
                    try {
                        Message message = messageHandler.receiveMessage();;
                        String command = message.getCommand();
                        switch (command) {
                            case "Pass":
                                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": Pass");
                                break;
                            case "TakeCard":
                                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": Take cad");
                                turnIndex.dec();
                                break;
                            case "END TURN":
                                Card card = message.getCard();
                                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": End turn " + card.getIcon() + " " + card.getColor());
                                lastCardLabel.setText(card.toString());
                                lastCardLabel.setForeground(isCardColor(card.getColor()));//color
                                boolean win = message.getConfirmation();
                                if (win == true) {
                                    text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": WIN!!!");
                                }
                                break;
                            case "Exit":
                                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": Out of the room");
                                gamerCount.dec();
                                gamerIndex.dec();
                                turnIndex.dec();
                                break;
                        }
                    } catch (IOException ex) {
                        logger.debug(ex.getMessage());
                    } catch (JAXBException ex) {
                        logger.debug(ex.getMessage());
                    }

                }
                turnIndex.setCount(0);
            }
            try {
                wait();
            } catch (InterruptedException ex) {
                logger.debug(ex.getMessage());
            }

        }

    }

    private Color isCardColor(String color) {
        if ("blue".equals(color)) {
            return Color.BLUE;
        } else {
            if ("yellow".equals(color)) {
                return Color.YELLOW;
            } else {
                if ("green".equals(color)) {
                    return Color.GREEN;
                } else {
                    return Color.RED;
                }

            }
        }

    }

    public synchronized void wakeUp() {
        notifyAll();
    }

}
