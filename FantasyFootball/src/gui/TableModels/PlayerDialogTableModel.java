package gui.TableModels;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import data.Player;

/**
 * Table for displaying individual player statistics
 * 
 * @author Nathan Murnaghan
 *
 */
public class PlayerDialogTableModel extends AbstractTableModel {

	private String[] labels;
	private Player player;
	private Integer[] totals;

	public PlayerDialogTableModel() {

		labels = new String[] {"Pass Comp","Pass Att", "Pass Yds",
				"Pass TDs", "Ints", "Sacks", "Rush Att",
				"Rush Yds", "Rush TDs", "Rec", "Rec Yds",
				"Rec TDs", "Fum", "Fantasy"};
		totals = new Integer[] {0,0,0,0,0,0,0,0,0,0,0,0,0};

	}
	
	public String getColumnName(int col) {
		return labels[col];
	}

	public int getColumnCount() {
		return 14;
	}

	public int getRowCount() {
		return 1;
	}

	public Object getValueAt(int row, int col) {
		if (col == 13) {
			NumberFormat formatter = new DecimalFormat("#0.00");
			return formatter.format(player.getFantasyPoints(17));
		} else {
			return totals[col];
		}
	}
	
	//Gets data, calculates totals, then sets it
	public void setData(Player player) {
		this.player = player;
		HashMap<String, Integer> stats = player.getStats();
		for (int i=0;i<13;i++) {
			totals[i] = 0;
		}
		
		for (int i=0;i<17;i++) {
			totals[0] += stats.get("Comp"+i);
			totals[1] += stats.get("PassAtt"+i);
			totals[2] += stats.get("PassYds"+i);
			totals[3] += stats.get("PassTD"+i);
			totals[4] += stats.get("Int"+i);
			totals[5] += stats.get("Sck"+i);
			totals[6] += stats.get("RushAtt"+i);
			totals[7] += stats.get("RushYds"+i);
			totals[8] += stats.get("RushTD"+i);
			totals[9] += stats.get("Rec"+i);
			totals[10] += stats.get("RecYds"+i);
			totals[11] += stats.get("RecTD"+i);
			totals[12] += (stats.get("PassFum"+i) + stats.get("RushFum"+i) + stats.get("RecFum"+i));
		}
		
		
	}

}
