/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import team5.library.transmissions.FileHandler;
import team5.library.actions.WorkUser;
import javax.swing.*;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import team5.client.actions.DataExchanger;

/**
 *
 * @author андрей
 */
public class LogInFrame extends JFrame {

    private DataExchanger dataE;
    private Logger log = Logger.getLogger(LogInFrame.class);
    private javax.swing.JButton signButton;
    private javax.swing.JButton registrationButton;
    private javax.swing.JLabel signLabel;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jLoginField;
    private javax.swing.JLabel jMessage;

    /**
     * Creates new form LogIn
     */
    public LogInFrame(DataExchanger dataE) {
        this.dataE = dataE;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        signLabel = new JLabel();
        jPasswordField = new JPasswordField();
        jLoginField = new JTextField();
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

        jLoginField.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        add(jLoginField);
        jLoginField.setBounds(125, 100, 160, 30);

        add(jPasswordField);
        jPasswordField.setBounds(125, 140, 160, 30);

        signButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        signButton.setText("Sign In");
        add(signButton);
        signButton.setBounds(220, 220, 120, 30);
        signButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signButtonActionPerformed(evt);
            }
        });

        registrationButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        registrationButton.setText("Registration");
        add(registrationButton);
        registrationButton.setBounds(60, 220, 120, 30);
        registrationButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationButtonActionPerformed(evt);
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
                } //                catch (IOException ex) {
                //                    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
                //                } 
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

    private void signButtonActionPerformed(java.awt.event.ActionEvent evt) {
        boolean f = false;
        try {
//            Commands command=new Commands("Login");
//            User usr=new User();
//            usr.setServiceInfo(new ServiceInfo(jLoginField.getText(), String.valueOf(jPasswordField.getPassword()), "not"));
            dataE.write("Login");
            dataE.write(jLoginField.getText());
            dataE.write(String.valueOf(jPasswordField.getPassword()));
//        command.setUser(usr);
//        WorkWithFiles.marshalData(Streams.getOutputStream(), command);
            f = dataE.readBool();
        } catch (IOException e) {
            log.debug(e.getMessage());
        }
//        catch(JAXBException e){
//            log.debug(e.getMessage());
//        }
        //SignIn sign = new SignIn();
        // WorkUser wu = WorkUser.getWork();
        //sign.sign(this.jTextField.getText(), this.jPasswordField.getPassword())
        if (f) {
            RoomSelectionFrame rooms = new RoomSelectionFrame(dataE);
            rooms.setVisible(true);
            this.setVisible(false);
        } else {
            jMessage.setText("Invalid login/password. Try it again.");
        }
    }

    private void registrationButtonActionPerformed(java.awt.event.ActionEvent evt) {
        RegistrationFrame secondFrame = new RegistrationFrame(dataE);
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
