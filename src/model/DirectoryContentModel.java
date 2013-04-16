package model;

import infrastructure.CommandsInvoker;
import infrastructure.EventHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import view.localization.Localization;

import commands.ChangeDirectory;

import events.DirectoryChanged;
import events.Event;

public class DirectoryContentModel extends DefaultTableModel implements EventHandler {
	
	private List<File> content;
	private List<Object[]> contentMap;
	private final Class[] columnsTypes = new Class[] { String.class, Long.class, String.class };
	
	private ListingContext listingContext;
	
	public DirectoryContentModel(ListingContext listingContext) {
		this.listingContext = listingContext;
		File currentDir = new File(listingContext.getCurrentPath());
		updateContent(FilesHelper.getContentOf(currentDir));
		
		listingContext.getEventAggregator().subscribe(this, new Class[] { DirectoryChanged.class });
	}
	
	private void updateContent(File[] newContent) {
		content = new ArrayList<>(Arrays.asList(newContent));
		contentMap = DirectoryContentHelper.buildMapFor(newContent);
		
		if (listingContext.inRootDirectory()) {
			return;
		}
		
		content.add(0, new File(listingContext.getUpperLevelPath()) {
			@Override
			public boolean isDirectory() {
				return true;
			}
		});
		contentMap.add(0, new Object[] {"..", "", ""});
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
		return columnsTypes.length;
	}

	@Override
	public int getRowCount() {
		if (contentMap == null) {
			return 0;
		}
		
		return contentMap.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		return contentMap.get(row)[column];
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
		if (content.get(rowNumber).isDirectory()) {
			CommandsInvoker.invoke(new ChangeDirectory(listingContext, content.get(rowNumber).getPath()));
		}
	}
}
