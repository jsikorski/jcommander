package model;

import infrastructure.CommandsInvoker;
import infrastructure.EventHandler;

import java.io.File;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import view.localization.Localization;

import commands.ChangeDirectory;

import events.DirectoryChanged;
import events.Event;

public class DirectoryContentModel extends DefaultTableModel implements EventHandler {
	
	private File[] content;
	private Object[][] contentMap;
	private final Class[] columnsTypes = new Class[] { String.class, Long.class, String.class };
	
	private ListingContext listingContext;
	
	public DirectoryContentModel(ListingContext listingContext) {
		this.listingContext = listingContext;
		File currentDir = new File(listingContext.getCurrentPath());
		updateContent(FilesHelper.listFor(currentDir));
		
		listingContext.getEventAggregator().subscribe(this, new Class[] { DirectoryChanged.class });
	}
	
	private void updateContent(File[] newContent) {
		content = newContent;
		contentMap = DirectoryContentHelper.buildMapFor(newContent);
	}
	
	@Override
	public String getColumnName(int column) {
		return getColumnsNames()[column];
	};
	
	private String[] getColumnsNames() {
		return new String[] {
			Localization.get("DirectoryContent_Headers_Name"),
			Localization.get("DirectoryContent_Headers_Size"),
			Localization.get("DirectoryContent_Headers_Date")
		};
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnsTypes[columnIndex];
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		if (contentMap == null) {
			return 0;
		}
		
		return contentMap.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return contentMap[row][column];
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public void handle(final Event event) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				File[] newContent = ((DirectoryChanged) event).getNewDirContent();
				updateContent(newContent);
				fireTableDataChanged();
			}
		});
	}

	public void navigateTo(int rowNumber) {
		if (content[rowNumber].isDirectory()) {
			CommandsInvoker.invoke(new ChangeDirectory(listingContext, content[rowNumber].getPath()));
		}
	}
}
