/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Enumeration;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import team5.client.actions.Flag;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import team5.client.actions.Counter;
import team5.client.actions.DataExchanger;
import team5.client.actions.GameThread;
import team5.datamodel.actions.WorkUser;
import team5.datamodel.transmissions.FileHandler;
import team5.datamodel.card.Card;
import team5.datamodel.card.NumericCard;
import team5.datamodel.transmissions.Message;
import team5.datamodel.transmissions.MessageHandler;

/**
 *
 * @author chanta
 */
public class GameFrame extends JFrame {

    private Flag canExit;
    private Counter turnIndex;
    private Counter gamerIndex;
    private final JPanel panels[];
    private ButtonGroup buttonGroups[];
    private Counter gamerCount;
    private String myLogin;
    private String[] logins;
    private DataExchanger dataE;
    private Logger logger = Logger.getLogger(GameFrame.class);
    private boolean isTakeCard = false;
    private JButton exitGameButton;
    private JButton rulesButton;
    private JButton takeCardButton;
    private JButton passButton;
    private JButton endTurnButton;
    private JLabel lastCardLabel;
    private JLabel uno;
    private JPanel panel;
    private JTabbedPane pane;
    private JTextArea text;
    private JScrollPane scroll;
    private MessageHandler messageHandler;
    private GameThread game;

    public GameFrame(MessageHandler messageHandler) {
        canExit = new Flag(false);
        this.messageHandler = messageHandler;
        turnIndex = new Counter();
        try {
            Message message = messageHandler.receiveMessage();
            myLogin = message.getChoice();
            gamerCount = new Counter(message.getValue());
            gamerIndex = new Counter();
            logins = new String[gamerCount.getCount()];
            for (int i = 0; i < gamerCount.getCount(); i++) {
                logins[i] = messageHandler.receiveMessage().getChoice();
                if (logins[i].equals(myLogin)) {
                    gamerIndex.setCount(i);
                }
            }
        } catch (IOException ex) {
            logger.debug(ex.getMessage());
        } catch (JAXBException ex) {
            logger.debug(ex.getMessage());
        }
        panels = new JPanel[gamerCount.getCount()];

        initStartFrame();
        initComponentsButton();
        initComponents();
        initCloseOperation();
        turnIndex.setCount(0);
        game = new GameThread(turnIndex, gamerIndex, this.messageHandler, lastCardLabel, gamerCount, text, logins, canExit);
        game.start();
        //gameOtherUsers();
    }

    private void initStartFrame() {

        setPreferredSize(new Dimension(800, 600));
        setLayout(null);
        //setBounds(200, 10, 710, 790);
        this.setResizable(false);
        setTitle("Game");
    }

    //http://spec-zone.ru/RU/Java/Docs/7/api/javax/swing/ButtonGroup.html
    private void initComponentsButton() {
        buttonGroups = new ButtonGroup[gamerCount.getCount()];
        for (int i = 0; i < gamerCount.getCount(); i++) {
            buttonGroups[i] = new ButtonGroup();
        }

        exitGameButton = new JButton();
        exitGameButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        exitGameButton.setText("Exit game");
        add(exitGameButton);
        exitGameButton.setBounds(50, 70, 100, 30);//310 300 80 30
        exitGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitGameButtonActionPerformed(evt);
            }
        });

        rulesButton = new JButton();
        rulesButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        rulesButton.setText("Rules");
        add(rulesButton);
        rulesButton.setBounds(50, 30, 100, 30);//310 300 80 30
        rulesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rulesButtonActionPerformed(evt);
            }
        });

        passButton = new JButton();
        passButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        passButton.setText("Pass");
        add(passButton);
        passButton.setBounds(650, 390, 100, 30);//310 300 80 30
        passButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passButtonActionPerformed(evt);
            }
        });

        takeCardButton = new JButton();
        takeCardButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        takeCardButton.setText("Take card");
        add(takeCardButton);
        takeCardButton.setBounds(650, 430, 100, 30);//310 300 80 30
        takeCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takeCardButtonActionPerformed(evt);
            }
        });

        endTurnButton = new JButton();
        endTurnButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        endTurnButton.setText("END TURN");
        add(endTurnButton);
        endTurnButton.setBounds(650, 470, 100, 30);//310 300 80 30
        endTurnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endTurnButtonActionPerformed(evt);
            }
        });
    }

    private void initComponents() {
        panel = new JPanel();
        TitledBorder border = new TitledBorder("");
        border.setTitleFont(new java.awt.Font("Comic Sans MS", 0, 13));
        border.setTitle("Last card");
        border.setTitleJustification(2);
        panel.setBorder(border);
        panel.setBounds(200, 200, 200, 100);
        panel.setLayout(null);
        add(panel);

        scroll = new JScrollPane();
        text = new JTextArea();
        text.setLineWrap(true);
        scroll.setViewportView(text);
        add(scroll);
        scroll.setBounds(450, 200, 300, 120);

        pane = new JTabbedPane();
        pane.setBounds(50, 300, 500, 200);
        pane.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        for (int i = 0; i < gamerCount.getCount(); i++) {
            panels[i] = new JPanel();
            panels[i].setBackground(Color.LIGHT_GRAY);
            pane.addTab(logins[i], panels[i]);
            pane.setEnabledAt(i, false);
        }
        add(pane);

        firstDistribution();
        uno = new JLabel();
        uno.setFont(new java.awt.Font("Comic Sans MS", 0, 42)); // NOI18N
        uno.setText("UNO");
        add(uno);
        uno.setBounds(350, 38, 200, 60);

        lastCardLabel = new JLabel();
        lastCardLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        lastCardLabel.setText("Select start card");
        panel.add(lastCardLabel);
        lastCardLabel.setBounds(10, 40, 170, 30);

    }

    private void initCloseOperation() {

        addWindowListener(new WindowListener() {

            @Override
            public void windowActivated(WindowEvent event) {
            }

            @Override
            public void windowClosed(WindowEvent event) {
            }

            @Override
            public void windowClosing(WindowEvent event) {
                try {
                    /*WorkUser workUser = WorkUser.getWork();
                     FileHandler workWithFiles = new FileHandler();
                     workWithFiles.marshalData("marshalData_WorkUser.xml", workUser);
                     //throw new UnsupportedOperationException("Not supported yet.");
                     } catch (JAXBException ex) {
                     logger.debug(ex.getMessage());
                     */
                } finally {
                    exit();
                }
            }

            @Override
            public void windowDeactivated(WindowEvent event) {
            }

            @Override
            public void windowDeiconified(WindowEvent event) {
            }

            @Override
            public void windowIconified(WindowEvent event) {
            }

            @Override
            public void windowOpened(WindowEvent event) {
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        this.setLocationRelativeTo(null);
    }

    private void exitGameButtonActionPerformed(java.awt.event.ActionEvent evt) {
        exit();
    }

    private void exit() {
        if ((turnIndex.getCount() == gamerIndex.getCount()) || (canExit.isFlag() == true)) {
            try {
                messageHandler.sendMessage(new Message("Exit"));
                RoomSelectionFrame rooms = new RoomSelectionFrame(messageHandler);
                rooms.setVisible(true);
                this.dispose();
            } catch (IOException ex) {
                logger.debug(ex.getMessage());
            } catch (JAXBException ex) {
                logger.debug(ex.getMessage());
            }

        } else {
            JOptionPane.showConfirmDialog(null, "It's not your turn", "Wou wou", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void rulesButtonActionPerformed(java.awt.event.ActionEvent evt) {

        RulesFrame rules = new RulesFrame();
        rules.setVisible(true);
    }

    private synchronized void passButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (turnIndex.getCount() == gamerIndex.getCount()) {
            if (isTakeCard) {
                isTakeCard = false;

                try {
                    messageHandler.sendMessage(new Message("Pass"));
                } catch (IOException ex) {
                    logger.debug(ex.getMessage());
                } catch (JAXBException ex) {
                    logger.debug(ex.getMessage());
                }
                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": Pass");
                if (turnIndex.getCount() + 1 < gamerCount.getCount()) {
                    turnIndex.inc();
                } else {
                    turnIndex.setCount(0);
                }
                game.wakeUp();
            } else {
                JOptionPane.showConfirmDialog(null, "You didn't take card", "Wou wou", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showConfirmDialog(null, "It's not your turn", "Wou wou", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void takeCardButtonActionPerformed(java.awt.event.ActionEvent evt) {

        if (turnIndex.getCount() == gamerIndex.getCount()) {
            if (!isTakeCard) {
                pane.setSelectedIndex(gamerIndex.getCount());
                JRadioButton jRadioButton = new JRadioButton();
                Card card = null;
                try {
                    messageHandler.sendMessage(new Message("TakeCard"));
                    card = messageHandler.receiveMessage().getCard();
                } catch (IOException ex) {
                    logger.debug(ex.getMessage());
                } catch (JAXBException ex) {
                    logger.debug(ex.getMessage());
                }
                jRadioButton.setText(card.toString());
                jRadioButton.setForeground(isCardColor(card.getColor()));//color
                jRadioButton.setActionCommand(card.toString());
                buttonGroups[gamerIndex.getCount()].add(jRadioButton);
                jRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                panels[gamerIndex.getCount()].add(jRadioButton);
                panels[gamerIndex.getCount()].revalidate();
                panels[gamerIndex.getCount()].repaint();
                isTakeCard = true;
                text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": Take card");
            } else {
                JOptionPane.showConfirmDialog(null, "You can't take card more", "Wou wou", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showConfirmDialog(null, "It's not your turn", "Wou wou", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Color isCardColor(String color) {
        if ("blue".equals(color)) {
            return Color.BLUE;
        } else if ("yellow".equals(color)) {
            return Color.YELLOW;
        } else if ("green".equals(color)) {
            return Color.GREEN;
        } else {
            return Color.RED;
        }

    }

    private void firstDistribution() {

        for (int j = 0; j < gamerCount.getCount(); j++) {
            if (logins[j].equals(myLogin)) {
                pane.setSelectedIndex(gamerIndex.getCount());
                pane.setEnabledAt(gamerIndex.getCount(), true);
                for (int i = 1; i <= 7; i++) {
                    JRadioButton jRadioButton = new JRadioButton();
                    Card card = null;
                    try {
                        card = messageHandler.receiveMessage().getCard();
                    } catch (IOException ex) {
                        logger.debug(ex.getMessage());
                    } catch (JAXBException ex) {
                        logger.debug(ex.getMessage());
                    }
                    jRadioButton.setText(card.toString());
                    jRadioButton.setForeground(isCardColor(card.getColor()));//color
                    jRadioButton.setActionCommand(card.toString());
                    jRadioButton.setSelected(true);
                    buttonGroups[gamerIndex.getCount()].add(jRadioButton);
                    jRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                    panels[gamerIndex.getCount()].add(jRadioButton);
                }
            }
        }
    }

    private synchronized void endTurnButtonActionPerformed(java.awt.event.ActionEvent evt) {

        if (turnIndex.getCount() == gamerIndex.getCount()) {
            JRadioButton jr = new JRadioButton();
            if (buttonGroups[gamerIndex.getCount()].getSelection() != null) {
                String str = buttonGroups[gamerIndex.getCount()].getSelection().getActionCommand();
                Card card = null;
                try {
                    Message clientRequest = new Message("END TURN");
                    clientRequest.setChoice(str);
                    messageHandler.sendMessage(clientRequest);
                    //dataE.write(str);
                    Message serverResponse = messageHandler.receiveMessage();
                    card = serverResponse.getCard();
                    if (serverResponse.getConfirmation() == true) {
                        Enumeration en = buttonGroups[gamerIndex.getCount()].getElements();
                        while (en.hasMoreElements()) {
                            jr = (JRadioButton) en.nextElement();
                            if (jr.getText().equals(str)) {
                                break;
                            }
                        }

                        lastCardLabel.setText(str);
                        lastCardLabel.setForeground(isCardColor(card.getColor()));//color
                        buttonGroups[gamerIndex.getCount()].remove(jr);
                        boolean endgame = false;
                        text.setText(text.getText() + "\n" + logins[turnIndex.getCount()] + ": End turn " + card.getIconId() + " " + card.getColor());
                        if (buttonGroups[gamerIndex.getCount()].getButtonCount() == 0) {
                            messageHandler.sendMessage(new Message(true));
                            FinishFrame finish = new FinishFrame(dataE);
                            finish.setVisible(true);
                            this.setVisible(false);
                            endgame = true;
                        }
                        if (!endgame) {
                            panels[gamerIndex.getCount()].remove(jr);
                            panels[gamerIndex.getCount()].revalidate();
                            panels[gamerIndex.getCount()].repaint();
                            isTakeCard = false;
                            messageHandler.sendMessage(new Message(false));
                            if (turnIndex.getCount() + 1 < gamerCount.getCount()) {
                                turnIndex.inc();
                            } else {
                                turnIndex.setCount(0);
                            }
                            game.wakeUp();
                        }

                    } else {
                        JOptionPane.showConfirmDialog(null, "This card isn't right", "Wou wou", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (IOException ex) {
                    logger.debug(ex.getMessage());
                } catch (JAXBException ex) {
                    logger.debug(ex.getMessage());
                }
            } else {
                JOptionPane.showConfirmDialog(null, "You didn't select card", "Wou wou", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showConfirmDialog(null, "It's not your turn", "Wou wou", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
