/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.NoSuchElementException;
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
import team5.desktop.actions.GamerController;
import team5.desktop.actions.RoomController;
import team5.desktop.actions.TableController;
import team5.desktop.actions.WorkUser;
import team5.desktop.actions.WorkWithFiles;
import team5.desktop.card.Card;

/**
 *
 * @author chanta
 */
public class GameFrame extends JFrame {

    private Logger log = Logger.getLogger(GameFrame.class);
    private boolean isTakeCard = false;
    private RoomController room;
    private TableController table;
    private GamerController gamer1;
    private GamerController gamer2;
    private JButton exitGameButton;
    private JButton rulesButton;
    private JButton takeCardButton;
    private JButton checkButton;
    private JButton finishMoveButton;
    private ButtonGroup buttonGroup1;
    private ButtonGroup buttonGroup2;
    private JLabel lastCardLabel;
    private JLabel uno;
    private JPanel panel;
    private JPanel pn1;
    private JPanel pn2;
    private JTabbedPane pane;

    public GameFrame() {
        initComponents();
    }

    private void initComponents() {

        
        room = new RoomController();
        gamer1 = new GamerController("Player1");
        room.addGamer(gamer1);
        gamer2 = new GamerController("Player2");
        room.addGamer(gamer2);
        table = room.getTableController();
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
        pn1 = new JPanel();
        pn2 = new JPanel();
        pn1.setBackground(Color.LIGHT_GRAY);
        pn2.setBackground(Color.LIGHT_GRAY);
        pane.addTab("Player1", pn1);
        pane.addTab("Player2", pn2);
        pane.setEnabledAt(1, false);// вторая вкладка заблокирована
        add(pane);

        //http://spec-zone.ru/RU/Java/Docs/7/api/javax/swing/ButtonGroup.html
        buttonGroup1 = new ButtonGroup();
        buttonGroup2 = new ButtonGroup();
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

        checkButton = new JButton();
        checkButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        checkButton.setText("Check");
        add(checkButton);
        checkButton.setBounds(650, 390, 100, 30);//310 300 80 30
        checkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkButtonActionPerformed(evt);
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

        finishMoveButton = new JButton();
        finishMoveButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        finishMoveButton.setText("Finish move");
        add(finishMoveButton);
        finishMoveButton.setBounds(650, 470, 100, 30);//310 300 80 30
        finishMoveButton.addActionListener(new java.awt.event.ActionListener() {
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
                }
//                catch (IOException ex) {
//                    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
//                } 
                catch (JAXBException ex) {
                    Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
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

        SelectRooms rooms = new SelectRooms();
        rooms.setVisible(true);
        this.setVisible(false);
    }

    private void rulesButtonActionPerformed(java.awt.event.ActionEvent evt) {

        Rules rules = new Rules();
        rules.setVisible(true);
        // this.setVisible(false);
    }

    private void checkButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (pane.isEnabledAt(0)) {
            pane.setSelectedIndex(1);
            pane.setEnabledAt(0, false);
            pane.setEnabledAt(1, true);
            isTakeCard = false;
        } else {
            pane.setSelectedIndex(0);
            pane.setEnabledAt(1, false);
            pane.setEnabledAt(0, true);
            isTakeCard = false;
        }
    }

    private void takeCardButtonActionPerformed(java.awt.event.ActionEvent evt) {

        if (pane.isEnabledAt(0)) {
            if (!isTakeCard) {
                pane.setSelectedIndex(0);
                JRadioButton jRadioButton = new JRadioButton();
                Card card = table.getCardFromPack();
                gamer1.addCardToHand(card);
                jRadioButton.setText(card.toString());
                jRadioButton.setForeground(isCardColor(card.getColor()));//color
                jRadioButton.setActionCommand(card.toString());
                //jRadioButton.addActionListener(aL); // монтируем Listener на кнопку
                buttonGroup1.add(jRadioButton);
                //выравниваем кнопку в центр панели по горизонтали
                jRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                pn1.add(jRadioButton);
                pn1.revalidate();
                pn1.repaint();
                isTakeCard = true;
            } else {
                JOptionPane.showConfirmDialog(null, "You can't take card more", "Wou wou", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (pane.isEnabledAt(1)) {
            if (!isTakeCard) {
                pane.setSelectedIndex(1);
                JRadioButton jRadioButton = new JRadioButton();
                Card card = table.getCardFromPack();
                gamer2.addCardToHand(card);
                jRadioButton.setText(card.toString());
                jRadioButton.setForeground(isCardColor(card.getColor()));//color
                jRadioButton.setActionCommand(card.toString());
                //jRadioButton.addActionListener(aL); // монтируем Listener на кнопку
                buttonGroup2.add(jRadioButton);
                //выравниваем кнопку в центр панели по горизонтали
                jRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                pn2.add(jRadioButton);
                pn2.revalidate();
                pn2.repaint();
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

        pane.setSelectedIndex(0);
        for (int i = 1; i <= 7; i++) {
            JRadioButton jRadioButton = new JRadioButton();
            Card card = table.getCardFromPack();
            gamer1.addCardToHand(card);
            jRadioButton.setText(card.toString());
            jRadioButton.setForeground(isCardColor(card.getColor()));//color
            jRadioButton.setActionCommand(card.toString());
            jRadioButton.setSelected(true);
            //jRadioButton.addActionListener(aL); // монтируем Listener на кнопку
            buttonGroup1.add(jRadioButton);
            //выравниваем кнопку в центр панели по горизонтали
            jRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            pn1.add(jRadioButton);
        }
        for (int i = 1; i <= 7; i++) {
            JRadioButton jRadioButton = new JRadioButton();
            Card card = table.getCardFromPack();
            gamer2.addCardToHand(card);
            jRadioButton.setText(card.toString());
            jRadioButton.setForeground(isCardColor(card.getColor()));//color
            jRadioButton.setActionCommand(card.toString());
            jRadioButton.setSelected(true);
            //jRadioButton.addActionListener(aL); // монтируем Listener на кнопку
            buttonGroup2.add(jRadioButton);
            //выравниваем кнопку в центр панели по горизонтали
            jRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            pn2.add(jRadioButton);
        }
    }

    private void finishMoveButtonActionPerformed(java.awt.event.ActionEvent evt) {

        if (pane.isEnabledAt(0)) {
            JRadioButton jr = new JRadioButton();
            if (buttonGroup1.getSelection() != null) {
                String str = buttonGroup1.getSelection().getActionCommand();

                Card card = gamer1.searchCardInHand(str);
                if (table.isRightCard(card)) {
                    // JRadioButton jr2 = null;
                    Enumeration en = buttonGroup1.getElements();
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
                    buttonGroup1.remove(jr);
                    boolean endgame = false;

                    if (buttonGroup1.getButtonCount() == 0) {
                        FinishFrame finish = new FinishFrame();
                        finish.setVisible(true);
                        this.setVisible(false);
                        endgame = true;
                    }
                    if (!endgame) {
                        /*try {
                         if ((jr2 == null) && (en.hasMoreElements())) {
                         en = buttonGroup1.getElements();
                         jr2 = (JRadioButton) en.nextElement();//выскакивае исключение есди нет следующего
                         }

                         buttonGroup1.setSelected(jr2.getModel(), true);
                         } catch (NullPointerException e) {
                         System.out.println(e.getMessage());
                         }*/
                        pn1.remove(jr);
                        pn1.revalidate();
                        pn1.repaint();
                        isTakeCard = false;

                        pane.setSelectedIndex(1);
                        pane.setEnabledAt(0, false);
                        pane.setEnabledAt(1, true);
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "This card isn't right", "Wou wou", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showConfirmDialog(null, "You didn't select card", "Wou wou", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }

        } else {

            JRadioButton jr = new JRadioButton();
            if (buttonGroup2.getSelection() != null) {
                String str = buttonGroup2.getSelection().getActionCommand();

                Card card = gamer2.searchCardInHand(str);
                if (table.isRightCard(card)) {
                    //JRadioButton jr2 = null;
                    Enumeration en = buttonGroup2.getElements();
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
                    buttonGroup2.remove(jr);
                    boolean endgame = false;
                    if (buttonGroup2.getButtonCount() == 0) {
                        FinishFrame finish = new FinishFrame();
                        finish.setVisible(true);
                        this.setVisible(false);
                        endgame = true;
                    }
                    if (!endgame) {
                        /*try {
                         if ((jr2 == null) && (en.hasMoreElements())) {
                         en = buttonGroup2.getElements();
                         jr2 = (JRadioButton) en.nextElement();//выскакивает исключение если нет следующего
                         }

                         buttonGroup2.setSelected(jr2.getModel(), true);
                         } catch (NullPointerException e) {
                         System.out.println(e.getMessage());
                         }*/
                        pn2.remove(jr);
                        pn2.revalidate();
                        pn2.repaint();
                        isTakeCard = false;

                        pane.setSelectedIndex(0);
                        pane.setEnabledAt(1, false);
                        pane.setEnabledAt(0, true);
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "This card isn't right", "Wou wou", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                JOptionPane.showConfirmDialog(null, "You didn't select card", "Wou wou", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

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
    }
}
