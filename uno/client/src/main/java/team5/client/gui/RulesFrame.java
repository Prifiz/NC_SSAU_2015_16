/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;
import org.apache.log4j.Logger;

/**
 *
 * @author chanta
 */
public class RulesFrame extends JFrame {

    private Logger log = Logger.getLogger(RulesFrame.class);
    private JButton backButton;
    private JLabel rulesLabel;
    private JScrollPane jScrollPane;
    private JTextArea ruleTextArea;

    /**
     * Creates new form Rules
     */
    public RulesFrame() {
        initStartFrame();
        initComponents();
        initCloseOperation();
    }

    private String textRule(String fileName) {
        String text = "";
        String s;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName)));
            while ((s = br.readLine()) != null) {
                text += s;
            }

        } catch (FileNotFoundException ex) {
            log.debug(ex.getMessage());
        } catch (IOException ex) {
            log.debug(ex.getMessage());
        }
        return text;

    }

    private void initStartFrame() {

        setPreferredSize(new Dimension(400, 330));
        setLayout(null);
        this.setResizable(false);

    }

    private void initComponents() {

        rulesLabel = new JLabel();
        rulesLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        rulesLabel.setText("Rules");
        add(rulesLabel);
        rulesLabel.setBounds(165, 10, 100, 60);

        ruleTextArea = new JTextArea();
        ruleTextArea.setEditable(false);
        ruleTextArea.setColumns(20);
        ruleTextArea.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        ruleTextArea.setRows(5);
        ruleTextArea.setLineWrap(true);

        jScrollPane = new JScrollPane();

        //jTextArea1.setText("      Players are dealt 7 cards. The top card of \nthe deck is turned face up and with it the game \nbegins (this card is the beginning of the game \ndeck).\n      The game begins in a clockwise direction.\n      On his turn, each player must put a card in \nthe game deck, and the card must match the \ntop card in the game deck on color or image. \nIf the player does not have a suitable card, \nthen he takes one card from the deck of the \nbank, and if the card is suitable, it could make \na move if he wants to. If a player has taken \nfrom the deck of the bank the right card, but \nshe did not move - he receives no fine. The \nnext player to act in a clockwise direction.\n");
        ruleTextArea.setText(textRule("/textRule.txt"));
        jScrollPane.setViewportView(ruleTextArea);
        add(jScrollPane);
        jScrollPane.setBounds(30, 80, 320, 150);

        backButton = new JButton();
        backButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        backButton.setText("Back");
        add(backButton);
        backButton.setBounds(150, 250, 100, 30);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

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
                    //sd.serializableData("serializableData_WorkUser.bin", wu);
                    workWithFiles.marshalData("marshalData_WorkUser.xml", workUser);
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } //                catch (IOException ex) {
                //                    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
                //                } 
                catch (JAXBException ex) {
                    log.debug(ex.getMessage());
                */} finally {
                    event.getWindow().setVisible(false);
                    dispose();
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

//       
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        this.setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        Logger log = Logger.getLogger(RulesFrame.class);
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
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RulesFrame().setVisible(true);
            }
        });
    }*/
}
