package model;

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
		this.getEventAggregator().publish(new DirectoryChanged(currentPath));
	}

	public String getRootPath() {
		return getCurrentPath().split("\\\\")[0] + "\\";
	}

	public String getUpperLevelPath() {		
		if (getCurrentPath().equals(getRootPath())) {
			return getRootPath();
		}
		else {
			String[] parts = getCurrentPath().split("\\\\"); 
			String result = parts[0];
			
			for (int i = 1; i < parts.length - 1; i++) {
				result = result.concat("\\" + parts[i]);
			}
			
			return result;
		}
	}

	public EventAggregator getEventAggregator() {
		return eventAggregator;
	}
}