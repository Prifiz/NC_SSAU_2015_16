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
public class FinishFrame extends JFrame {

    private Logger log = Logger.getLogger(FinishFrame.class);
    private JButton playAgainButton;
    private JButton backButton;
    private JLabel resultLabel;

    public FinishFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setPreferredSize(new Dimension(400, 300));
        setLayout(null);
        this.setResizable(false);
        setTitle("Finish game");

        playAgainButton = new JButton();
        playAgainButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        playAgainButton.setText("Play again");
        add(playAgainButton);
        playAgainButton.setBounds(120, 150, 150, 30);//310 300 80 30
        playAgainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playAgainButtonActionPerformed(evt);
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
        
        resultLabel = new JLabel();
       resultLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        resultLabel.setText("YOU WIN");
        add(resultLabel);
        resultLabel.setBounds(135, 100, 150, 30);

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
                }
//                catch (IOException ex) {
//                    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
//                }
                catch (JAXBException ex) {
                    log.debug(ex.getMessage());
                }  finally {
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

    private void playAgainButtonActionPerformed(java.awt.event.ActionEvent evt) {

        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);
        this.setVisible(false);
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

        Logger log = Logger.getLogger(FinishFrame.class);
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
                new FinishFrame().setVisible(true);
            }
        });
    }
}
