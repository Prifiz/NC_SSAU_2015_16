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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import team5.client.actions.DataExchange;
import team5.client.actions.WorkUser;
import team5.client.actions.WorkWithFiles;
import team5.client.card.Card;
import team5.client.card.NumericCard;

/**
 *
 * @author chanta
 */
public class GameFrame extends JFrame {

    private int enabledPane;
    private int gamerIndex;
    private JPanel panels[];
    private ButtonGroup buttonGroups[];
    private int gamerCount;
    private String myLogin;
    private String[] logins;
    private DataExchange dataE;
    private Logger log = Logger.getLogger(GameFrame.class);
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

    public GameFrame(DataExchange dataE) {
        this.dataE = dataE;
        try {
            myLogin = dataE.readString();
            gamerCount = dataE.readInt();
            logins = new String[gamerCount];
            for (int i = 0; i < gamerCount; i++) {
                logins[i] = dataE.readString();
                if (logins[i].equals(myLogin)) {
                    gamerIndex = i;
                }
            }
        } catch (IOException ex) {
            log.debug(ex.getMessage());
        }
        panels = new JPanel[gamerCount];
        initComponents();
        enabledPane = 0;
        gameOtherUsers();
    }

    private void initComponents() {

        setPreferredSize(new Dimension(800, 600));
        setLayout(null);
        //setBounds(200, 10, 710, 790);
        this.setResizable(false);
        setTitle("Game");

        panel = new JPanel();
        TitledBorder border = new TitledBorder("");
        border.setTitleFont(new java.awt.Font("Comic Sans MS", 0, 13));
        border.setTitle("Last card");
        border.setTitleJustification(2);
        panel.setBorder(border);
        panel.setBounds(300, 200, 200, 100);
        panel.setLayout(null);
        add(panel);

        pane = new JTabbedPane();
        pane.setBounds(50, 300, 500, 200);
        pane.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        for (int i = 0; i < gamerCount; i++) {
            panels[i] = new JPanel();
            panels[i].setBackground(Color.LIGHT_GRAY);
            pane.addTab(logins[i], panels[i]);
            if (i > 0) {
                pane.setEnabledAt(i, false);
            }
        }
        add(pane);

        //http://spec-zone.ru/RU/Java/Docs/7/api/javax/swing/ButtonGroup.html
        buttonGroups = new ButtonGroup[gamerCount];
        for (int i = 0; i < gamerCount; i++) {
            buttonGroups[i] = new ButtonGroup();
        }
        firstDistribution();
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
                finishMoveButtonActionPerformed(evt);
            }
        });

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

        addWindowListener(new WindowListener() {

            public void windowActivated(WindowEvent event) {
            }

            public void windowClosed(WindowEvent event) {
            }

            public void windowClosing(WindowEvent event) {
                try {
                    WorkUser workUser = WorkUser.getWork();
                    WorkWithFiles workWithFiles = new WorkWithFiles();
                    //sd.serializableData("serializableData_WorkUser.bin", workUser);
                    workWithFiles.marshalData("marshalData_WorkUser.xml", workUser);
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } //                catch (IOException ex) {
                //                    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
                //                } 
                catch (JAXBException ex) {
                    log.debug(ex.getMessage());
                } finally {
                    event.getWindow().setVisible(false);
                    System.exit(0);
                }
            }

            public void windowDeactivated(WindowEvent event) {
            }

            public void windowDeiconified(WindowEvent event) {
            }

            public void windowIconified(WindowEvent event) {
            }

            public void windowOpened(WindowEvent event) {
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
        this.setLocationRelativeTo(null);
    }

    private void exitGameButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            dataE.write("Exit");
        } catch (IOException ex) {
            log.debug(ex.getMessage());
        }
        SelectRooms rooms = new SelectRooms(dataE);
        rooms.setVisible(true);
        this.setVisible(false);
    }

    private void rulesButtonActionPerformed(java.awt.event.ActionEvent evt) {

        Rules rules = new Rules();
        rules.setVisible(true);
        // this.setVisible(false);
    }

    private void passButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (enabledPane == gamerIndex) {
            pane.setEnabledAt(enabledPane, false);
            isTakeCard = false;

            try {
                dataE.write("Pass");
            } catch (IOException ex) {
                log.debug(ex.getMessage());
            }
            if (enabledPane + 1 < gamerCount) {
                enabledPane++;
            } else {
                enabledPane = 0;
            }
            gameOtherUsers();
        }
        //метод игры остальных игроков

        /*if (pane.isEnabledAt(0)) {
         pane.setSelectedIndex(1);
         pane.setEnabledAt(0, false);
         pane.setEnabledAt(1, true);
         isTakeCard = false;
         } else {
         pane.setSelectedIndex(0);
         pane.setEnabledAt(1, false);
         pane.setEnabledAt(0, true);
         isTakeCard = false;
         }*/
    }

    private void takeCardButtonActionPerformed(java.awt.event.ActionEvent evt) {

        if (enabledPane == gamerIndex) {
            if (!isTakeCard) {
                pane.setSelectedIndex(gamerIndex);
                JRadioButton jRadioButton = new JRadioButton();
                Card card = null;
                try {
                    dataE.write("TakeCard");
                    card = new NumericCard(dataE.readInt(), dataE.readString());
                } catch (IOException ex) {
                    log.debug(ex.getMessage());
                }
                jRadioButton.setText(card.toString());
                jRadioButton.setForeground(isCardColor(card.getColor()));//color
                jRadioButton.setActionCommand(card.toString());
                //jRadioButton.addActionListener(aL); // монтируем Listener на кнопку
                buttonGroups[gamerIndex].add(jRadioButton);
                //выравниваем кнопку в центр панели по горизонтали
                jRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                panels[gamerIndex].add(jRadioButton);
                panels[gamerIndex].revalidate();
                panels[gamerIndex].repaint();
                isTakeCard = true;
            } else {
                JOptionPane.showConfirmDialog(null, "You can't take card more", "Wou wou", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
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

    private void firstDistribution() {

        for (int j = 0; j < gamerCount; j++) {
            if (logins[j].equals(myLogin)) {
                pane.setSelectedIndex(gamerIndex);
                //enabledPane = gamerIndex;
                for (int i = 1; i <= 7; i++) {
                    JRadioButton jRadioButton = new JRadioButton();
                    //Card card = table.getCardFromPack();
                    //gamer1.addCardToHand(card);
                    Card card = null;
                    try {
                        card = new NumericCard(dataE.readInt(), dataE.readString());
                    } catch (IOException ex) {
                        log.debug(ex.getMessage());
                    }
                    jRadioButton.setText(card.toString());
                    jRadioButton.setForeground(isCardColor(card.getColor()));//color
                    jRadioButton.setActionCommand(card.toString());
                    jRadioButton.setSelected(true);
                    //jRadioButton.addActionListener(aL); // монтируем Listener на кнопку
                    buttonGroups[gamerIndex].add(jRadioButton);
                    //выравниваем кнопку в центр панели по горизонтали
                    jRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                    panels[gamerIndex].add(jRadioButton);
                }
            } else {
                for (int i = 1; i <= 7; i++) {
                    JRadioButton jRadioButton = new JRadioButton();
                    jRadioButton.setText("Card");
                    jRadioButton.setActionCommand("Card");
                    jRadioButton.setSelected(true);
                    //jRadioButton.addActionListener(aL); // монтируем Listener на кнопку
                    buttonGroups[j].add(jRadioButton);
                    //выравниваем кнопку в центр панели по горизонтали
                    jRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                    panels[j].add(jRadioButton);
                }
            }
        }
    }

    private void finishMoveButtonActionPerformed(java.awt.event.ActionEvent evt) {

        if (enabledPane == gamerIndex) {
            JRadioButton jr = new JRadioButton();
            if (buttonGroups[gamerIndex].getSelection() != null) {
                String str = buttonGroups[gamerIndex].getSelection().getActionCommand();
                Card card = null;
                try {
                    dataE.write("END TURN");
                    dataE.write(str);
                    card = new NumericCard(dataE.readInt(), dataE.readString());

                    //Card card = gamer1.searchCardInHand(str);
                    if (dataE.readBool()) {//table.isRightCard(card)
                        // JRadioButton jr2 = null;
                        Enumeration en = buttonGroups[gamerIndex].getElements();
                        while (en.hasMoreElements()) {
                            jr = (JRadioButton) en.nextElement();
                            if (jr.getText().equals(str)) {

                                break;
                            } else {
                                //jr2 = jr;
                            }
                        }

                        lastCardLabel.setText(str);
                        lastCardLabel.setForeground(isCardColor(card.getColor()));//color
                        buttonGroups[gamerIndex].remove(jr);
                        boolean endgame = false;

                        if (buttonGroups[gamerIndex].getButtonCount() == 0) {
                            FinishFrame finish = new FinishFrame(dataE);
                            finish.setVisible(true);
                            this.setVisible(false);
                            endgame = true;
                        }
                        if (!endgame) {
                            panels[gamerIndex].remove(jr);
                            panels[gamerIndex].revalidate();
                            panels[gamerIndex].repaint();
                            isTakeCard = false;
                        }
                        if (enabledPane + 1 < gamerCount) {
                            enabledPane++;
                        } else {
                            enabledPane = 0;
                        }
                        gameOtherUsers();

                    } else {
                        JOptionPane.showConfirmDialog(null, "This card isn't right", "Wou wou", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (IOException ex) {
                    log.debug(ex.getMessage());
                }
            } else {
                JOptionPane.showConfirmDialog(null, "You didn't select card", "Wou wou", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void gameOtherUsers() {
        if (enabledPane < gamerIndex) {
            for (; enabledPane < gamerIndex; enabledPane++) {
                pane.setEnabledAt(enabledPane, true);
                pane.setSelectedIndex(enabledPane);
                try {
                    //int asd = dataE.readInt();
                    String command = dataE.readString();
                    switch (command) {
                        case "Pass"://надо бы сделать лэйбл, который будет отражать ходы противника.
                            pane.setEnabledAt(enabledPane, false);
                            break;
                        case "TakeCard":
                            JRadioButton jRadioButton = new JRadioButton();
                            jRadioButton.setText("Card");
                            jRadioButton.setActionCommand("Card");
                            jRadioButton.setSelected(true);
                            //jRadioButton.addActionListener(aL); // монтируем Listener на кнопку
                            buttonGroups[enabledPane].add(jRadioButton);
                            //выравниваем кнопку в центр панели по горизонтали
                            jRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                            panels[enabledPane].add(jRadioButton);
                            panels[enabledPane].revalidate();
                            panels[enabledPane].repaint();
                            enabledPane--;
                            break;
                        case "END TURN":
                            Card card = new NumericCard(dataE.readInt(), dataE.readString());
                            lastCardLabel.setText(card.toString());
                            lastCardLabel.setForeground(isCardColor(card.getColor()));//color
                            Enumeration en = buttonGroups[enabledPane].getElements();
                            JRadioButton jr = (JRadioButton) en.nextElement();
                            buttonGroups[enabledPane].remove(jr);
                            panels[enabledPane].revalidate();
                            panels[enabledPane].repaint();
                            pane.setEnabledAt(enabledPane, false);
                            break;
                    }
                } catch (IOException ex) {
                    log.debug(ex.getMessage());
                }

            }
        }
        pane.setEnabledAt(enabledPane, true);
        pane.setSelectedIndex(enabledPane);
        if (enabledPane > gamerIndex) {
            for (; enabledPane < gamerCount; enabledPane++) {
                pane.setEnabledAt(enabledPane, true);
                pane.setSelectedIndex(enabledPane);
                try {
                    //int asd = dataE.readInt();
                    String command = dataE.readString();
                    switch (command) {
                        case "Pass"://надо бы сделать лэйбл, который будет отражать ходы противника.
                            pane.setEnabledAt(enabledPane, false);
                            break;
                        case "TakeCard":
                            JRadioButton jRadioButton = new JRadioButton();
                            jRadioButton.setText("Card");
                            jRadioButton.setActionCommand("Card");
                            jRadioButton.setSelected(true);
                            //jRadioButton.addActionListener(aL); // монтируем Listener на кнопку
                            buttonGroups[enabledPane].add(jRadioButton);
                            //выравниваем кнопку в центр панели по горизонтали
                            jRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                            panels[enabledPane].add(jRadioButton);
                            panels[enabledPane].revalidate();
                            panels[enabledPane].repaint();
                            enabledPane--;
                            break;
                        case "END TURN":
                            Card card = new NumericCard(dataE.readInt(), dataE.readString());
                            lastCardLabel.setText(card.toString());
                            lastCardLabel.setForeground(isCardColor(card.getColor()));//color
                            Enumeration en = buttonGroups[enabledPane].getElements();
                            JRadioButton jr = (JRadioButton) en.nextElement();
                            buttonGroups[enabledPane].remove(jr);
                            panels[enabledPane].revalidate();
                            panels[enabledPane].repaint();
                            pane.setEnabledAt(enabledPane, false);
                            break;
                    }
                } catch (IOException ex) {
                    log.debug(ex.getMessage());
                }

            }
            enabledPane = 0;
            gameOtherUsers();
        }

    }
}

/**
 * @param args the command line arguments
 */
/*public static void main(String args[]) {

 Logger log = Logger.getLogger(GameFrame.class);
 try {
 for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
 if ("Nimbus".equals(info.getName())) {
 javax.swing.UIManager.setLookAndFeel(info.getClassName());
 break;
 }
 }
 } catch (ClassNotFoundException ex) {
 log.debug(ex.getMessage());
 } catch (InstantiationException ex) {
 log.debug(ex.getMessage());
 } catch (IllegalAccessException ex) {
 log.debug(ex.getMessage());
 } catch (javax.swing.UnsupportedLookAndFeelException ex) {
 log.debug(ex.getMessage());
 }

 java.awt.EventQueue.invokeLater(new Runnable() {
 public void run() {
 new GameFrame().setVisible(true);

 }
 });
 }*/
