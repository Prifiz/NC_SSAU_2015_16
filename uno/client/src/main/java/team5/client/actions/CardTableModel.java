/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import team5.datamodel.actions.WorkCard;
import team5.datamodel.card.Card;

/**
 *
 * @author Dmitry
 */
public class CardTableModel extends AbstractTableModel {

    private final Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private WorkCard work = WorkCard.getWork();
    private ArrayList<Card> cards = work.getArrOfCards();
    String[] names = {"Icon", "Color"};

    public ArrayList getArrayOfCards() {
        return cards;
    }

    public void setArrayOfCards(ArrayList<Card> cards) {
        this.cards = cards;
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
        return cards.size();
    }

    public int getColumnCount() {
        return names.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return cards.get(rowIndex).getIcon();
            case 1:
                return cards.get(rowIndex).getColor();

        }
        return null;

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                cards.get(rowIndex).setIcon((int) aValue);
                break;
            case 1:
                cards.get(rowIndex).setColor((String) aValue);
                break;
        }
    }
}
