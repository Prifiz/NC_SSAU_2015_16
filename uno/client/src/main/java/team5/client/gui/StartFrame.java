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
import team5.client.net.ClientSocket;

/**
 *
 * @author chanta
 */
public class StartFrame extends JFrame {

    private Logger logger = Logger.getLogger(StartFrame.class);
    private javax.swing.JButton ruleButton;
    private javax.swing.JButton enterButton;
    private javax.swing.JLabel unoLabel;
    private javax.swing.JLabel descriptionLabel;

    /**
     * Creates new form StartFrame
     */
    public StartFrame() {
        initStartForm();
        initComponents();
        initCloseOperation();
    }

    @SuppressWarnings("unchecked")

    private void initStartForm() {

        setPreferredSize(new Dimension(400, 300));
        setLayout(null);
        this.setBounds(200, 10, 710, 790);
        this.setSize(250, 250);

        this.setResizable(false);
    }

    private void initComponents() {

        unoLabel = new JLabel();
        unoLabel.setFont(new java.awt.Font("Comic Sans MS", 3, 36)); // NOI18N
        unoLabel.setText("UNO");
        add(unoLabel);
        unoLabel.setBounds(150, 38, 100, 60);

        descriptionLabel = new JLabel();
        descriptionLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        descriptionLabel.setText("Uno is an American card game");
        add(descriptionLabel);
        descriptionLabel.setBounds(110, 90, 200, 30);

        ruleButton = new JButton();
        ruleButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        ruleButton.setText("View rules");
        add(ruleButton);
        ruleButton.setBounds(145, 150, 100, 30);
        ruleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruleButtonActionPerformed(evt);
            }
        });

        enterButton = new JButton();
        enterButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        enterButton.setText("Еnter game");
        add(enterButton);
        enterButton.setBounds(145, 190, 100, 30);
        enterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterButtonActionPerformed(evt);
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
                    workWithFiles.marshalData("marshalData_WorkUser.xml", workUser);
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } catch (JAXBException ex) {

                */} finally {
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
    }

    private void ruleButtonActionPerformed(java.awt.event.ActionEvent evt) {
        RulesFrame rule = RulesFrame.getRulesFrame();
        rule.setVisible(true);
        //this.setVisible(false);
    }

    private void enterButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            ClientSocket clientSocket = new ClientSocket();
            clientSocket.connection();

            //DataExchanger dataE = new DataExchanger(clientSocket.getInputStream(), clientSocket.getOutputStream());//временно остаивил
            LogInFrame loginFrame = new LogInFrame(clientSocket.getMessageHandler());
            loginFrame.setVisible(true);
            this.dispose();
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "You didn't connect to server. Please try agane", "Oops", JOptionPane.CLOSED_OPTION);
            logger.debug(e.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Logger log = Logger.getLogger(StartFrame.class);
        try {
            WorkUser workUser = WorkUser.getWork();
            FileHandler workWithFiles = FileHandler.getFileHandler();
            workUser.addWorkUser(workWithFiles.unmarshalData("marshalData_WorkUser.xml"));

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
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (JAXBException ex) {

            log.debug(ex.getMessage());
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartFrame().setVisible(true);
            }
        });

    }

}
