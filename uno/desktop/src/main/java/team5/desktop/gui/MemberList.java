/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import team5.desktop.user.action.UserTableModel;
import team5.desktop.user.action.WorkUser;

/**
 *
 * @author chanta
 */
public class MemberList extends javax.swing.JFrame {

    private JButton backButton;
    private JButton deleteButton;
    private JButton addButton;
    
    private JTextField tfname;
    private JTextField tfsurname;
    private JTextField tfcity;
    private JTextField tfcountry;
    private JTextField tfemail;
    private JTextField tflogin;
    private JTextField tfpassword;
    
    private JLabel labname;
    private JLabel labsurname;
    private JLabel labcountry;
    private JLabel labcity;
    private JLabel labemail;
    private JLabel lablogin;
    private JLabel labpassword;
    
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JPanel panel;
    
    
    public MemberList() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
                  
    private void initComponents() {

        setLayout(null);
        setBounds(200, 10, 620, 860);
        setTitle("Member list");
        
        panel = new JPanel();
        panel.setBorder(new TitledBorder("Add/delete users"));
        panel.setBounds(10,440,580,360);
        panel.setLayout(null);
        
        jScrollPane1 = new JScrollPane(jTable1);
        jScrollPane1.setBounds(10,10,580,420);
        //Таблица и модель
        UserTableModel model = new UserTableModel();
        jTable1 = new JTable(model);
        model.addTableModelListener(jTable1);
        jTable1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        //jTable1.setSize(new java.awt.Dimension(500, 400));
        jScrollPane1.setViewportView(jTable1);
        
        
        backButton = new javax.swing.JButton();    
        backButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        backButton.setText("Back");
        panel.add(backButton);
        backButton.setBounds(250, 300 ,80 ,30);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        addButton = new JButton();
        addButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        addButton.setText("Add User");
        panel.add(addButton);
        addButton.setBounds(20,220,130,30);
        addButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                addButtonActionPerfomed(e);
            }
        });
        
        
        deleteButton = new JButton();
        deleteButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        deleteButton.setText("Delete User");
        panel.add(deleteButton);
        deleteButton.setBounds(180, 220, 130, 30);
        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                deleteButtonActionPerfomed(e);
            }
        });
        
        //TextFields
        tfname = new JTextField();
        tfname.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        panel.add(tfname);
        tfname.setBounds(120, 30, 130, 30);
        
        
        tfsurname = new JTextField();
        tfsurname.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        panel.add(tfsurname);
        tfsurname.setBounds(120, 70, 130, 30);
        
        tfcity = new JTextField();
        tfcity.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        panel.add(tfcity);
        tfcity.setBounds(120, 110, 130, 30);
       
        tfcountry = new JTextField();
        tfcountry.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        panel.add(tfcountry);
        tfcountry.setBounds(120, 150, 130, 30);
        
        
        tfemail = new JTextField();
        tfemail.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        panel.add(tfemail);
        tfemail.setBounds(430, 30, 130, 30);
        
        tflogin = new JTextField();
        tflogin.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        panel.add(tflogin);
        tflogin.setBounds(430, 70, 130, 30);
        
        tfpassword = new JTextField();
        tfpassword.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        panel.add(tfpassword);
        tfpassword.setBounds(430, 110, 130, 30);
        
        
        //Labels
        
        labname = new JLabel("Name");
        labname.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        labname.setBounds(20, 30, 90, 30);
        panel.add(labname);
        
        labsurname = new JLabel("Surname");
        labsurname.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        labsurname.setBounds(20, 70, 90, 30);
        panel.add(labsurname);
        
        labcountry = new JLabel("Country");
        labcountry.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        labcountry.setBounds(20, 110, 90, 30);
        panel.add(labcountry);
        
        labcity = new JLabel("City");
        labcity.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        labcity.setBounds(20, 150, 90, 30);
        panel.add(labcity);
        
        labemail = new JLabel("Email");
        labemail.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        labemail.setBounds(330, 30, 90, 30);
        panel.add(labemail);
        
        lablogin = new JLabel("Login");
        lablogin.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        lablogin.setBounds(330, 70, 90, 30);
        panel.add(lablogin);
        
        labpassword = new JLabel("Password");
        labpassword.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        labpassword.setBounds(330, 110, 90, 30);
        panel.add(labpassword);
        

        //add(jTable1);
        add(panel);
        add(jScrollPane1);
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }                     

    private void deleteButtonActionPerfomed(ActionEvent evt)
    {
        if((jTable1.getSelectedRow()>=0)&&(jTable1.getSelectedRow()<WorkUser.getWork().getArrOfUsers().size()))
        {
            WorkUser.getWork().deleteUser(WorkUser.getWork().getArrOfUsers().get(jTable1.getSelectedRow()).getServiceInfo().getLogin());
        }
        jTable1.revalidate();
        jTable1.repaint();
    }
    private void addButtonActionPerfomed(ActionEvent evt)
    {
        WorkUser.getWork().addUser(tfname.getText(), tfsurname.getText(), tfcountry.getText(), tfcity.getText(), tflogin.getText(),
                tfpassword.getText(), tfemail.getText(), LocalDate.MIN);
        tfname.setText("");
        tfsurname.setText("");
        tfcountry.setText("");
        tfcity.setText("");
        tflogin.setText("");
        tfpassword.setText("");
        tfemail.setText("");
        jTable1.revalidate();
        jTable1.repaint();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        SelectRooms rooms =new SelectRooms();
        rooms.setVisible(true);
        this.setVisible(false);
    }                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MemberList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemberList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemberList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemberList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemberList().setVisible(true);
            }
        });
    }

           
}
