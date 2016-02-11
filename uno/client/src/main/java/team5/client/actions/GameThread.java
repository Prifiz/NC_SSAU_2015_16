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

    private final Counter turnIndex;
    private final Counter gamerIndex;
    private final DataExchanger dataE;
    private final JLabel lastCardLabel;
    private final Counter gamerCount;
    private Logger log;
    private final JTextArea text;
    private final String[] logins;
    private final Flag canExit;

    public GameThread(Counter enabledPane, Counter gamerIndex, DataExchanger dataE,
            JLabel lastCardLabel, Counter gamerCount, JTextArea text, String[] logins, Flag canExit) {
        this.dataE = dataE;
        this.turnIndex = enabledPane;
        this.gamerCount = gamerCount;
        this.gamerIndex = gamerIndex;
        this.lastCardLabel = lastCardLabel;
        this.text = text;
        this.logins = logins;
        this.canExit = canExit;
    }

    @Override
    public synchronized void run() {
        while (true) {
            if (turnIndex.getCount() < gamerIndex.getCount()) {
                for (; turnIndex.getCount() < gamerIndex.getCount(); turnIndex.inc()) {
                    try {
                        String command = dataE.readString();
                        switch (command) {
                            case "Pass":
                                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": Pass");
                                break;
                            case "TakeCard":
                                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": Take card");
                                turnIndex.dec();
                                break;
                            case "END TURN":
                                Card card = new NumericCard(dataE.readInt(), dataE.readString());
                                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": End turn " + card.getIcon() + " " + card.getColor());
                                lastCardLabel.setText(card.toString());
                                lastCardLabel.setForeground(isCardColor(card.getColor()));//color
                                boolean win = dataE.readBool();
                                if (win == true) {
                                    text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": WIN!!!");
                                    canExit.setFlag(true);
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
                        log.debug(ex.getMessage());
                    }

                }
            }
            if (turnIndex.getCount() > gamerIndex.getCount()) {
                for (; turnIndex.getCount() < gamerCount.getCount(); turnIndex.inc()) {
                    try {
                        String command = dataE.readString();
                        switch (command) {
                            case "Pass"://надо бы сделать лэйбл, который будет отражать ходы противника.
                                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": Pass");
                                break;
                            case "TakeCard":
                                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": Take card");
                                turnIndex.dec();
                                break;
                            case "END TURN":
                                Card card = new NumericCard(dataE.readInt(), dataE.readString());
                                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": End turn " + card.getIcon() + " " + card.getColor());
                                lastCardLabel.setText(card.toString());
                                lastCardLabel.setForeground(isCardColor(card.getColor()));//color
                                boolean win = dataE.readBool();
                                if (win == true) {
                                    text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": WIN!!!");
                                    canExit.setFlag(true);
                                }
                                break;
                            case "Exit":
                                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": Out of the room");
                                gamerCount.dec();
                                break;
                        }
                    } catch (IOException ex) {
                        log.debug(ex.getMessage());
                    }

                }
                turnIndex.setCount(0);
            }
            if (turnIndex.getCount() == gamerIndex.getCount()) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    log.debug(ex.getMessage());
                }
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
