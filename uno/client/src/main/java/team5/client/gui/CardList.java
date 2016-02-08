package team5.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import team5.client.actions.CardTableModel;
import team5.client.actions.DataExchanger;
import team5.library.actions.WorkCard;
import team5.library.actions.WorkUser;
import team5.library.transmissions.FileHandler;
import team5.library.card.Card;
import team5.library.exceptions.NotFoundException;
import team5.library.searches.CardSearch;
import team5.library.searches.Search;

/**
 *
 * @author Dmitry
 */
public class CardList extends javax.swing.JFrame {

    private DataExchanger dataE;
    private Logger log = Logger.getLogger(MemberListFrame.class);
    private SearchFrameOfCard searchFrame;
    private JButton backButton;
    private JButton deleteButton;
    private JButton addButton;
    private JButton searchButton;
    private JButton cleanButton;

    private JTextField tficon;
    private JTextField tfcolor;

    private JLabel labicon;
    private JLabel labcolor;

    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JPanel panel;

    private CardTableModel model;

    public CardList(DataExchanger dataE) {
        this.dataE = dataE;
        initStartFrame();
        initComponents();
        initCloseOperation();
    }

    private void initStartFrame() {
        searchFrame = new SearchFrameOfCard(dataE);
        setLayout(null);
        setBounds(200, 10, 710, 790);
        this.setLocationRelativeTo(null);

        this.setResizable(false);
        setTitle("Card list");
    }

    private void initComponents() {

        panel = new JPanel();
        panel.setBorder(new TitledBorder("Add/delete cards"));
        panel.setBounds(10, 440, 670, 280);
        panel.setLayout(null);

        jScrollPane1 = new JScrollPane(jTable1);
        jScrollPane1.setBounds(10, 10, 670, 420);
        //Таблица и модель
        model = new CardTableModel();
        jTable1 = new JTable(model);
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
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        addButton = new JButton();
        addButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        addButton.setText("Add Card");
        panel.add(addButton);
        addButton.setBounds(20, 190, 150, 30);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addButtonActionPerfomed(e);
            }
        });

        deleteButton = new JButton();
        deleteButton.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        deleteButton.setText("Delete Card");
        panel.add(deleteButton);
        deleteButton.setBounds(20, 230, 150, 30);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteButtonActionPerfomed(e);
            }
        });

        //TextFields
        tficon = new JTextField();
        tficon.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        panel.add(tficon);
        tficon.setBounds(120, 30, 130, 30);

        tfcolor = new JTextField();
        tfcolor.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        panel.add(tfcolor);
        tfcolor.setBounds(490, 30, 130, 30);

        //Labels
        labicon = new JLabel("Icon");
        labicon.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        labicon.setBounds(20, 30, 90, 30);
        panel.add(labicon);

        labcolor = new JLabel("Color");
        labcolor.setFont(new java.awt.Font("Comic Sans MS", 0, 13));
        labcolor.setBounds(390, 30, 90, 30);
        panel.add(labcolor);

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
//TODO есть ли смысл тут писать аналог как для пользователя?

            @Override
            public void windowClosing(WindowEvent event) {
                try {
                    WorkUser workUser = WorkUser.getWork();
                    FileHandler workWithFiles = new FileHandler();
                    //sd.serializableData("serializableData_WorkUser.bin", workUser);
                    workWithFiles.marshalData("marshalData_WorkUser.xml", workUser);
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } catch (JAXBException ex) {
                    log.debug(ex.getMessage());
                }//                catch (IOException ex) {
                //                    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
                //                }
                finally {
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
    }

    private void deleteButtonActionPerfomed(ActionEvent evt) {
        try {
            if ((jTable1.getSelectedRow() >= 0) && (jTable1.getSelectedRow() < WorkCard.getWork().getOfCountCards())) {
                WorkCard.getWork().deleteCard(WorkCard.getWork().getArrOfCards().get(jTable1.getSelectedRow()).getIcon(),
                        WorkCard.getWork().getArrOfCards().get(jTable1.getSelectedRow()).getColor());
                if (searchFrame.getSearchRequest() != null) {
                    Search search = new CardSearch();
                    model.setArrayOfCards((ArrayList<Card>) search.regularSearch(searchFrame.getSearchRequest()));
                }
            }
        } catch (NotFoundException e) {
            log.debug(e.getMessage());
        }

        jTable1.revalidate();
        jTable1.repaint();
    }

    private void addButtonActionPerfomed(ActionEvent evt) {
        try {
            WorkCard.getWork().addCard(Integer.parseInt(tficon.getText()), tfcolor.getText());
            if (searchFrame.getSearchRequest() != null) {
                Search search = new CardSearch();
                model.setArrayOfCards((ArrayList<Card>) search.regularSearch(searchFrame.getSearchRequest()));
            }
        } catch (NotFoundException ex) {
            log.debug(ex.getMessage());
        }
        tficon.setText("");
        tfcolor.setText("");
        jTable1.revalidate();
        jTable1.repaint();
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        AdminRoom adminRoom = new AdminRoom(dataE);
        //SelectRooms rooms = new SelectRooms();
        //rooms.setVisible(true);
        adminRoom.setVisible(true);
        this.setVisible(false);
        model.setArrayOfCards(WorkCard.getWork().getArrOfCards());
        searchFrame.setSearchRequest(null);
    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {

        searchFrame = new SearchFrameOfCard(jTable1, model);
        searchFrame.setVisible(true);

    }

    private void clearButtonActionPerformed(ActionEvent evt) {
        model.setArrayOfCards(WorkCard.getWork().getArrOfCards());
        searchFrame.setSearchRequest(null);
        jTable1.revalidate();
        jTable1.repaint();
    }

}
