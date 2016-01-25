/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.TableModelListener;
import team5.client.exceptions.UserNotFoundException;
import team5.client.user.User;

/**
 *
 * @author андрей
 */
public class UserTableModel extends AbstractTableModel {

    private final Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private WorkUser work = WorkUser.getWork();
    private ArrayList<User> users = work.getArrOfUsers();
    String[] names = {"Name", "Surname", "BirthDay", "Country", "City", "Email", "Login", "Password"};

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

    public int getRowCount() {
        return users.size();
    }

    public int getColumnCount() {
        return names.length;
    }

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
        switch (columnIndex) {
            case 0:
                users.get(rowIndex).getPrivateInformation().setName((String) aValue);
                break;
            case 1:
                users.get(rowIndex).getPrivateInformation().setSurname((String) aValue);
                break;
            case 2:
                users.get(rowIndex).getPrivateInformation().setbDay(work.stringToLocalDate((String) aValue));
            case 3:
                users.get(rowIndex).getAddress().setCity((String) aValue);
                break;
            case 4:
                users.get(rowIndex).getAddress().setCountry((String) aValue);
                break;
            case 5:
                users.get(rowIndex).getServiceInfo().setEmail((String) aValue);
                break;
            case 6:
                try {
                    if (work.search((String) aValue) == null) {
                        users.get(rowIndex).getServiceInfo().setLogin((String) aValue);
                    }
                } catch (UserNotFoundException e) {
                    // TODO
                }
            //нужно написать обработку, когда лoгин не может быть изменен
            case 7:
                users.get(rowIndex).getServiceInfo().setPassword((String) aValue);
                break;

        }
    }

}
