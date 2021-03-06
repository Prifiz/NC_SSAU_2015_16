/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.apache.log4j.Logger;
import team5.client.actions.DataExchanger;
import team5.datamodel.transmissions.MessageHandler;

/**
 *
 * @author chanta
 */
public class FinishFrame extends JFrame {

    private DataExchanger dataE;
    private Logger log = Logger.getLogger(FinishFrame.class);
    private JButton backButton;
    private JLabel resultLabel;
    private MessageHandler messageHandler;

    public FinishFrame(DataExchanger dataE) {
        this.dataE = dataE;
        initStartFrame();
        initComponents();
        initCloseOperation();
    }

    private void initStartFrame() {
        setPreferredSize(new Dimension(400, 300));
        setLayout(null);
        this.setResizable(false);
        setTitle("Finish game");

    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

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
                    //workWithFiles.serializableData("serializableData_WorkUser.bin", workUser);
                    workWithFiles.marshalData("marshalData_WorkUser.xml", workUser);
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } //                catch (IOException ex) { //                catch (IOException ex) {
                //                    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
                //                }
                catch (JAXBException ex) {
                    log.debug(ex.getMessage());
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

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {

        RoomSelectionFrame rooms = new RoomSelectionFrame(messageHandler);
        rooms.setVisible(true);
        this.dispose();
    }

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {

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
     }*/
}
