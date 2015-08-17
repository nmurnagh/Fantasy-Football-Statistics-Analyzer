package gui.Dialogs;

import gui.TableModels.CustomTableRenderer;
import gui.TableModels.PlayerDialogTableModel;

import java.awt.Color;
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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

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
		setSize(new Dimension(1400,700));
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
		tablePane.setPreferredSize(new Dimension(1350,50));
		
		//////////////Next Row/////////////////////
		gc.gridy++;
		gc.insets = new Insets(0, 30, 10, 0);
		//Creation of Chart
		JFreeChart barChart = ChartFactory.createBarChart(
		         "Points Scored Compared to Average Points Allowed by Defense" ,
		         "Week" ,
		         "Points" ,
		         createDataset(player) ,
		         PlotOrientation.VERTICAL ,
		         true , true , false);
		final CategoryAxis domainAxis = barChart.getCategoryPlot().getDomainAxis();
        domainAxis.setMaximumCategoryLabelLines(2);
        
        final BarRenderer renderer = (BarRenderer) barChart.getCategoryPlot().getRenderer();
        renderer.setItemMargin(0.0f);
        renderer.setBarPainter(new StandardBarPainter());

		add(playerChart = new ChartPanel(barChart),gc);
		playerChart.setPreferredSize(new Dimension(1350,500));

	}
	
	//Creates the dataset for the chart
	private DefaultCategoryDataset createDataset(Player player) {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		final String series1 = "Fantasy Points";
		final String series2 = "Avg Defense Points Allowed";
		
		HashMap<String, Double> defensePoints = mainApplication.getDefense();
		
		for (int i=0;i<17;i++) {
			if(defensePoints.get(player.getOpponents(i)+player.getPosition()) != null) {
				dataset.addValue(player.getFantasyPoints(i), series1, "Wk" + (i+1) + " " + player.getOpponents(i));
				dataset.addValue(defensePoints.get(player.getOpponents(i)+player.getPosition()), series2, "Wk" + (i+1) + " " + player.getOpponents(i));
			} else {
				dataset.addValue(0, series1,"Wk" + (i+1) + " N/A");
				dataset.addValue(0, series2, "Wk" + (i+1) + " N/A");
			}
			
		}
	
		return dataset;
	}

}
