/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.apache.log4j.Logger;
import team5.client.actions.WorkUser;
import team5.client.actions.WorkWithFiles;

/**
 *
 * @author chanta
 */
public class AdminRoom extends JFrame {

    private Logger log = Logger.getLogger(AdminRoom.class);
    private JButton memberListButton;
    private JButton cardListButton;
    private JButton backButton;
    private JLabel adminLabel;

    public AdminRoom() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setPreferredSize(new Dimension(400, 300));
        setLayout(null);
        this.setResizable(false);
        setTitle("Admin room");

        memberListButton = new JButton();
        memberListButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        memberListButton.setText("Member list");
        add(memberListButton);
        memberListButton.setBounds(120, 110, 150, 30);//310 300 80 30
        memberListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberListButtonActionPerformed(evt);
            }
        });
        
        cardListButton = new JButton();
        cardListButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        cardListButton.setText("Card list");
        add(cardListButton);
        cardListButton.setBounds(120, 150, 150, 30);//310 300 80 30
        cardListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardListButtonActionPerformed(evt);
            }
        });

        backButton = new JButton();
        backButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        backButton.setText("Back select rooms");
        add(backButton);
        backButton.setBounds(120, 190, 150, 30);//310 300 80 30
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        adminLabel = new JLabel();
        adminLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        adminLabel.setText("Admin room");
        add(adminLabel);
        adminLabel.setBounds(135, 30, 150, 30);

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
                catch (JAXBException ex) {
                   log.debug(ex.getMessage());
                }//                catch (IOException ex) {
//                    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
//                }
                  finally {
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

    private void memberListButtonActionPerformed(java.awt.event.ActionEvent evt) {
       
        MemberList list = new MemberList();
        list.setVisible(true);
        this.setVisible(false);
    }

    private void cardListButtonActionPerformed(java.awt.event.ActionEvent evt) {

//        CardList cardList = new CardList();
//        cardList.setVisible(true);
//        this.setVisible(false);
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {

        SelectRooms rooms = new SelectRooms();
        rooms.setVisible(true);
        this.setVisible(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        Logger log = Logger.getLogger(AdminRoom.class);
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
                new AdminRoom().setVisible(true);
            }
        });
    }
}
