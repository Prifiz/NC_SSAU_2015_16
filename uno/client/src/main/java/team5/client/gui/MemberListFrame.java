/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import team5.client.actions.DataExchanger;
import team5.client.actions.UserTableModel;
import team5.datamodel.actions.WorkUser;
import team5.datamodel.exceptions.NotFoundException;
import team5.datamodel.searches.UserSearch;
import team5.datamodel.searches.Search;
import team5.datamodel.transmissions.Message;
import team5.datamodel.transmissions.MessageHandler;
import team5.datamodel.user.PrivateInformation;
import team5.datamodel.user.ServiceInfo;
import team5.datamodel.user.User;
import team5.datamodel.user.adress.Address;

/**
 *
 * @author chanta
 */
public class MemberListFrame extends javax.swing.JFrame {

    private DataExchanger dataE;
    private Logger logger = Logger.getLogger(MemberListFrame.class);
    private SearchFrameOfUser searchFrame;
    private JButton backButton;
    private JButton deleteButton;
    private JButton addButton;
    private JButton searchButton;
    private JButton cleanButton;

    private JTextField tfname;
    private JTextField tfsurname;
    private JTextField tfcity;
    private JTextField tfcountry;
    private JTextField tfemail;
    private JTextField tflogin;
    private JTextField tfpassword;
    private JTextField tfbday;

    private JLabel labname;
    private JLabel labsurname;
    private JLabel labcountry;
    private JLabel labcity;
    private JLabel labemail;
    private JLabel lablogin;
    private JLabel labpassword;
    private JLabel labbday;

    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JPanel panel;

    private UserTableModel model;
    private MessageHandler messageHandler;

    public MemberListFrame(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
        initStartFrame();
        initComponents();
        initCloseOperation();
    }

    public JTable getTable() {
        return jTable1;
    }

    public void setTable(JTable table) {
        jTable1 = table;
    }

    private void initStartFrame() {

        searchFrame = new SearchFrameOfUser(dataE);
        setPreferredSize(new Dimension(710, 790));
        setLayout(null);
        this.setResizable(false);
        setTitle("Member list");

    }

    private void initComponents() {

        panel = new JPanel();
        panel.setBorder(new TitledBorder("Add/delete users"));
        panel.setBounds(10, 440, 670, 280);
        panel.setLayout(null);

        jScrollPane1 = new JScrollPane(jTable1);
        jScrollPane1.setBounds(10, 10, 670, 420);
        //Таблица и модель
        model = new UserTableModel(messageHandler);
        jTable1 = new JTable(model);

        //сортировка  по столбцу
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        sorter.setSortable(7, false);
        sorter.setComparator(0, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });
        jTable1.setRowSorter(sorter);

        model.addTableModelListener(jTable1);
        jTable1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        //jTable1.setSize(new java.awt.Dimension(500, 400));
        jScrollPane1.setViewportView(jTable1);

        backButton = new javax.swing.JButton();
        backButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        backButton.setText("Back");
        panel.add(backButton);
        backButton.setBounds(500, 220, 150, 30);//310 300 80 30
        backButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        searchButton = new javax.swing.JButton();
        searchButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        searchButton.setText("Search");
        panel.add(searchButton);
        searchButton.setBounds(200, 190, 150, 30);//310 300 80 30
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        cleanButton = new javax.swing.JButton();
        cleanButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        cleanButton.setText("Clean");
        panel.add(cleanButton);
        cleanButton.setBounds(200, 230, 150, 30);//310 300 80 30
        cleanButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        addButton = new JButton();
        addButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        addButton.setText("Add User");
        panel.add(addButton);
        addButton.setBounds(20, 190, 150, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addButtonActionPerfomed(e);
            }
        });

        deleteButton = new JButton();
        deleteButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        deleteButton.setText("Delete User");
        panel.add(deleteButton);
        deleteButton.setBounds(20, 230, 150, 30);
        deleteButton.addActionListener(new ActionListener() {
            @Override
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
        tfemail.setBounds(490, 30, 130, 30);

        tflogin = new JTextField();
        tflogin.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        panel.add(tflogin);
        tflogin.setBounds(490, 70, 130, 30);

        tfpassword = new JTextField();
        tfpassword.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        panel.add(tfpassword);
        tfpassword.setBounds(490, 110, 130, 30);

        tfbday = new JTextField();
        tfbday.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        panel.add(tfbday);
        tfbday.setBounds(490, 150, 130, 30);

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
        labemail.setBounds(390, 30, 90, 30);
        panel.add(labemail);

        lablogin = new JLabel("Login");
        lablogin.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        lablogin.setBounds(390, 70, 90, 30);
        panel.add(lablogin);

        labpassword = new JLabel("Password");
        labpassword.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        labpassword.setBounds(390, 110, 90, 30);
        panel.add(labpassword);

        labbday = new JLabel("BirthDay");
        labbday.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        labbday.setBounds(390, 150, 90, 30);
        panel.add(labbday);

        //add(jTable1);
        add(panel);
        add(jScrollPane1);
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
                    messageHandler.sendMessage(new Message("Exit"));
                    /*WorkUser workUser = WorkUser.getWork();
                     FileHandler workWithFiles = new FileHandler();
                     //sd.serializableData("serializableData_WorkUser.bin", wu);
                     workWithFiles.marshalData("marshalData_WorkUser.xml", workUser);
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     } //                catch (IOException ex) { //                catch (IOException ex) {
                     //                    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
                     //                }*/
                } catch (JAXBException ex) {
                    logger.debug(ex.getMessage());
                } catch (IOException ex) {
                    logger.debug(ex.getMessage());
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
    }

    private void deleteButtonActionPerfomed(ActionEvent evt) {
        try {
            if ((jTable1.getSelectedRow() >= 0) && (jTable1.getSelectedRow() < WorkUser.getWork().getArrOfUsers().size())) {
                Message clientRequest = new Message("Delete");
                clientRequest.setValue(jTable1.getSelectedRow());
                messageHandler.sendMessage(clientRequest);
                WorkUser.getWork().deleteUser(WorkUser.getWork().getArrOfUsers().get(jTable1.getSelectedRow()).getServiceInfo().getLogin());
                if (searchFrame.getSearchRequest() != null) {
                    Search search = new UserSearch();
                    model.setArrayOfUsers((ArrayList<User>) search.searchByRegexp(searchFrame.getSearchRequest()));
                }
            }
        } catch (NotFoundException e) {
            logger.debug(e.getMessage());
        } catch (JAXBException ex) {
            logger.debug(ex.getMessage());
        } catch (IOException ex) {
            logger.debug(ex.getMessage());
        }

        jTable1.revalidate();
        jTable1.repaint();
    }

    private void addButtonActionPerfomed(ActionEvent evt) {
        try {
            Message message = new Message("Add");
            User usr = new User();
            usr.setServiceInfo(new ServiceInfo(tflogin.getText(), tfpassword.getText(), tfemail.getText()));
            usr.setAddress(new Address(tfcountry.getText(), tfcity.getText()));
            usr.setPrivateInformation(new PrivateInformation(tfname.getText(), tfsurname.getText(), LocalDate.now()));
            message.setUser(usr);
            messageHandler.sendMessage(message);
            WorkUser.getWork().addUser(usr);//остановился тут //tfbday.getText() заменил на LocalDate.now()
            if (searchFrame.getSearchRequest() != null) {
                Search search = new UserSearch();
                model.setArrayOfUsers((ArrayList<User>) search.searchByRegexp(searchFrame.getSearchRequest()));
            }
        } catch (JAXBException ex) {
            logger.debug(ex.getMessage());
        } catch (IOException ex) {
            logger.debug(ex.getMessage());
        } catch (NotFoundException ex) {
            logger.debug(ex.getMessage());
        }
        tfname.setText("");
        tfsurname.setText("");
        tfcountry.setText("");
        tfcity.setText("");
        tflogin.setText("");
        tfpassword.setText("");
        tfbday.setText("");
        tfemail.setText("");
        jTable1.revalidate();
        jTable1.repaint();
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            messageHandler.sendMessage(new Message("Exit"));
        } catch (JAXBException ex) {
            logger.debug(ex.getMessage());
        } catch (IOException ex) {
            logger.debug(ex.getMessage());
        }
        AdminRoom adminRoom = new AdminRoom(messageHandler);
        //SelectRooms rooms = new SelectRooms();
        //rooms.setVisible(true);
        adminRoom.setVisible(true);
        this.dispose();
        model.setArrayOfUsers(WorkUser.getWork().getArrOfUsers());
        searchFrame.setSearchRequest(null);
    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {

        searchFrame = new SearchFrameOfUser(jTable1, model, this);
        searchFrame.setVisible(true);

    }

    private void clearButtonActionPerformed(ActionEvent evt) {
        model.setArrayOfUsers(WorkUser.getWork().getArrOfUsers());
        searchFrame.setSearchRequest(null);
        jTable1.revalidate();
        jTable1.repaint();
    }

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {

     Logger log = Logger.getLogger(MemberList.class);
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
