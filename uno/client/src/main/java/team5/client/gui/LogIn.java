/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import team5.client.actions.WorkWithFiles;
import team5.client.actions.SignIn;
import team5.client.actions.WorkUser;
import javax.swing.*;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;

/**
 *
 * @author андрей
 */
public class LogIn extends JFrame {

    private InputStream in;
    private OutputStream out;
    private Logger log = Logger.getLogger(LogIn.class);
    private javax.swing.JButton signButton;
    private javax.swing.JButton registrationButton;
    private javax.swing.JLabel signLabel;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jTextField;
    private javax.swing.JLabel jMessage;
    
    /**
     * Creates new form LogIn
     */
    public LogIn(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
        initComponents();
    }

    
    @SuppressWarnings("unchecked")                      
    private void initComponents() {

        
        signLabel = new JLabel();
        jPasswordField = new JPasswordField();
        jTextField = new JTextField();
        signButton = new JButton();
        registrationButton = new JButton();
        loginLabel = new JLabel();
        passwordLabel = new JLabel();
        jMessage = new JLabel();

        setPreferredSize(new Dimension(400, 300));
        setLayout(null);
        this.setResizable(false);

        signLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        signLabel.setText("Sign In");
        add(signLabel);
        signLabel.setBounds(150, 20, 200, 60);

        loginLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        loginLabel.setText("Login");
        add(loginLabel);
        loginLabel.setBounds(40, 100, 100, 30);

        passwordLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        passwordLabel.setText("Password");
        add(passwordLabel);
        passwordLabel.setBounds(40, 140, 100, 30);
        
        jMessage.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        this.add(jMessage);
        jMessage.setBounds(70, 180, 300, 30);
        jMessage.setText("");

        jTextField.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        add(jTextField);
        jTextField.setBounds(125, 100, 160, 30);
        
        add(jPasswordField);
        jPasswordField.setBounds(125, 140, 160, 30);
        

        signButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        signButton.setText("Sign In");
        add(signButton);
        signButton.setBounds(220, 220, 120, 30);
        signButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        registrationButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        registrationButton.setText("Registration");
        add(registrationButton);
        registrationButton.setBounds(60, 220, 120, 30);
        registrationButton.addActionListener(new java.awt.event.ActionListener() {
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
//                catch (IOException ex) {
//                    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
//                } 
                catch (JAXBException ex) {
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
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        DataInputStream din = new DataInputStream(in);
        DataOutputStream dout = new DataOutputStream(out);     
        boolean f = false;
        try{
        dout.writeUTF("Login");
        dout.writeUTF(jTextField.getText());
        dout.writeUTF(String.valueOf(jPasswordField.getPassword()));
        dout.flush();
        f = din.readBoolean();
        }
        catch(IOException e)
        {
            log.debug(e.getMessage());
        }
        //SignIn sign = new SignIn();
        // WorkUser wu = WorkUser.getWork();
        //sign.sign(this.jTextField.getText(), this.jPasswordField.getPassword())
        if (f) {
            SelectRooms rooms = new SelectRooms();
            rooms.setVisible(true);
            this.setVisible(false);
        } else {
            jMessage.setText("Invalid login/password. Try it again.");
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        RegistrationFrame secondFrame = new RegistrationFrame(in,out);
        secondFrame.setVisible(true);
        this.setVisible(false);
    }

    /**
     * @param args the command line arguments
     */
   /* public static void main(String args[]) {
        Logger log = Logger.getLogger(LogIn.class);
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

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIn(new InputStream(), new OutputStream()).setVisible(true);
            }
        });*/
    }

                     
   
                    

