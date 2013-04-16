package model;

import infrastructure.EventHandler;

import javax.swing.table.AbstractTableModel;

import events.DirectoryChanged;
import events.Event;

public class DirectoryContentModel extends AbstractTableModel implements EventHandler {

	public DirectoryContentModel(ListingContext listingContext) {
		listingContext.getEventAggregator().subscribe(this, new Class[] { DirectoryChanged.class });
	}
	
	@Override
	public String getColumnName(int column) {
		return "BBB";
	};
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getValueAt(int row, int column) {
		
		return "aaa";
	}

	@Override
	public void handle(Event event) {
		
		
	}

}
