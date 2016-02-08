/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import team5.datamodel.transmissions.FileHandler;
import team5.datamodel.actions.WorkUser;
import org.apache.log4j.Logger;
import team5.client.actions.DataExchanger;

/**
 *
 * @author chanta
 */
public class RoomSelectionFrame extends JFrame {

    private DataExchanger dataE;
    private Logger log = Logger.getLogger(RoomSelectionFrame.class);
    private javax.swing.JButton startButton;
    private javax.swing.JButton adminRoomButton;
    private javax.swing.JComboBox jComboBox;
    private javax.swing.JLabel selectLabel;

    /**
     * Creates new form SelectRooms
     *
     * @param in
     * @param out
     */
    public RoomSelectionFrame(DataExchanger dataE) {
        this.dataE = dataE;
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
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Room 1", "Room 2", "Room 3", "Room 4"}));
        add(jComboBox);
        jComboBox.setBounds(150, 100, 100, 30);

        startButton = new JButton();
        startButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        startButton.setText("Start!");
        add(startButton);
        startButton.setBounds(150, 210, 100, 30);
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        adminRoomButton = new JButton();
        adminRoomButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        adminRoomButton.setText("Admin room");
        add(adminRoomButton);
        adminRoomButton.setBounds(280, 10, 110, 30);
        adminRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminRoomButtonActionPerformed(evt);
            }
        });

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
                    WorkUser workUser = WorkUser.getWork();
                    FileHandler workWithFiles = new FileHandler();
                    //sd.serializableData("serializableData_WorkUser.bin", wu);
                    workWithFiles.marshalData("marshalData_WorkUser.xml", workUser);
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } //catch (IOException ex) { //catch (IOException ex) {
                //    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
                //} 
                catch (JAXBException ex) {
                    log.debug(ex.getMessage());
                } finally {
                    event.getWindow().setVisible(false);
                    System.exit(0);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
        this.setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void adminRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {
        AdminRoom adminRoom = new AdminRoom(dataE);
        adminRoom.setVisible(true);
        //MemberList list = new MemberList();
        //list.setVisible(true);
        this.setVisible(false);
    }

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            dataE.write("Select");
            dataE.write(jComboBox.getSelectedItem().toString());
            String comand = dataE.readString();
            switch (comand) {
                case "Wait": {
                    JOptionPane.showConfirmDialog(null, "Wait for other players", "Waiting...", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "Full":
                    JOptionPane.showConfirmDialog(null, "This room is full. Select other room", "Oops", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
            boolean f = dataE.readBool();
            if (f) {
                GameFrame gameFrame = new GameFrame(dataE);
                gameFrame.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showConfirmDialog(null, "You could't enter this room. Try again", "Oops", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            log.debug(ex.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//     Logger log = Logger.getLogger(SelectRooms.class);
//     try {
//     for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//     if ("Nimbus".equals(info.getName())) {
//     javax.swing.UIManager.setLookAndFeel(info.getClassName());
//     break;
//     }
//     }
//     } catch (ClassNotFoundException ex) {
//     log.debug(ex.getMessage());
//     } catch (InstantiationException ex) {
//     log.debug(ex.getMessage());
//     } catch (IllegalAccessException ex) {
//     log.debug(ex.getMessage());
//     } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//     log.debug(ex.getMessage());
//     }
//     //</editor-fold>
//
//     /* Create and display the form */
//     java.awt.EventQueue.invokeLater(new Runnable() {
//     public void run() {
//     new SelectRooms().setVisible(true);
//     }
//     });
//    }
}
