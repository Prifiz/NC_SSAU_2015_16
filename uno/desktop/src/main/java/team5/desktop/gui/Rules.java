/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import team5.desktop.actions.WorkWithFiles;
import team5.desktop.actions.WorkUser;
import javax.swing.*;
import javax.xml.bind.JAXBException;

/**
 *
 * @author chanta
 */
public class Rules extends JFrame {

    private JButton backButton;
    private JLabel rulesLabel;
    private JScrollPane jScrollPane;
    private JTextArea ruleTextArea;
    /**
     * Creates new form Rules
     */
    public Rules() {
        initComponents();
    }

    private String textRule(String fileName) throws FileNotFoundException, IOException {
        String text = "";
        String s = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((s = br.readLine()) != null) {
                text += s;
            }
            return text;
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("File not found.");
        }
    }

    
    @SuppressWarnings("unchecked")                       
    private void initComponents() {

        rulesLabel = new JLabel();
        jScrollPane = new JScrollPane();
        ruleTextArea = new JTextArea();
        backButton = new JButton();
        
        setPreferredSize(new Dimension(400, 330));
        setLayout(null);
        this.setResizable(false);

        rulesLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        rulesLabel.setText("Rules");
        add(rulesLabel);
        rulesLabel.setBounds(165, 10, 100, 60);

        ruleTextArea.setEditable(false);
        ruleTextArea.setColumns(20);
        ruleTextArea.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        ruleTextArea.setRows(5);
        ruleTextArea.setLineWrap(true);
        try {
            //jTextArea1.setText("      Players are dealt 7 cards. The top card of \nthe deck is turned face up and with it the game \nbegins (this card is the beginning of the game \ndeck).\n      The game begins in a clockwise direction.\n      On his turn, each player must put a card in \nthe game deck, and the card must match the \ntop card in the game deck on color or image. \nIf the player does not have a suitable card, \nthen he takes one card from the deck of the \nbank, and if the card is suitable, it could make \na move if he wants to. If a player has taken \nfrom the deck of the bank the right card, but \nshe did not move - he receives no fine. The \nnext player to act in a clockwise direction.\n");
            ruleTextArea.setText(textRule("textRule.txt"));
        } catch (IOException ex) {
            Logger.getLogger(Rules.class.getName()).log(Level.SEVERE, null, ex);
        }
        jScrollPane.setViewportView(ruleTextArea);
        add(jScrollPane);
        jScrollPane.setBounds(30, 80, 320, 150);

        backButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        backButton.setText("Back");
        add(backButton);
        backButton.setBounds(150, 250, 100, 30);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        addWindowListener(new WindowListener() {

            public void windowActivated(WindowEvent event) {
            }

            public void windowClosed(WindowEvent event) {
            }

            public void windowClosing(WindowEvent event) {
                try {
                    WorkUser workUser = WorkUser.getWork();
                    WorkWithFiles workWithFiles = new WorkWithFiles();
                    //sd.serializableData("serializableData_WorkUser.bin", wu);
                    workWithFiles.marshalData("marshalData_WorkUser.xml", workUser);
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } 
//                catch (IOException ex) {
//                    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
//                } 
                catch (JAXBException ex) {
                    Logger.getLogger(Rules.class.getName()).log(Level.SEVERE, null, ex);
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

//       

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
        this.setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
      //  StartFrame start = new StartFrame();
      //  start.setVisible(true);
        this.setVisible(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Rules.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rules.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rules.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rules.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Rules().setVisible(true);
            }
        });
    }

                     
}
