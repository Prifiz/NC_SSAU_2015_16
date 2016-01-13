/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import org.apache.log4j.Logger;
import team5.client.actions.UserTableModel;
import team5.client.exceptions.UserNotFoundException;
import team5.client.searches.*;
import team5.client.user.User;

/**
 *
 * @author chanta
 */
public class Search extends javax.swing.JFrame {

    private InputStream in;
    private OutputStream out;
    private String searchRequest;
    private JButton backButton;
    private JButton okButton;
    private JTable table;
    private JLabel jMessage;

    private JTextField tfsearch;
//    private JTextField[] fields;
    UserTableModel model;

    private JPanel panel;

    public Search(InputStream in,OutputStream out) {
        this.in = in;
        this.out = out;
        initComponents();
    }

    public Search(JTable table, UserTableModel model) {
        this.table = table;
        //this.fields=fields;
        this.model = model;
        initComponents();
    }

    public String getSearchRequest() {
        if (searchRequest != null) {
            return searchRequest;
        } else {
            return null;
        }
    }

    public void setSearchRequest(String searchRequest) {
        this.searchRequest = searchRequest;
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        setBounds(200, 10, 400, 250);
        this.setLocationRelativeTo(null);
        setLayout(null);
        setTitle("Search");

        panel = new JPanel();
        panel.setBorder(new TitledBorder("Search"));
        panel.setBounds(10, 10, 360, 170);
        panel.setLayout(null);

        jMessage = new javax.swing.JLabel();
        jMessage.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        this.add(jMessage);
        jMessage.setBounds(40, 90, 150, 30);
        jMessage.setText("");

        backButton = new javax.swing.JButton();
        backButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        backButton.setText("Back");
        panel.add(backButton);
        backButton.setBounds(270, 120, 80, 30);//310 300 80 30
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        okButton = new JButton();
        okButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        okButton.setText("OK");
        panel.add(okButton);
        okButton.setBounds(140, 120, 80, 30);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okButtonActionPerfomed(e);
            }
        });

        //TextFields
        tfsearch = new JTextField();
        tfsearch.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        panel.add(tfsearch);
        tfsearch.setBounds(30, 30, 300, 30);

//        
        add(panel);
//        

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
    }

//    
    private void okButtonActionPerfomed(ActionEvent evt) {
        searchRequest = tfsearch.getText();
        try {

            model.setUser((ArrayList<User>) UserSearch.regularSearch(searchRequest));
            table.revalidate();
            table.repaint();
            this.setVisible(false);

        } catch (UserNotFoundException e) {
            jMessage.setText("Nothing found.");
        }
        //   finally {
        //     this.setVisible(false);
        // }
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {

//        MemberList memberList =new MemberList();
//        memberList.setVisible(true);
        this.setVisible(false);
    }
//    

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {

        Logger log = Logger.getLogger(Search.class);
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
                new MemberList().setVisible(true);
            }
        });
    }*/

}
