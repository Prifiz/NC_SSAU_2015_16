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
import java.time.LocalDate;
import javax.swing.*;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import team5.client.actions.DataExchanger;
import team5.datamodel.transmissions.Message;
import team5.datamodel.transmissions.MessageHandler;
import team5.datamodel.user.PrivateInformation;
import team5.datamodel.user.ServiceInfo;
import team5.datamodel.user.User;
import team5.datamodel.user.adress.Address;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kate_Romanoff
 */
public class RegistrationFrame extends JFrame {

    private DataExchanger dataE;
    private Logger logger = Logger.getLogger(RegistrationFrame.class);
    private JButton okButton;
    private JButton canselButton;
    private JLabel emailLabel;
    private JLabel nameLabel;
    private JLabel countryLabel;
    private JLabel сityLabel;
    private JLabel surnameLabel;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel registrationLabel;
    private JTextField loginTextField;
    private JTextField passwordTextField;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField countryTextField;
    private JTextField cityTextField;
    private JTextField emailTextField;
    private JLabel jMessage;
//    private JLabel bDayLabel;
//    private JTextField bDayTextField; TODO временно убрал поле с датой, непонятно что она должна в себе содержать
    private MessageHandler messageHandler;

    /**
     * Creates new form SecondFrame
     */
//    public RegistrationFrame(DataExchanger dataE) {
//        this.dataE = dataE;
//        initComponents();
//    }
    public RegistrationFrame(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
        initStartFrame();
        initComponents();
        initCloseOperation();
    }

    private void initStartFrame() {
//        bDayLabel = new JLabel();
//        bDayTextField = new JTextField();

        setPreferredSize(new Dimension(400, 550));
        setLayout(null);
        this.setResizable(false);

    }

    private void initComponents() {

        registrationLabel = new JLabel();
        registrationLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        registrationLabel.setText("Registration");
        add(registrationLabel);
        registrationLabel.setBounds(140, 20, 200, 60);

        loginLabel = new JLabel();
        loginLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        loginLabel.setText("Login");
        add(loginLabel);
        loginLabel.setBounds(40, 100, 100, 30);

        passwordLabel = new JLabel();
        passwordLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        passwordLabel.setText("Password");
        add(passwordLabel);
        passwordLabel.setBounds(40, 140, 100, 30);

        emailLabel = new JLabel();
        emailLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        emailLabel.setText("Email");
        add(emailLabel);
        emailLabel.setBounds(40, 180, 100, 30);

        nameLabel = new JLabel();
        nameLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        nameLabel.setText("Name");
        add(nameLabel);
        nameLabel.setBounds(40, 220, 100, 30);

        surnameLabel = new JLabel();
        surnameLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        surnameLabel.setText("Surname");
        add(surnameLabel);
        surnameLabel.setBounds(40, 260, 100, 30);

        countryLabel = new JLabel();
        countryLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        countryLabel.setText("Country");
        add(countryLabel);
        countryLabel.setBounds(40, 300, 100, 30);

        сityLabel = new JLabel();
        сityLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        сityLabel.setText("City");
        add(сityLabel);
        сityLabel.setBounds(40, 340, 100, 30);

//         bDayLabel = new JLabel();
//        bDayLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
//        bDayLabel.setText("Birthday");
//        add(bDayLabel);
//        bDayLabel.setBounds(40, 380, 100, 30);
        jMessage = new JLabel();
        jMessage.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        add(jMessage);
        jMessage.setBounds(40, 420, 300, 30);
        jMessage.setText("");

        loginTextField = new JTextField();
        loginTextField.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        add(loginTextField);
        loginTextField.setBounds(130, 100, 150, 30);

        passwordTextField = new JTextField();
        passwordTextField.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        add(passwordTextField);
        passwordTextField.setBounds(130, 140, 150, 30);

        emailTextField = new JTextField();
        emailTextField.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        add(emailTextField);
        emailTextField.setBounds(130, 180, 150, 30);

        nameTextField = new JTextField();
        nameTextField.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        add(nameTextField);
        nameTextField.setBounds(130, 220, 150, 30);

        surnameTextField = new JTextField();
        surnameTextField.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        add(surnameTextField);
        surnameTextField.setBounds(130, 260, 150, 30);

        countryTextField = new JTextField();
        countryTextField.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        add(countryTextField);
        countryTextField.setBounds(130, 300, 150, 30);

        cityTextField = new JTextField();
        cityTextField.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        add(cityTextField);
        cityTextField.setBounds(130, 340, 150, 30);

//        bDayTextField = new JTextField();
//        bDayTextField.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
//        add(bDayTextField);
//        bDayTextField.setBounds(130, 380, 150, 30);
        okButton = new JButton();
        okButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        okButton.setText("OK");
        add(okButton);
        okButton.setBounds(70, 460, 100, 30);
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        canselButton = new JButton();
        canselButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        canselButton.setText("Cansel");
        add(canselButton);
        canselButton.setBounds(230, 460, 100, 30);
        canselButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canselButtonActionPerformed(evt);
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
                } //                catch (IOException ex) { //                catch (IOException ex) {
                //                    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
                //                } 
                catch (JAXBException ex) {
                    logger.debug(ex.getMessage());
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
    }// </editor-fold>                        

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
        boolean f = false;
        Message message = new Message("Registration");
        User usr = new User();
        usr.setServiceInfo(new ServiceInfo(loginTextField.getText(), passwordTextField.getText(), emailTextField.getText()));
        usr.setAddress(new Address(countryTextField.getText(), cityTextField.getText()));
        usr.setPrivateInformation(new PrivateInformation(nameTextField.getText(), surnameTextField.getText(), LocalDate.now()));
        message.setUser(usr);
        Message serverResponse = new Message();//            dataE.write("Registration");
        //            dataE.write(nameTextField.getText());
        //            dataE.write(surnameTextField.getText());
        //            dataE.write(countryTextField.getText());
        //            dataE.write(cityTextField.getText());
        //            dataE.write(loginTextField.getText());
        //            dataE.write(passwordTextField.getText());
        //            dataE.write(emailTextField.getText());
        //            dataE.write("12.05.2010");
        try {
            messageHandler.sendMessage(message);
        } catch (JAXBException ex) {
            logger.debug(ex.getMessage());
        } catch (IOException ex) {
            logger.debug(ex.getMessage());
        }
        try {
            serverResponse = messageHandler.receiveMessage();
            f = serverResponse.getConfirmation();;
        } catch (JAXBException ex) {
            logger.debug(ex.getMessage());
        } catch (IOException ex) {
            logger.debug(ex.getMessage());
        }
        //f = dataE.readBool();
        //Registration r = new Registration();
        //r.registrationUser(this.nameTextField.getText(), this.surnameTextField.getText(), 
        //this.countryTextField.getText(), this.sityTextField.getText(), this.loginTextField.getText(), this.passwordTextField.getText(), this.emailTextField.getText(), "12.05.2010")F
        if (f) {
            LogInFrame logIn = new LogInFrame(messageHandler);
            logIn.setVisible(true);
            this.dispose();
        } else {
            //String str = dataE.readString();
            String str = serverResponse.getChoice();//TODO внес поправки, используя XML механизм передачи. Не проверял на падения
            switch (str) {
                case "Field":
                    jMessage.setText("The field is empty. Fill in all the fields.");
                    break;
                case "User":
                    jMessage.setText("User already created");
                    break;
            }
        }

    }

    private void canselButtonActionPerformed(java.awt.event.ActionEvent evt) {
        LogInFrame logIn = new LogInFrame(messageHandler);
        logIn.setVisible(true);
        this.setVisible(false);
    }

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
     Logger log = Logger.getLogger(SecondFrame.class);
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
     new SecondFrame().setVisible(true);
     }
     });
     }*/
}
