package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import controller.MainApplication;

public class MainFrame extends JFrame {

	private MainApplication mainApplication;
	private JTabbedPane tabPane;
	private ScoringDialog scoringDialog;
	private TablePanel fantasyPointsTablePanel;
	private TablePanel qbSituationalPointsTablePanel;
	private TablePanel rbSituationalPointsTablePanel;
	private TablePanel wrSituationalPointsTablePanel;
	private TablePanel teSituationalPointsTablePanel;

	public MainFrame() {
		super("Fantasy Football Statistics Analyzer");
		
		//Initialization of Components
		mainApplication = new MainApplication();
		scoringDialog = new ScoringDialog(this);
		fantasyPointsTablePanel = new TablePanel(0);
		qbSituationalPointsTablePanel = new TablePanel(1);
		rbSituationalPointsTablePanel = new TablePanel(1);
		wrSituationalPointsTablePanel = new TablePanel(1);
		teSituationalPointsTablePanel = new TablePanel(1);
		setJMenuBar(createMenuBar());
		tabPane = new JTabbedPane();
		
		//Setting up Components
		fantasyPointsTablePanel.setData(mainApplication.getPlayers(0));
		qbSituationalPointsTablePanel.setData(mainApplication.getPlayers(1));
		rbSituationalPointsTablePanel.setData(mainApplication.getPlayers(2));
		wrSituationalPointsTablePanel.setData(mainApplication.getPlayers(3));
		teSituationalPointsTablePanel.setData(mainApplication.getPlayers(4));
		tabPane.add("Fantasy Points", fantasyPointsTablePanel);
		tabPane.add("QB - Situational Stats", qbSituationalPointsTablePanel);
		tabPane.add("RB - Situational Stats", rbSituationalPointsTablePanel);
		tabPane.add("WR - Situational Stats", wrSituationalPointsTablePanel);
		tabPane.add("TE - Situational Stats", teSituationalPointsTablePanel);
		
		
		//Listeners
		scoringDialog.setScoringListener(new ScoringListener() {
			public void scoringSet(double pa, double pc, double ip, double py,
					double pt, double in, double sc, double ra, double ry,
					double rt, double rec, double recy, double rect, double fum) {
				mainApplication.setScoringPreferences(pa, pc, ip, py, pt, in,
						sc, ra, ry, rt, rec, recy, rect, fum);
				fantasyPointsTablePanel.refresh(mainApplication.getPlayers(0));
				qbSituationalPointsTablePanel.refresh(mainApplication.getPlayers(1));
				rbSituationalPointsTablePanel.refresh(mainApplication.getPlayers(2));
				wrSituationalPointsTablePanel.refresh(mainApplication.getPlayers(3));
				teSituationalPointsTablePanel.refresh(mainApplication.getPlayers(4));
			}
		});
		
		fantasyPointsTablePanel.setFilterListener(new FilterListener() {
			public void buttonSet(int idx) {
				fantasyPointsTablePanel.refresh(mainApplication.getPlayers(idx));
			}
		});
		

		//Formatting MainFrame
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(960, 540));
		setSize(1600, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		add(tabPane, BorderLayout.CENTER);
	}

	public JMenuBar createMenuBar() {

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exitItem);

		JMenu prefMenu = new JMenu("Preferences");
		JMenuItem scoring = new JMenuItem("Scoring...");

		prefMenu.add(scoring);

		menuBar.add(fileMenu);
		menuBar.add(prefMenu);

		// ActionListensers for MenuBar

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you really want to exit the application?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

		scoring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				mainApplication.setScoringValues(scoringDialog);
				scoringDialog.setVisible(true);
			}
		});

		return menuBar;
	}
}
