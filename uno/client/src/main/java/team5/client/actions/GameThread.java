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
import org.apache.log4j.Logger;
import team5.library.card.Card;
import team5.library.card.NumericCard;

/**
 *
 * @author андрей
 */
public class GameThread extends Thread {

    private Counter enabledPane;
    private Counter gamerIndex;
    private DataExchange dataE;
    private JLabel lastCardLabel;
    private Counter gamerCount;
    private Logger log;
    private JTextArea text;
    private String[] logins;

    public GameThread(Counter enabledPane, Counter gamerIndex, DataExchange dataE, JLabel lastCardLabel, Counter gamerCount, JTextArea text, String[] logins) {
        this.dataE = dataE;
        this.enabledPane = enabledPane;
        this.gamerCount = gamerCount;
        this.gamerIndex = gamerIndex;
        this.lastCardLabel = lastCardLabel;
        this.text = text;
        this.logins = logins;
    }

    @Override
    public synchronized void run() {
        while (true) {
            if (enabledPane.getCount() < gamerIndex.getCount()) {
                for (; enabledPane.getCount() < gamerIndex.getCount(); enabledPane.inc()) {
                    try {
                        String command = dataE.readString();
                        switch (command) {
                            case "Pass":
                                text.setText(text.getText() + "\n" + logins[enabledPane.getCount()] + ": Pass");
                                break;
                            case "TakeCard":
                                text.setText(text.getText() + "\n" + logins[enabledPane.getCount()] + ": Take cad");
                                enabledPane.dec();
                                break;
                            case "END TURN":
                                Card card = new NumericCard(dataE.readInt(), dataE.readString());
                                text.setText(text.getText() + "\n" + logins[enabledPane.getCount()] + ": End turn " + card.getIcon() + " " + card.getColor());
                                lastCardLabel.setText(card.toString());
                                lastCardLabel.setForeground(isCardColor(card.getColor()));//color
                                boolean win = dataE.readBool();
                                if (win == true) {
                                    text.setText(text.getText() + "\n" + logins[enabledPane.getCount()] + ": WIN!!!");
                                }
                                break;
                            case "Exit":
                                text.setText(text.getText() + "\n" + logins[enabledPane.getCount()] + ": Out of the room");
                                gamerCount.dec();
                                gamerIndex.dec();
                                enabledPane.dec();
                                break;
                        }
                    } catch (IOException ex) {
                        log.debug(ex.getMessage());
                    }

                }
            }
            if (enabledPane.getCount() > gamerIndex.getCount()) {
                for (; enabledPane.getCount() < gamerCount.getCount(); enabledPane.inc()) {
                    try {
                        String command = dataE.readString();
                        switch (command) {
                            case "Pass"://надо бы сделать лэйбл, который будет отражать ходы противника.
                                text.setText(text.getText() + "\n" + logins[enabledPane.getCount()] + ": Pass");
                                break;
                            case "TakeCard":
                                text.setText(text.getText() + "\n" + logins[enabledPane.getCount()] + ": Take cad");
                                enabledPane.dec();
                                break;
                            case "END TURN":
                                Card card = new NumericCard(dataE.readInt(), dataE.readString());
                                text.setText(text.getText() + "\n" + logins[enabledPane.getCount()] + ": End turn " + card.getIcon() + " " + card.getColor());
                                lastCardLabel.setText(card.toString());
                                lastCardLabel.setForeground(isCardColor(card.getColor()));//color
                                boolean win = dataE.readBool();
                                if (win == true) {
                                    text.setText(text.getText() + "\n" + logins[enabledPane.getCount()] + ": WIN!!!");
                                }
                                break;
                            case "Exit":
                                text.setText(text.getText() + "\n" + logins[enabledPane.getCount()] + ": Out of the room");
                                gamerCount.dec();
                                break;
                        }
                    } catch (IOException ex) {
                        log.debug(ex.getMessage());
                    }

                }
                enabledPane.setCount(0);
            }
            try {
                wait();
            } catch (InterruptedException ex) {
                log.debug(ex.getMessage());
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
