package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Defense implements Serializable{

	private static final long serialVersionUID = -4943112127731251850L;
	
	private String team;
	private HashMap<String, Integer> qbStats;
	private HashMap<String, Integer> rbStats;
	private HashMap<String, Integer> teStats;
	private HashMap<String, Integer> wrStats;
	
	public String getTeam() {
		return team;
	}
	public int getQbStats(String key) {
		return qbStats.get(key);
	}
	public int getRbStats(String key) {
		return rbStats.get(key);
	}
	public int getTeStats(String key) {
		return teStats.get(key);
	}
	public int getWrStats(String key) {
		return wrStats.get(key);
	}
	
	/**
	 * Returns a hashmap of the average points allowed to 
	 * each position throughout the season with the keys being
	 * "TeamName+Position"
	 * 
	 * @param defenses - ArrayList of all the defenses
	 * @param scoring - League scoring preferences
	 * @return defensePoints - Returns a hashmap of the average points allowed to 
	 * each position throughout the season
	 */
	public static HashMap<String, Double> calculateDefensePoints(ArrayList<Defense> defenses, ScoringPreferences scoring) {
		HashMap<String, Double> defensePoints = new HashMap<String, Double>();
		
		for(Defense defense: defenses) {
			double qb = 0.0;
			double rb = 0.0;
			double te = 0.0;
			double wr = 0.0;
			
			qb += scoring.getPassingAtt()*defense.getQbStats("PassAtt");
			qb += scoring.getPassingComp()*defense.getQbStats("Comp");
			qb += scoring.getIncompPass()*(defense.getQbStats("PassAtt")-defense.getQbStats("Comp"));
			qb += defense.getQbStats("PassYds")/scoring.getPassingYards();
			qb += scoring.getPassingTD()*defense.getQbStats("PassTD");
			qb += scoring.getInts()*defense.getQbStats("Int");
			qb += scoring.getRushingAtt()*defense.getQbStats("RushAtt");
			qb += defense.getQbStats("RushYds")/scoring.getRushingYards();
			qb += scoring.getRushingTD()*defense.getQbStats("RushTD");
			
			rb += scoring.getRushingAtt()*defense.getRbStats("RushAtt");
			rb += defense.getRbStats("RushYds")/scoring.getRushingYards();
			rb += scoring.getRushingTD()*defense.getRbStats("RushTD");
			rb += scoring.getReceptions()*defense.getRbStats("Rec");
			rb += defense.getRbStats("RecYds")/scoring.getReceivingYards();
			rb += scoring.getReceivingTD()*defense.getRbStats("RecTD");
			
			wr += scoring.getRushingAtt()*defense.getWrStats("RushAtt");
			wr += defense.getWrStats("RushYds")/scoring.getRushingYards();
			wr += scoring.getRushingTD()*defense.getWrStats("RushTD");
			wr += scoring.getReceptions()*defense.getWrStats("Rec");
			wr += defense.getWrStats("RecYds")/scoring.getReceivingYards();
			wr += scoring.getReceivingTD()*defense.getWrStats("RecTD");
			
			te += scoring.getReceptions()*defense.getTeStats("Rec");
			te += defense.getTeStats("Yds")/scoring.getReceivingYards();
			te += scoring.getReceivingTD()*defense.getTeStats("TD");
			
			defensePoints.put(defense.getTeam()+"QB", qb/16.0);
			defensePoints.put(defense.getTeam()+"RB", rb/16.0);
			defensePoints.put(defense.getTeam()+"WR", wr/16.0);
			defensePoints.put(defense.getTeam()+"TE", te/16.0);
		}
		return defensePoints;
	}
	

}
