package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		c.setFont(new Font("Arial", Font.PLAIN,15));
		if(row % 2 == 0) {
			c.setBackground(Color.WHITE);
		} else {
			c.setBackground(new Color(220,220,220));
		}
		if(column != 1) {
			((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
		}

		return c;
	}
}
