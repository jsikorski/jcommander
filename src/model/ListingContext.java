package model;

import java.io.File;

import events.DirectoryChanged;
import infrastructure.EventAggregator;

public class ListingContext {
	private String currentPath;
	private EventAggregator eventAggregator;
	
	public ListingContext() {
		this.eventAggregator = new EventAggregator();
	}
	
	public String getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
		
		File[] newContent = FilesHelper.getContentOf(new File(currentPath));
		this.getEventAggregator().publish(new DirectoryChanged(currentPath, newContent));
	}

	public String getRootPath() {
		return getCurrentPath().split("\\\\")[0] + "\\";
	}

	public String getUpperLevelPath() {		
		if (inRootDirectory()) {
			return getRootPath();
		}
		else {
			String[] parts = getCurrentPath().split("\\\\"); 
			String result = parts[0];
			
			for (int i = 1; i < parts.length - 1; i++) {
				result = result.concat("\\" + parts[i]);
			}
			
			if (result.endsWith(":")) {
				result = result + "\\";
			}
			
			return result;
		}
	}

	public EventAggregator getEventAggregator() {
		return eventAggregator;
	}

	public boolean inRootDirectory() {
		return getCurrentPath().equals(getRootPath());
	}
}