package gui.Dialogs;

import gui.TableModels.CustomTableRenderer;
import gui.TableModels.PlayerDialogTableModel;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import controller.MainApplication;
import data.Player;

/**
 * Dialog Panel containing additional player information
 * Launches after back clicking on player in table
 * 
 * @author Nathan Murnaghan
 *
 */
public class PlayerDialog extends JDialog {
	
	private MainApplication mainApplication;
	private JLabel nameLabel;
	private JLabel teamLabel;
	private JTable table;
	private PlayerDialogTableModel tableModel;
	private JScrollPane tablePane;
	private ChartPanel playerChart;
	
	public PlayerDialog(JFrame parent, Player player) {
		super(parent, "Player Details", false);
		mainApplication = new MainApplication();
		
		//Formatting
		setSize(new Dimension(1200,600));
		setResizable(false);
		setLocationRelativeTo(parent);
		setVisible(true);
		
		//Creating stats table
		table = new JTable(tableModel = new PlayerDialogTableModel());
		tableModel.setData(player);
		for(int i=0; i<14;i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableRenderer());
		}
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setRowHeight(25);
		table.getTableHeader().setFont(new Font("Arial",Font.PLAIN,15));
		TableColumn column1 = table.getColumnModel().getColumn(0);
		column1.setPreferredWidth(100);
		
		//Layout of Dialog
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy = 0;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		//////////////First Row////////////////////
		gc.insets = new Insets(0, 30, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameLabel = new JLabel(player.getName()),gc);
		nameLabel.setFont(new Font("Arial", Font.PLAIN,30));
		
		//////////////Next Row/////////////////////
		gc.gridy++;
		add(teamLabel = new JLabel(player.getPosition() + " - " + player.getTeam()), gc);
		teamLabel.setFont(new Font("Arial", Font.PLAIN,20));
		
		//////////////Next Row/////////////////////
		gc.gridy++;
		add(tablePane = new JScrollPane(table),gc);
		tablePane.setPreferredSize(new Dimension(1100,50));
		
		//////////////Next Row/////////////////////
		gc.gridy++;
		gc.insets = new Insets(0, 30, 10, 0);
		//Creation of Chart
		JFreeChart xylineChart = ChartFactory.createXYLineChart(
		         "Points Scored Compared to Average Points Allowed by Defense" ,
		         "Week" ,
		         "Points" ,
		         createDataset(player) ,
		         PlotOrientation.VERTICAL ,
		         true , true , false);
		add(playerChart = new ChartPanel(xylineChart),gc);
		final XYPlot plot = xylineChart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesStroke(0,new BasicStroke(1.0f));
		plot.setRenderer(renderer);
		playerChart.setPreferredSize(new Dimension(1100,400));

	}
	
	//Creates the dataset for the chart
	private XYDataset createDataset(Player player) {
		final XYSeries fantasyseries = new XYSeries("Fantasy Points");
		final XYSeries defenseseries = new XYSeries("Avg Defense Points Allowed");
		
		HashMap<String, Double> defensePoints = mainApplication.getDefense();
		
		for (int i=0;i<17;i++) {
			fantasyseries.add(i+1,player.getFantasyPoints(i));
			if(defensePoints.get(player.getOpponents(i)+player.getPosition()) != null) {
				defenseseries.add(i+1,defensePoints.get(player.getOpponents(i)+player.getPosition()));
			} else {
				defenseseries.add(i+1,0);
			}
			
		}
		
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(fantasyseries);
		dataset.addSeries(defenseseries);
	
		return dataset;
	}

}
