package team5.client.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import team5.client.actions.CardTableModel;
import team5.client.actions.DataExchanger;
import team5.datamodel.card.Card;
import team5.datamodel.exceptions.NotFoundException;
import team5.datamodel.searches.CardSearch;
import team5.datamodel.searches.Search;

/**
 *
 * @author Dmitry
 */
public class SearchFrameOfCard extends javax.swing.JFrame {

    private DataExchanger dataE;
    private String searchRequest;
    private JButton backButton;
    private JButton okButton;
    private JTable table;
    private JLabel jMessage;
    private JCheckBox searchCheckBox;
    private boolean isRegular = false;

    private JTextField tfsearch;
//    private JTextField[] fields;
    CardTableModel model;

    private JPanel panel;

    public SearchFrameOfCard(DataExchanger dataE) {
        this.dataE = dataE;
        initStartFrame();
        initComponents();
        initCloseOperation();
    }

    public SearchFrameOfCard(JTable table, CardTableModel model) {
        this.table = table;
        //this.fields=fields;
        this.model = model;
        initStartFrame();
        initComponents();
        initCloseOperation();
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

    private void initStartFrame() {

        setPreferredSize(new Dimension(400, 250));
        setLayout(null);
        this.setResizable(false);
        setTitle("Search cards");

    }

    private void initComponents() {

        panel = new JPanel();
        panel.setBorder(new TitledBorder("Search cards"));
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

        searchCheckBox = new JCheckBox("Use regular expressions");
        panel.add(searchCheckBox);
        searchCheckBox.setBounds(30, 60, 250, 30);
        searchCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                searchCheckBoxPerformed(e);
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

    }

    private void initCloseOperation() {
//        

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        pack();
        this.setLocationRelativeTo(null);
    }

    private void searchCheckBoxPerformed(ItemEvent evt) {
        if (isRegular) {
            isRegular = false;
        } else {
            isRegular = true;
        }

    }
//    

    private void okButtonActionPerfomed(ActionEvent evt) {
        searchRequest = tfsearch.getText();
        try {
            Search search = new CardSearch();
            if (isRegular) {
                model.setArrayOfCards((ArrayList<Card>) search.searchByRegexp(searchRequest));
            } else {
                model.setArrayOfCards((ArrayList<Card>) search.searchBySubstring(searchRequest));
            }

            table.revalidate();
            table.repaint();
            this.setVisible(false);//TODO мб this.dispose();  ? 

        } catch (NotFoundException e) {
            jMessage.setText("Nothing found.");
        }
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
//    

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {

     Logger log = Logger.getLogger(SearchFrameOfUser.class);
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
