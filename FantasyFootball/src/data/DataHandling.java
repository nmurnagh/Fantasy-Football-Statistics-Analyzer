package data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class DataHandling {

	public static ArrayList<Player> loadPlayers() {
		ArrayList<Player> playerList = new ArrayList<Player>();
		
		try {
			ObjectInputStream os = new ObjectInputStream(DataHandling.class.getResourceAsStream("PlayerStats.ser"));
			
			int num = os.readInt();
			
			for (int i=0; i<num; i++) {
				playerList.add((Player) os.readObject());
			}
			
			os.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return playerList;
		
	}
	
	public static ArrayList<Defense> loadDefenses() {
		ArrayList<Defense> defenseList = new ArrayList<Defense>();
		
		try {
			ObjectInputStream os = new ObjectInputStream(DataHandling.class.getResourceAsStream("DefenseStats.ser"));
			
			for (int i=0; i<32; i++) {
				defenseList.add((Defense) os.readObject());
			}
			
			os.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return defenseList;
		
	}
	
	public static void normalizeSituationalStats(ArrayList<Player> players) {
		
		double min = 0.0;
		double max = 0.0;
		
		for (Player player: players) {
			for (int i=0;i<17;i++) {
				if (player.getSituationalPoints(i) > max) {
					max = player.getSituationalPoints(i);
				} else if(player.getSituationalPoints(i) < min) {
					min = player.getSituationalPoints(i);
				}
			}
		}
		
		for (Player player: players) {
			double count = 0.0;
			for (int i=0;i<17;i++) {
				if (Arrays.asList("ERROR 42").contains(player.getOpponents(i))) {	
				} else {
					count++;
					player.setSituationalPoints(i, (player.getSituationalPoints(i)-min)/(max-min)*100);
					player.setSituationalPoints(17, player.getSituationalPoints(17)+player.getSituationalPoints(i));
				}
			}
			if (count !=0) {
				player.setSituationalPoints(17, player.getSituationalPoints(17)/count);
			}
			
		}
		
		
	}
	
	public static ArrayList<Player> positionFilter(ArrayList<Player> playerList, int idx) {
		ArrayList<Player> filteredList = new ArrayList<Player>();
		
		for(Player player: playerList) {
			if (idx == 0) {
				filteredList.add(player);
			} else if (idx == 1 && player.getPosition().equals("QB")) {
				filteredList.add(player);
			} else if (idx == 2 && player.getPosition().equals("RB")) {
				filteredList.add(player);
			} else if (idx == 3 && player.getPosition().equals("WR")) {
				filteredList.add(player);
			} else if (idx == 4 && player.getPosition().equals("TE")) {
				filteredList.add(player);
			}
		}
		
		return filteredList;
	}
}
