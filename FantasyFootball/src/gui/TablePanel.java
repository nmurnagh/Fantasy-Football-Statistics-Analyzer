package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import data.Player;

public class TablePanel extends JTable {
	
	private JTable table;
	private TableModel tableModel;
	private FilterPanel positionFilters;

	
	public TablePanel(int idx) {
		
		positionFilters = new FilterPanel();
		setLayout(new BorderLayout());
		
		if (idx == 0) {
			tableModel = new FantasyPointsTableModel();
			add(positionFilters, BorderLayout.SOUTH);
			
		} else if (idx == 1) {
			tableModel = new SituationalPointsTableModel();
		}
		
		table = new JTable(tableModel);
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		
		//Formatting the Table
		for(int i=0; i<22;i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableRenderer());
		}
		
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setRowHeight(25);
		TableColumn column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(50);
		TableColumn column1 = table.getColumnModel().getColumn(1);
		column1.setPreferredWidth(150);
		
		table.getTableHeader().setFont(new Font("Arial",Font.PLAIN,15));
	}
	
	public void setData(ArrayList<Player> playerList) {
		tableModel.setData(playerList);
	}
	
	public void refresh(ArrayList<Player> playerList) {
		tableModel.setData(playerList);
		tableModel.fireTableDataChanged();
	}
	
	public void setFilterListener(FilterListener filterListener) {
		positionFilters.setFilterListener(filterListener);
	}
}
