package model;

import java.awt.Component;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import view.localization.Localization;

public class TableDateRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if (value instanceof Date) {			
			JLabel label = (JLabel) component;
			label.setText(Localization.formatDate(((Date)value)));
			return label;
		}
		
		return component;
	}
}
