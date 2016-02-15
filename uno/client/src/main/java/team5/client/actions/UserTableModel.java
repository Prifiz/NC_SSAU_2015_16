/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.TableModelListener;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import team5.datamodel.actions.WorkUser;
import team5.datamodel.exceptions.NotFoundException;
import team5.datamodel.exceptions.UserNotFoundException;
import team5.datamodel.searches.Search;
import team5.datamodel.searches.UserSearch;
import team5.datamodel.transmissions.Message;
import team5.datamodel.transmissions.MessageHandler;
import team5.datamodel.user.User;

/**
 *
 * @author андрей
 */
public class UserTableModel extends AbstractTableModel {

    private Logger logger = Logger.getLogger(UserTableModel.class);
    private final Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private WorkUser work = WorkUser.getWork();
    private MessageHandler messageHandler;
    private ArrayList<User> users;
    String[] names = {"Name", "Surname", "BirthDay", "Country", "City", "Email", "Login", "Password", "DateOfRegistration"};

    public UserTableModel(MessageHandler messageHandler) {
        work.cleanUsers();
        this.messageHandler = messageHandler;
        try {
            messageHandler.sendMessage(new Message("UserTable"));
            Message serverResponse = messageHandler.receiveMessage();
            for (int i = 0; i < serverResponse.getValue(); i++) {
                work.addUser(messageHandler.receiveMessage().getUser());
            }
        } catch (JAXBException ex) {
            logger.debug(ex.getMessage());
        } catch (IOException ex) {
            logger.debug(ex.getMessage());
        }
        users = work.getArrOfUsers();
    }

    public ArrayList getArrayOfUsers() {
        return users;
    }

    public void setArrayOfUsers(ArrayList<User> user) {
        this.users = user;
    }

    @Override
    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    @Override
    public String getColumnName(int columnIndex) {
        return names[columnIndex];

    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return names.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return users.get(rowIndex).getPrivateInformation().getName();
            case 1:
                return users.get(rowIndex).getPrivateInformation().getSurname();
            case 2:
                return users.get(rowIndex).getPrivateInformation().getbDay();
            case 3:
                return users.get(rowIndex).getAddress().getCountry();
            case 4:
                return users.get(rowIndex).getAddress().getCity();
            case 5:
                return users.get(rowIndex).getServiceInfo().getEmail();
            case 6:
                return users.get(rowIndex).getServiceInfo().getLogin();
            case 7:
                String s = users.get(rowIndex).getServiceInfo().getPassword();
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    str.append('*');
                }
                return str.toString();
            case 8:
                return users.get(rowIndex).getServiceInfo().getDateOfRegistration();

        }
        return null;

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;

    }

    /*@Override
     public Class<?> getColumnClass(int columnIndex)
     {
     return String.class;
     }*/
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Message clientRequest = new Message();
        try {
            messageHandler.sendMessage(new Message("SetUserInformation"));
        } catch (JAXBException ex) {
            logger.debug(ex.getMessage());
        } catch (IOException ex) {
            logger.debug(ex.getMessage());
        }
        switch (columnIndex) {
            case 0:
                clientRequest.setCommand("Name");
                clientRequest.setValue(rowIndex);
                clientRequest.setChoice((String) aValue);
                users.get(rowIndex).getPrivateInformation().setName((String) aValue);
                break;
            case 1:
                clientRequest.setCommand("Surname");
                clientRequest.setValue(rowIndex);
                clientRequest.setChoice((String) aValue);
                users.get(rowIndex).getPrivateInformation().setSurname((String) aValue);
                break;
            case 2:
                clientRequest.setCommand("bDay");
                clientRequest.setValue(rowIndex);
                clientRequest.setChoice((String) aValue);
                users.get(rowIndex).getPrivateInformation().setbDay(work.stringToLocalDate((String) aValue));
            case 3:
                clientRequest.setCommand("City");
                clientRequest.setValue(rowIndex);
                clientRequest.setChoice((String) aValue);
                users.get(rowIndex).getAddress().setCity((String) aValue);
                break;
            case 4:
                clientRequest.setCommand("Country");
                clientRequest.setValue(rowIndex);
                clientRequest.setChoice((String) aValue);
                users.get(rowIndex).getAddress().setCountry((String) aValue);
                break;
            case 5:
                clientRequest.setCommand("Email");
                clientRequest.setValue(rowIndex);
                clientRequest.setChoice((String) aValue);
                users.get(rowIndex).getServiceInfo().setEmail((String) aValue);
                break;
            case 6:
                try {
                    Search search = new UserSearch();
                    search.searchByField((String) aValue, "login").isEmpty();
                    clientRequest.setCommand("Login");
                    clientRequest.setValue(rowIndex);
                    clientRequest.setChoice((String) aValue);
                    users.get(rowIndex).getServiceInfo().setLogin((String) aValue);

                } catch (NotFoundException e) {
                    // TODO
                }
            //нужно написать обработку, когда лoгин не может быть изменен
            case 7:
                clientRequest.setCommand("Password");
                clientRequest.setValue(rowIndex);
                clientRequest.setChoice((String) aValue);
                users.get(rowIndex).getServiceInfo().setPassword((String) aValue);
                break;

        }
        try {
            messageHandler.sendMessage(clientRequest);
        } catch (JAXBException ex) {
            logger.debug(ex.getMessage());
        } catch (IOException ex) {
            logger.debug(ex.getMessage());
        }
    }

}
