package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class FilterPanel extends JPanel {
	
	private JButton allBtn;
	private JButton qbBtn;
	private JButton rbBtn;
	private JButton wrBtn;
	private JButton teBtn;
	private FilterListener filterListener;
	private ActionListener buttonClick;

	public FilterPanel() {
		
		Dimension dim = getPreferredSize();
		dim.height = 100;
		setPreferredSize(dim);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		Border innerBorder = BorderFactory.createTitledBorder(blackline, "Position Filters");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		
		add(allBtn = new JButton("All Positions"),gc);
		gc.gridx++;
		add(qbBtn = new JButton("Quarterbacks"),gc);
		gc.gridx++;
		add(rbBtn = new JButton("Running Backs"),gc);
		gc.gridx++;
		add(wrBtn = new JButton("Wide Recievers"),gc);
		gc.gridx++;
		add(teBtn = new JButton("Tight Ends"),gc);
		
		buttonClick = new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				
				int idx = 0;
				if (ev.getSource()== allBtn) {
					idx = 0;
				} else if (ev.getSource() == qbBtn) {
					idx = 1;
				} else if (ev.getSource() == rbBtn) {
					idx = 2;
				} else if (ev.getSource() == wrBtn) {
					idx = 3;
				} else if (ev.getSource() == teBtn) {
					idx = 4;
				}
				
				if(filterListener != null) {
					filterListener.buttonSet(idx);
				}
			}
		};
		
		allBtn.addActionListener(buttonClick);
		qbBtn.addActionListener(buttonClick);
		rbBtn.addActionListener(buttonClick);
		wrBtn.addActionListener(buttonClick);
		teBtn.addActionListener(buttonClick);
		
		
	}
	
	public void setFilterListener(FilterListener filterListener) {
		this.filterListener = filterListener;
	}
}
