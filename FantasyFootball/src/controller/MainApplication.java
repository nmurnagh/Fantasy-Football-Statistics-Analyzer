package controller;

import gui.MainFrame;
import gui.Dialogs.ScoringDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.SwingUtilities;

import data.DataHandling;
import data.Defense;
import data.Player;
import data.ScoringPreferences;

public class MainApplication {

	static ArrayList<Player> playerList = new ArrayList<Player>();
	static ArrayList<Defense> defenseList = new ArrayList<Defense>();
	static ScoringPreferences scoring = new ScoringPreferences();

	public static void main(String[] args) {
		
		//Loads Players and Defenses
		playerList = DataHandling.loadPlayers();
		defenseList = DataHandling.loadDefenses();
		
		//Calculates fantasy/situational points
		for (Player player: playerList) {
			player.calculateFantasyPoints(scoring, Defense.calculateDefensePoints(defenseList, scoring));
		}
		
		//Normalizes the situational points
		for (int i=1;i<5;i++) {
			DataHandling.normalizeSituationalStats(DataHandling.positionFilter(playerList, i));
		}
		
		//Launches GUI
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				new MainFrame();
			}
		});
		
		
	}
	
	//Sets the values shown in the scoring dialog
	public void setScoringValues(ScoringDialog scoringDialog) {
		scoringDialog.setDefaults(scoring.getPassingAtt(),
				scoring.getPassingComp(), scoring.getIncompPass(),
				scoring.getPassingYards(), scoring.getPassingTD(),
				scoring.getInts(), scoring.getSacks(), scoring.getRushingAtt(),
				scoring.getRushingYards(), scoring.getRushingTD(),
				scoring.getReceptions(), scoring.getReceivingYards(),
				scoring.getReceivingTD(), scoring.getFumbles());
	}
	
	//Saves any changes made to scoring preferences by user
	public void setScoringPreferences(double pa, double pc, double ip,
			double py, double pt, double in, double sc, double ra, double ry,
			double rt, double rec, double recy, double rect, double fum) {
		scoring.setPassingAtt(pa);
		scoring.setPassingComp(pc);
		scoring.setIncompPass(ip);
		scoring.setPassingYards(py);
		scoring.setPassingTD(pt);
		scoring.setInts(in);
		scoring.setSacks(sc);
		scoring.setRushingAtt(ra);
		scoring.setRushingYards(ry);
		scoring.setRushingTD(rt);
		scoring.setReceptions(rec);
		scoring.setReceivingYards(recy);
		scoring.setReceivingTD(rect);
		scoring.setFumbles(fum);
		
		//Updates the fantasy points
		for (Player player: playerList) {
			player.calculateFantasyPoints(scoring, Defense.calculateDefensePoints(defenseList, scoring));
		}
		for (int i=1;i<5;i++) {
			DataHandling.normalizeSituationalStats(DataHandling.positionFilter(playerList, i));
		}
	}
	
	//Returns playerList
	public ArrayList<Player> getPlayers(int idx) {
		return DataHandling.positionFilter(playerList, idx);
	}
	
	//Returns specific player
	public Player getPlayer() {
		return playerList.get(0);
	}

	//Returns defense statistics
	public HashMap<String, Double> getDefense() {
		return Defense.calculateDefensePoints(defenseList, scoring);
	}
}
