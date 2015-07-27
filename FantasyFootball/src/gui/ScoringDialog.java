package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScoringDialog extends JDialog {

	private JButton saveButton;
	private JButton cancelButton;
	private JTextField passingYards;
	private JTextField passingTD;
	private JTextField ints;
	private JTextField rushingYards;
	private JTextField rushingTD;
	private JTextField receivingYards;
	private JTextField receivingTD;
	private JTextField passingAtt;
	private JTextField passingComp;
	private JTextField incompPass;
	private JTextField sacks;
	private JTextField rushingAtt;
	private JTextField receptions;
	private JTextField fumbles;
	private ScoringListener scoringListener;

	public ScoringDialog(JFrame parent) {
		super(parent, "Scoring Preferences", false);

		setLayout(new GridBagLayout());
		setMinimumSize(new Dimension(400, 800));
		setLocationRelativeTo(parent);

		GridBagConstraints gc = new GridBagConstraints();

		gc.gridy = 0;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		// ///////////First Row////////////////////

		gc.anchor = GridBagConstraints.LINE_START;
		add(new JLabel("  Passing"), gc);

		// ///////////Next Row/////////////////////

		gc.gridy++;

		gc.insets = new Insets(0, 30, 0, 0);
		add(new JLabel("Passing Attempts"), gc);
		gc.gridx++;
		gc.insets = new Insets(0, 0, 0, 0);
		add(passingAtt = new JTextField(3), gc);
		gc.gridx++;
		add(new JLabel("Points"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		gc.insets = new Insets(0, 30, 0, 0);
		add(new JLabel("Passing Completions"), gc);
		gc.gridx++;
		gc.insets = new Insets(0, 0, 0, 0);
		add(passingComp = new JTextField(3), gc);
		gc.gridx++;
		add(new JLabel("Points"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		gc.insets = new Insets(0, 30, 0, 0);
		add(new JLabel("Incomplete Passes"), gc);
		gc.gridx++;
		gc.insets = new Insets(0, 0, 0, 0);
		add(incompPass = new JTextField(3), gc);
		gc.gridx++;
		add(new JLabel("Points"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		gc.insets = new Insets(0, 30, 0, 0);
		add(new JLabel("Passing Yards: 1 Point for every "), gc);
		gc.gridx++;
		gc.insets = new Insets(0, 0, 0, 0);
		add(passingYards = new JTextField(3), gc);
		gc.gridx++;
		add(new JLabel("Yards"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		gc.insets = new Insets(0, 30, 0, 0);
		add(new JLabel("Passing Touchdowns"), gc);
		gc.gridx++;
		gc.insets = new Insets(0, 0, 0, 0);
		add(passingTD = new JTextField(3), gc);
		gc.gridx++;
		add(new JLabel("Points"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		gc.insets = new Insets(0, 30, 0, 0);
		add(new JLabel("Interceptions"), gc);
		gc.gridx++;
		gc.insets = new Insets(0, 0, 0, 0);
		add(ints = new JTextField(3), gc);
		gc.gridx++;
		add(new JLabel("Points"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		gc.insets = new Insets(0, 30, 0, 0);
		add(new JLabel("Sacks"), gc);
		gc.gridx++;
		gc.insets = new Insets(0, 0, 0, 0);
		add(sacks = new JTextField(3), gc);
		gc.gridx++;
		add(new JLabel("Points"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		add(new JLabel("  Rushing"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		gc.insets = new Insets(0, 30, 0, 0);
		add(new JLabel("Rushing Attempts"), gc);
		gc.gridx++;
		gc.insets = new Insets(0, 0, 0, 0);
		add(rushingAtt = new JTextField(3), gc);
		gc.gridx++;
		add(new JLabel("Points"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		gc.insets = new Insets(0, 30, 0, 0);
		add(new JLabel("Rushing Yards: 1 point for every "), gc);
		gc.gridx++;
		gc.insets = new Insets(0, 0, 0, 0);
		add(rushingYards = new JTextField(3), gc);
		gc.gridx++;
		add(new JLabel("Yards"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		gc.insets = new Insets(0, 30, 0, 0);
		add(new JLabel("Rushing Touchdowns"), gc);
		gc.gridx++;
		gc.insets = new Insets(0, 0, 0, 0);
		add(rushingTD = new JTextField(3), gc);
		gc.gridx++;
		add(new JLabel("Points"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		add(new JLabel("  Receiving"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		gc.insets = new Insets(0, 30, 0, 0);
		add(new JLabel("Receptions"), gc);
		gc.gridx++;
		gc.insets = new Insets(0, 0, 0, 0);
		add(receptions = new JTextField(3), gc);
		gc.gridx++;
		add(new JLabel("Points"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		gc.insets = new Insets(0, 30, 0, 0);
		add(new JLabel("Receiving Yards: 1 point for every "), gc);
		gc.gridx++;
		gc.insets = new Insets(0, 0, 0, 0);
		add(receivingYards = new JTextField(3), gc);
		gc.gridx++;
		add(new JLabel("Yards"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		gc.insets = new Insets(0, 30, 0, 0);
		add(new JLabel("Receiving Touchdowns"), gc);
		gc.gridx++;
		gc.insets = new Insets(0, 0, 0, 0);
		add(receivingTD = new JTextField(3), gc);
		gc.gridx++;
		add(new JLabel("Points"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		add(new JLabel("  Miscellaneous"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		gc.insets = new Insets(0, 30, 0, 0);
		add(new JLabel("Fumbles Lost"), gc);
		gc.gridx++;
		gc.insets = new Insets(0, 0, 0, 0);
		add(fumbles = new JTextField(3), gc);
		gc.gridx++;
		add(new JLabel("Points"), gc);

		// ///////////Next Row/////////////////////

		gc.gridx = 0;
		gc.gridy++;

		gc.insets = new Insets(0, 20, 0, 0);
		add(saveButton = new JButton("Save"), gc);
		gc.gridx = 2;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(cancelButton = new JButton("Cancel"), gc);

		// Action Listeners
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				double pa = Double.parseDouble(passingAtt.getText());
				double pc = Double.parseDouble(passingComp.getText());
				double ip = Double.parseDouble(incompPass.getText());
				double py = Double.parseDouble(passingYards.getText());
				double pt = Double.parseDouble(passingTD.getText());
				double in = Double.parseDouble(ints.getText());
				double sc = Double.parseDouble(sacks.getText());
				double ra = Double.parseDouble(rushingAtt.getText());
				double ry = Double.parseDouble(rushingYards.getText());
				double rt = Double.parseDouble(rushingTD.getText());
				double rec = Double.parseDouble(receptions.getText());
				double recy = Double.parseDouble(receivingYards.getText());
				double rect = Double.parseDouble(receivingTD.getText());
				double fum = Double.parseDouble(fumbles.getText());
				
				if(scoringListener != null) {
					scoringListener.scoringSet(pa,pc,ip,py,pt,in,sc,ra,ry,rt,rec,recy,rect,fum);
				}
				
				setVisible(false);
			}
			
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				setVisible(false);
			}
		});

	}

	public void setDefaults(double pa, double pc, double ip, double py,
			double pt, double in, double sc, double ra, double ry,
			double rt, double rec, double recy, double rect, double fum) {
		
		passingAtt.setText(String.valueOf(pa));
		passingComp.setText(String.valueOf(pc));
		incompPass.setText(String.valueOf(ip));
		passingYards.setText(String.valueOf(py));
		passingTD.setText(String.valueOf(pt));
		ints.setText(String.valueOf(in));
		sacks.setText(String.valueOf(sc));
		rushingAtt.setText(String.valueOf(ra));
		rushingYards.setText(String.valueOf(ry));
		rushingTD.setText(String.valueOf(rt));
		receptions.setText(String.valueOf(rec));
		receivingYards.setText(String.valueOf(recy));
		receivingTD.setText(String.valueOf(rect));
		fumbles.setText(String.valueOf(fum));
		
	}
	
	public void setScoringListener(ScoringListener scoringListener) {
		this.scoringListener = scoringListener;
	}
}
