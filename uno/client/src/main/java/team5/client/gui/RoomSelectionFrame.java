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
import java.util.TimerTask;
import org.apache.log4j.Logger;
import team5.client.actions.DataExchanger;
import team5.datamodel.transmissions.Message;
import team5.datamodel.transmissions.MessageHandler;

/**
 *
 * @author chanta
 */
public class RoomSelectionFrame extends JFrame {

    private Logger logger = Logger.getLogger(RoomSelectionFrame.class);
    private javax.swing.JButton startButton;
    private javax.swing.JButton adminRoomButton;
    private javax.swing.JComboBox jComboBox;
    private javax.swing.JLabel selectLabel;
    private MessageHandler messageHandler;
    private JLabel descriptionLabel;

    /**
     * Creates new form SelectRooms
     *
     * @param in
     * @param out
     */
//    public RoomSelectionFrame(DataExchanger dataE) {
//        this.dataE = dataE;
//        initComponents();
//    }
    public RoomSelectionFrame(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
        Message message = new Message("Rooms");
        boolean[] rooms = new boolean[4];
        try {
            messageHandler.sendMessage(message);
            for (int i = 0; i < 4; i++) {
                message = messageHandler.receiveMessage();
                rooms[i] = message.getConfirmation();
            }
        } catch (JAXBException ex) {
            logger.debug(ex.getMessage());
        } catch (IOException ex) {
            logger.debug(ex.getMessage());
        }
        initStartFrame();
        initComponents(rooms);
        initCloseOperation();
    }

    private void initStartFrame() {

        setPreferredSize(new Dimension(400, 300));
        setLayout(null);
        this.setResizable(false);
    }

    private void initComponents(boolean[] rooms) {

        selectLabel = new JLabel();
        selectLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        selectLabel.setText("Select Rooms");
        add(selectLabel);
        selectLabel.setBounds(120, 38, 200, 60);

        jComboBox = new JComboBox();
        jComboBox.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        String[] s = new String[4];
        int j = 0;
        for (int i = 0; i < 4; i++) {
            if(rooms[i]==false){
                s[j] = "Room "+(i+1);
                j++;
            }
        }
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel(s));//new String[]{"Room 1", "Room 2", "Room 3", "Room 4"}));
        add(jComboBox);
        jComboBox.setBounds(150, 100, 100, 30);

        startButton = new JButton();
        startButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        startButton.setText("Start!");
        add(startButton);
        startButton.setBounds(150, 210, 100, 30);
        startButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
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
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminRoomButtonActionPerformed(evt);
            }
        });

        descriptionLabel = new JLabel();
        descriptionLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        //descriptionLabel.setText("Waiting for the other players in 30 seconds...");
        descriptionLabel.setText("");
        add(descriptionLabel);
        descriptionLabel.setBounds(50, 140, 320, 30);
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
                } //catch (IOException ex) { //catch (IOException ex) {
                //    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
                //} 
                catch (JAXBException ex) {
                    logger.debug(ex.getMessage());
                     */
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
        AdminRoom adminRoom = new AdminRoom(messageHandler);
        adminRoom.setVisible(true);
        //MemberList list = new MemberList();
        //list.setVisible(true);
        this.dispose();
    }

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            descriptionLabel.setText("Waiting for the other players (~30 seconds)...");
            Message message = new Message("Select");
            message.setChoice(jComboBox.getSelectedItem().toString());
            try {
                messageHandler.sendMessage(message);
            } catch (JAXBException ex) {
                logger.debug(ex.getMessage());
            }
            String command = "";
            try {
                command = messageHandler.receiveMessage().getCommand();
            } catch (JAXBException ex) {
                logger.debug(ex.getMessage());
            }
            switch (command) {
                case "Wait": {
                    JOptionPane.showConfirmDialog(null, "Wait for other players",
                            "Waiting...", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "Full":
                    JOptionPane.showConfirmDialog(null, "This room is full. Select other room",
                            "Oops", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    break;
            }

//            descriptionLabel.setText("Waiting for the other players in 30 seconds...");
//
//            timerLabel = new TimerLabel(new java.util.Timer());
//            timerLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
//            add(timerLabel);
//            timerLabel.setBounds(175, 170, 50, 30);
            boolean f = false;
            try {
                f = messageHandler.receiveMessage().getConfirmation();
            } catch (JAXBException ex) {
                logger.debug(ex.getMessage());
            }
            if (f) {
                GameFrame gameFrame = new GameFrame(messageHandler);
                gameFrame.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showConfirmDialog(null, "You could't enter this room. Try again", "Oops", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            logger.debug(ex.getMessage());
        }

    }

    public static class TimerLabel extends JLabel {

        public java.util.Timer timer;

        public TimerLabel(java.util.Timer timer) {
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
        }

        private final TimerTask timerTask = new TimerTask() {
            private volatile int time = 30;

            private Runnable refresher = new Runnable() {
                @Override
                public void run() {
                    int t = time;
                    TimerLabel.this.setText(String.format("%02d:%02d", t / 60, t % 60));
                }
            };

            @Override
            public void run() {
                time--;
                SwingUtilities.invokeLater(refresher);
                if (time == -1) {
                    time = 0;
                }
            }
        };
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
