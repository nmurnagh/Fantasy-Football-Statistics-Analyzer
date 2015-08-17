package gui.TableModels;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import data.Player;

/**
 * Table Model for tables displaying Fantasy Points
 * 
 * @author Nathan Murnaghan
 *
 */
public class FantasyPointsTableModel extends TableModel {

	private String[] labels;

	public FantasyPointsTableModel() {

		labels = new String[] { "Rank", "Name", "Team", "Position", "Week 1",
				"Week 2", "Week 3", "Week 4", "Week 5", "Week 6", "Week 7",
				"Week 8", "Week 9", "Week 10", "Week 11", "Week 12", "Week 13",
				"Week 14", "Week 15", "Week 16", "Week 17", "Average", "Std Dev", "Total" };
	}
	
	public String getColumnName(int col) {
		return labels[col];
	}

	public Object getValueAt(int row, int col) {

		Player player = playerList.get(row);

		if (col == 0) {
			return row + 1;
		} else if (col == 1) {
			return player.getName();
		} else if (col == 2) {
			return player.getTeam();
		} else if (col == 3) {
			return player.getPosition();
		}
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(player.getFantasyPoints(col - 4));
	}
	
	//Gets data, sorts it, then sets it
	public void setData(ArrayList<Player> playerList) {
		Collections.sort(playerList, new Comparator<Player>() {
			public int compare(Player p1, Player p2) {
				if (p1.getFantasyPoints(19) == p2.getFantasyPoints(19)) {
					return 0;
				} else if (p1.getFantasyPoints(19) < p2.getFantasyPoints(19)) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		this.playerList = playerList;
	}
	
}
