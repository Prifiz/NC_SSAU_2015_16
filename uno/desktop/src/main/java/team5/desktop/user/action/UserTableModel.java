/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.user.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.TableModelListener;
import team5.desktop.user.User;

/**
 *
 * @author андрей
 */
public class UserTableModel extends AbstractTableModel {
    private final Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private WorkUser work = WorkUser.getWork();
    ArrayList<User> user = work.getArrOfUsers();
    String [] names = {"Name", "Surname" ,"BirthDay", "Country", "City","Email","Login","Password"};

    @Override
    public void addTableModelListener(TableModelListener listener){listeners.add(listener);}
    @Override
    public void removeTableModelListener(TableModelListener listener){listeners.remove(listener);}

    @Override
    public String getColumnName(int columnIndex)
    {
        switch(columnIndex)
        {
            case 0:
                return names[0];
            case 1:
                return names[1];
            case 2:
                return names[2];
            case 3:
                return names[3];
            case 4:
                return names[4];
            case 5:
                return names[5];
            case 6:
                return names[6];
            case 7:
                return names[7];
        }
        return "";
        
    }
    public int getRowCount() {
        return user.size();
    }

    public int getColumnCount() {
        return names.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return user.get(rowIndex).getPrivateInformation().getName();
            case 1:
                return user.get(rowIndex).getPrivateInformation().getSurname();
            case 2:
                return user.get(rowIndex).getPrivateInformation().getbDay();
            case 3:
                return user.get(rowIndex).getAddress().getCountry();
            case 4:
                return user.get(rowIndex).getAddress().getCity();
            case 5:
                return user.get(rowIndex).getServiceInfo().getEmail();
            case 6:
                return user.get(rowIndex).getServiceInfo().getLogin();
            case 7:
                return user.get(rowIndex).getServiceInfo().getPassword();
            
        }
        return null;
        
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
        
    }
    /*@Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return String.class;
    }*/
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        switch(columnIndex)
        {
            case 0:
                user.get(rowIndex).getPrivateInformation().setName((String)aValue);
                break;
            case 1:
                user.get(rowIndex).getPrivateInformation().setSurname((String)aValue);
                break;
            case 2:
                user.get(rowIndex).getPrivateInformation().setbDay(work.stringToLocalDate((String)aValue));
            case 3:
                user.get(rowIndex).getAddress().setCity((String)aValue);
                break;
            case 4:
                user.get(rowIndex).getAddress().setCountry((String)aValue);
                break;
            case 5:
                user.get(rowIndex).getServiceInfo().setEmail((String)aValue);
                break;
            case 6:
                if(work.search((String)aValue)==null)
                {
                    user.get(rowIndex).getServiceInfo().setLogin((String)aValue);
                }
                //нужно написать обработку, когда лгин не может быть изменен
            case 7:
                user.get(rowIndex).getServiceInfo().setPassword((String)aValue);   
                break;
                
        }
    }
    
}
