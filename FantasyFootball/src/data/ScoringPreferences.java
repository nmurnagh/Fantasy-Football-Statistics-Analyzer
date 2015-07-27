package data;

public class ScoringPreferences {

	private double passingYards;
	private double passingTD;
	private double ints;
	private double rushingYards;
	private double rushingTD;
	private double receivingYards;
	private double receivingTD;
	private double passingAtt;
	private double passingComp;
	private double incompPass;
	private double sacks;
	private double rushingAtt;
	private double receptions;
	private double fumbles;
	
	public double getPassingYards() {
		return passingYards;
	}
	public void setPassingYards(double passingYards) {
		this.passingYards = passingYards;
	}
	public double getPassingTD() {
		return passingTD;
	}
	public void setPassingTD(double passingTD) {
		this.passingTD = passingTD;
	}
	public double getInts() {
		return ints;
	}
	public void setInts(double ints) {
		this.ints = ints;
	}
	public double getRushingYards() {
		return rushingYards;
	}
	public void setRushingYards(double rushingYards) {
		this.rushingYards = rushingYards;
	}
	public double getRushingTD() {
		return rushingTD;
	}
	public void setRushingTD(double rushingTD) {
		this.rushingTD = rushingTD;
	}
	public double getReceivingYards() {
		return receivingYards;
	}
	public void setReceivingYards(double receivingYards) {
		this.receivingYards = receivingYards;
	}
	public double getReceivingTD() {
		return receivingTD;
	}
	public void setReceivingTD(double receivingTD) {
		this.receivingTD = receivingTD;
	}
	public double getPassingAtt() {
		return passingAtt;
	}
	public void setPassingAtt(double passingAtt) {
		this.passingAtt = passingAtt;
	}
	public double getPassingComp() {
		return passingComp;
	}
	public void setPassingComp(double passingComp) {
		this.passingComp = passingComp;
	}
	public double getIncompPass() {
		return incompPass;
	}
	public void setIncompPass(double incompPass) {
		this.incompPass = incompPass;
	}
	public double getSacks() {
		return sacks;
	}
	public void setSacks(double sacks) {
		this.sacks = sacks;
	}
	public double getRushingAtt() {
		return rushingAtt;
	}
	public void setRushingAtt(double rushingAtt) {
		this.rushingAtt = rushingAtt;
	}
	public double getReceptions() {
		return receptions;
	}
	public void setReceptions(double receptions) {
		this.receptions = receptions;
	}
	public double getFumbles() {
		return fumbles;
	}
	public void setFumbles(double fumbles) {
		this.fumbles = fumbles;
	}
	
	public ScoringPreferences() {
		passingAtt = 0;
		passingComp = 0;
		incompPass = 0;
		passingYards = 25;
		passingTD = 4;
		ints = -2;
		sacks = 0;
		rushingAtt = 0;
		rushingYards = 10;
		rushingTD = 6;
		receptions = 0;
		receivingYards = 10;
		receivingTD = 6;
		fumbles = -2;
	}
	
	
}

