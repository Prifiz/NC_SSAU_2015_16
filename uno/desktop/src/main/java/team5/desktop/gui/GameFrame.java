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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import team5.desktop.actions.WorkUser;
import team5.desktop.actions.WorkWithFiles;

/**
 *
 * @author chanta
 */
public class GameFrame extends JFrame {

    private JButton exitGameButton;
    private JButton rulesButton;
    private JButton takeCardButton;
    private JButton finishMoveButton;
    private ButtonGroup buttonGroup;
    private JLabel lastCardLabel;
    private JLabel uno;
    private JPanel panel;
    private JTabbedPane pane;
    private JPanel pn1;
    private JPanel pn2;

    public GameFrame() {
        initComponents();
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
        pn1 = new JPanel();
        pn2 = new JPanel();
        pn1.setBackground(Color.white);
        pn2.setBackground(Color.white);
        pane.add(pn1, "Player1");
        pane.add(pn2, "Player2");
        pane.setEnabledAt(1, false);// вторая вкладка заблокирована
        add(pane);

        //http://spec-zone.ru/RU/Java/Docs/7/api/javax/swing/ButtonGroup.html
        buttonGroup = new ButtonGroup();
        for (int i = 1; i <= 4; i++) {
            JRadioButton jRadioButton = new JRadioButton();
            jRadioButton.setText("Card " + i);
            //jRadioButton.addActionListener(aL); // монтируем Listener на кнопку
            buttonGroup.add(jRadioButton);
            //выравниваем кнопку в центр панели по горизонтали
            jRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            pn1.add(jRadioButton);
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
        lastCardLabel.setText("Last card");
        panel.add(lastCardLabel);
        lastCardLabel.setBounds(30, 40, 120, 30);

        addWindowListener(new WindowListener() {

            public void windowActivated(WindowEvent event) {
            }

            public void windowClosed(WindowEvent event) {
            }

            public void windowClosing(WindowEvent event) {
                try {
                    WorkUser workUser = WorkUser.getWork();
                    WorkWithFiles sd = new WorkWithFiles();
                    sd.serializableData("serializableData_WorkUser.bin", workUser);

                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } catch (IOException ex) {
                    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        this.setVisible(false);
    }

    private void takeCardButtonActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void finishMoveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (pane.isEnabledAt(0)) {
            pane.setEnabledAt(0, false);// первая вкладка заблокирована
            pane.setEnabledAt(1, true);//вторая вкладка активная
        } else {
            pane.setEnabledAt(0, true);// вторая вкладка заблокирована
            pane.setEnabledAt(1, false);//первая вкладка активная  
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MemberList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemberList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemberList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemberList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }
}
