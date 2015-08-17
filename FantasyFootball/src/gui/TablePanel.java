package gui;

import gui.Dialogs.PlayerDialog;
import gui.Listeners.FilterListener;
import gui.TableModels.CustomTableRenderer;
import gui.TableModels.FantasyPointsTableModel;
import gui.TableModels.SituationalPointsTableModel;
import gui.TableModels.TableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import data.Player;

/**
 * The layout and design for the tables in the main frame
 * 
 * @author Nathan Murnaghan
 *
 */
public class TablePanel extends JTable {
	
	private JTable table;
	private TableModel tableModel;
	private FilterPanel positionFilters;
	private JPopupMenu popup;
	
	public TablePanel(int idx, MainFrame mainFrame) {
		
		//Initialize components
		positionFilters = new FilterPanel();
		popup = new JPopupMenu();
		JMenuItem additionalInfo = new JMenuItem("Additional Information");
		popup.add(additionalInfo);
		setLayout(new BorderLayout());
		
		//Sets table model depending on data
		if (idx == 0) {
			tableModel = new FantasyPointsTableModel();
			add(positionFilters, BorderLayout.SOUTH);
			
		} else if (idx == 1) {
			tableModel = new SituationalPointsTableModel();
		}
		table = new JTable(tableModel);
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		
		//Formatting the Table
		for(int i=0; i<24;i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableRenderer());
		}
		
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setRowHeight(25);
		TableColumn column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(50);
		TableColumn column1 = table.getColumnModel().getColumn(1);
		column1.setPreferredWidth(175);
		table.getTableHeader().setFont(new Font("Arial",Font.PLAIN,15));
		
		//Listener for back click on table to launch popup
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				int col = table.columnAtPoint(e.getPoint());
				int row = table.rowAtPoint(e.getPoint());
				table.setColumnSelectionInterval(col,col);
				table.setRowSelectionInterval(row,row);

				if(e.getButton() == MouseEvent.BUTTON3 || e.getButton() == MouseEvent.BUTTON1 && col == 1) {
					popup.show(table, e.getX(), e.getY());
				}
			}
		});
		
		//Listener for popup click on table to launch dialog
		additionalInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				PlayerDialog test = new PlayerDialog(mainFrame,tableModel.getPlayer(row));
			}
		});
	}
	
	//Sets data for the table
	public void setData(ArrayList<Player> playerList) {
		tableModel.setData(playerList);
	}
	
	//Fires table refresh if data changes
	public void refresh(ArrayList<Player> playerList) {
		tableModel.setData(playerList);
		tableModel.fireTableDataChanged();
	}
	
	//Sets Listener
	public void setFilterListener(FilterListener filterListener) {
		positionFilters.setFilterListener(filterListener);
	}
}
