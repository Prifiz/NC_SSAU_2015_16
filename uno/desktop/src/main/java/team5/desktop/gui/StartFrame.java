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

import team5.desktop.actions.WorkWithFiles;
import team5.desktop.actions.WorkUser;
import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import team5.desktop.actions.WorkWithFiles;
import team5.desktop.actions.WorkUser;
import javax.swing.*;
import org.apache.log4j.Logger;

/**
 *
 * @author chanta
 */
public class StartFrame extends JFrame {

    private Logger log = Logger.getLogger(StartFrame.class);
    private javax.swing.JButton ruleButton;
    private javax.swing.JButton enterButton;
    private javax.swing.JLabel unoLabel;
    private javax.swing.JLabel descriptionLabel;

    /**
     * Creates new form StartFrame
     */
    public StartFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {
        //this.setLocationRelativeTo(null);

        setPreferredSize(new Dimension(400, 300));
        setLayout(null);
        this.setBounds(200, 10, 710, 790);
        this.setSize(250, 250);

        this.setResizable(false);
        //this.setLocationRelativeTo(null);

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
                    //workWithFiles.serializableData("serializableData_WorkUser.bin", workUser);
                    workWithFiles.marshalData("marshalData_WorkUser.xml", workUser);
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } //catch (IOException ex) {
                //    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
                //} 
                catch (JAXBException ex) {

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

    private void ruleButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Rules rule = new Rules();
        rule.setVisible(true);
        //this.setVisible(false);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        int serverport = 8080;
        String address = "127.0.0.1";
        InputStream in = null;
        OutputStream out = null;
        try {
            InetAddress ipaddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipaddress, serverport);
            log.info("Connect to server");
            in = socket.getInputStream();
            out = socket.getOutputStream();
        }
        catch(IOException e)
        {
            log.debug(e.getMessage());
        }
        LogIn log = new LogIn(in,out);
        log.setVisible(true);
        this.setVisible(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Logger log = Logger.getLogger(StartFrame.class);
        try {
            WorkUser workUser = WorkUser.getWork();
            WorkWithFiles workWithFiles = new WorkWithFiles();
            //wu.addWorkUser(sd.deserializableData("serializableData_WorkUser.bin"));
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
        } //        catch (IOException ex) {
        //            Logger.getLogger(StartFrame.class.getName()).log(Level.SEVERE, null, ex);
        //        }
        catch (JAXBException ex) {

            log.debug(ex.getMessage());
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartFrame().setVisible(true);
            }
        });

    }

}
