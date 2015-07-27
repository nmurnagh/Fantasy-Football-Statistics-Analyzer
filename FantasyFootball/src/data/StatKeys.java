package data;

import java.util.ArrayList;

public class StatKeys {
	
	private String[] qbDefenseKeys;
	private String[] rbDefenseKeys;
	private String[] wrDefenseKeys;
	private String[] teDefenseKeys;
	private ArrayList<String> playerKeys;
	
	public String getQbDefenseKeys(int idx) {
		return qbDefenseKeys[idx];
	}

	public String getRbDefenseKeys(int idx) {
		return rbDefenseKeys[idx];
	}

	public String getWrDefenseKeys(int idx) {
		return wrDefenseKeys[idx];
	}

	public String getTeDefenseKeys(int idx) {
		return teDefenseKeys[idx];
	}

	public String getPlayerKeys(int idx) {
		return playerKeys.get(idx);
	}

	public StatKeys() {
		qbDefenseKeys = new String[] {"Comp", "PassAtt", "PassYds", "Int", "PassTD", "RushAtt", "RushYds", "RushTD"};
		rbDefenseKeys = new String[] {"RushAtt", "RushYds","RushTD","Rec","Targets","RecYds","RecTD"};
		wrDefenseKeys = new String[] {"RushAtt", "RushYds","RushTD","Rec","Targets","RecYds","RecTD"};
		teDefenseKeys = new String[] {"Rec", "Targets", "Yds", "TD"};
		playerKeys = new ArrayList<String>();
		
		for (int i=0; i<17; i++) {
			playerKeys.add("Comp"+i);
			playerKeys.add("PassAtt"+i);
			playerKeys.add("PassYds"+i);
			playerKeys.add("PassTD"+i);
			playerKeys.add("Int"+i);
			playerKeys.add("Sck"+i);
			playerKeys.add("PassFum"+i);
			playerKeys.add("RushAtt"+i);
			playerKeys.add("RushYds"+i);
			playerKeys.add("RushTD"+i);
			playerKeys.add("RushFum"+i);
			playerKeys.add("Rec"+i);
			playerKeys.add("RecYds"+i);
			playerKeys.add("RecTD"+i);
			playerKeys.add("RecFum"+i);
		}
	}
}
