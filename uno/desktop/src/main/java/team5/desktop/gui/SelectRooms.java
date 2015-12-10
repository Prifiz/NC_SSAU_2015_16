/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
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
public class SelectRooms extends JFrame {

     private javax.swing.JButton startButton;
    private javax.swing.JButton adminRoomButton;
    private javax.swing.JComboBox jComboBox;
    private javax.swing.JLabel selectLabel;  
    
    /**
     * Creates new form SelectRooms
     */
    public SelectRooms() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")                       
    private void initComponents() {

       

        setPreferredSize(new Dimension(400, 300));
        setLayout(null);
        this.setResizable(false);

        selectLabel = new JLabel();
        selectLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        selectLabel.setText("Select Rooms");
        add(selectLabel);
        selectLabel.setBounds(120, 38, 200, 60);

        jComboBox = new JComboBox();
        jComboBox.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Room 1"}));
        add(jComboBox);
        jComboBox.setBounds(150, 130, 100, 30);

        startButton = new JButton();
        startButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        startButton.setText("Start!");
        add(startButton);
        startButton.setBounds(150, 190, 100, 30);
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        adminRoomButton = new JButton();
        adminRoomButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        adminRoomButton.setText("Admin room");
        add(adminRoomButton);
        adminRoomButton.setBounds(280, 10, 110, 30);
        adminRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                //catch (IOException ex) {
                //    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
                //} 
                catch (JAXBException ex) {
                    Logger.getLogger(SelectRooms.class.getName()).log(Level.SEVERE, null, ex);
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
    }// </editor-fold>                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        AdminRoom adminRoom = new AdminRoom();
        adminRoom.setVisible(true);
        //MemberList list = new MemberList();
        //list.setVisible(true);
        this.setVisible(false);
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);
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
            java.util.logging.Logger.getLogger(SelectRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelectRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelectRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelectRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SelectRooms().setVisible(true);
            }
        });
    }

                       
                  
}
