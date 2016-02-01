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
import javax.swing.JTextField;
import org.apache.log4j.Logger;
import team5.library.card.Card;
import team5.library.card.NumericCard;

/**
 *
 * @author андрей
 */
public class GameThread extends Thread {

    private Counter enabledPane;
    private int gamerIndex;
    private DataExchange dataE;
    private JLabel lastCardLabel;
    private int gamerCount;
    private Logger log;
    private JTextArea text;
    private String[] logins;

    public GameThread(Counter enabledPane, int gamerIndex, DataExchange dataE, JLabel lastCardLabel, int gamerCount, JTextArea text, String[] logins) {
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
            if (enabledPane.getCount() < gamerIndex) {
                for (; enabledPane.getCount() < gamerIndex; enabledPane.inc()) {
                    try {
                        //int asd = dataE.readInt();
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
                                break;
                        }
                    } catch (IOException ex) {
                        log.debug(ex.getMessage());
                    }

                }
            }
            if (enabledPane.getCount() > gamerIndex) {
                for (; enabledPane.getCount() < gamerCount; enabledPane.inc()) {
                    try {
                        //int asd = dataE.readInt();
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
                                break;
                        }
                    } catch (IOException ex) {
                        log.debug(ex.getMessage());
                    }

                }
                enabledPane.setCount(0);
            }
            //while(enabledPane==gamerIndex){
            try {
                wait();
            } catch (InterruptedException ex) {
                log.debug(ex.getMessage());
            }
            //}

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
    
    public synchronized void wakeUp(){
        notifyAll();
    }

}
