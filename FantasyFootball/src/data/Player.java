package data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

public class Player implements Serializable, Comparable<Player> {

	private static final long serialVersionUID = 6119864773800200976L;
	
	private String name;
	private String team;
	private String position;
	private HashMap<String, Integer> stats;
	private String[] opponents;
	private Double[] fantasyPoints;
	private Double[] situationalPoints;
	
	public String getName() {
		return name;
	}
	public String getTeam() {
		return team;
	}
	public String getPosition() {
		return position;
	}
	public int getStats(String key) {
		return stats.get(key);
	}
	public String getOpponents(int idx) {
		return opponents[idx];
	}
	public Double[] getFantasyPoints() {
		return fantasyPoints;
	}
	public double getFantasyPoints(int idx) {
		return fantasyPoints[idx];
	}
	public double getSituationalPoints(int idx) {
		return situationalPoints[idx];
	}
	public void setSituationalPoints(int idx, double points) {
		this.situationalPoints[idx] = points;
	}
	
	public void calculateFantasyPoints(ScoringPreferences scoringprefs, HashMap<String, Double> defensePoints) {
		
		fantasyPoints = new Double[] {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
		situationalPoints = new Double[] {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
		
		for (int i=0;i<17;i++) {
			fantasyPoints[i] += scoringprefs.getPassingAtt()*this.getStats("PassAtt"+i);
			fantasyPoints[i] += scoringprefs.getPassingComp()*this.getStats("Comp"+i);
			fantasyPoints[i] += scoringprefs.getIncompPass()*(this.getStats("PassAtt"+i)-this.getStats("Comp"+i));
			fantasyPoints[i] += this.getStats("PassYds"+i)/scoringprefs.getPassingYards();
			fantasyPoints[i] += scoringprefs.getPassingTD()*this.getStats("PassTD"+i);
			fantasyPoints[i] += scoringprefs.getInts()*this.getStats("Int"+i);
			fantasyPoints[i] += scoringprefs.getSacks()*this.getStats("Sck"+i);
			fantasyPoints[i] += scoringprefs.getRushingAtt()*this.getStats("RushAtt"+i);
			fantasyPoints[i] += this.getStats("RushYds"+i)/scoringprefs.getRushingYards();
			fantasyPoints[i] += scoringprefs.getRushingTD()*this.getStats("RushTD"+i);
			fantasyPoints[i] += scoringprefs.getReceptions()*this.getStats("Rec"+i);
			fantasyPoints[i] += this.getStats("RecYds"+i)/scoringprefs.getReceivingYards();
			fantasyPoints[i] += scoringprefs.getReceivingTD()*this.getStats("RecTD"+i);
			fantasyPoints[i] += scoringprefs.getFumbles()*(this.getStats("PassFum"+i)+this.getStats("RushFum"+i)+this.getStats("RecFum"+i));
			fantasyPoints[17] += fantasyPoints[i];
			
			if (Arrays.asList("ERROR 42").contains(this.getOpponents(i))) {	
			} else {
				situationalPoints[i] += fantasyPoints[i] - defensePoints.get(this.getOpponents(i)+this.getPosition());	
			}
			
		}
		
	}
	
	public int compareTo(Player o) {
		if (this.getSituationalPoints(17) == o.getSituationalPoints(17)) {
			return 0;
		} else if (this.getSituationalPoints(17) < o.getSituationalPoints(17)) {
			return 1;
		} else {
			return -1;
		}
	}
}
