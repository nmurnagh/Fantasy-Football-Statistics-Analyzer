package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import data.Player;

public class TableModel extends AbstractTableModel {
	
	protected ArrayList<Player> playerList;

	public int getColumnCount() {
		return 22;
	}

	public int getRowCount() {
		return playerList.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		//To be overridden in child classes
		return null;
	}
	
	public void setData(ArrayList<Player> playerList) {
		//To be overridden in child classes
	}
}
