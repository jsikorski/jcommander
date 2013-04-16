package model;

import java.util.Arrays;

import javax.swing.JFrame;

import events.Event;
import events.ListingContextChanged;

public class ApplicationContext {
	
	private static JFrame mainWindow;
	private static ListingContext[] listingContexts;
	private static ListingContext activeListingContext;
	
	public static void setListingContexts(ListingContext[] listingContexts) {
		ApplicationContext.listingContexts = listingContexts;
	}
	
	public static ListingContext getActiveListingContext() {
		return activeListingContext;
	}
	
	public static void setActiveListingContext(ListingContext listingContext) {
		if (listingContexts == null || !Arrays.asList(listingContexts).contains(listingContext)) {
			throw new IllegalArgumentException();
		}
		
		ListingContext oldListingContext = activeListingContext;
		activeListingContext = listingContext;
		
		if (oldListingContext == null || !oldListingContext.equals(activeListingContext)) {
			if (oldListingContext != null) {
				oldListingContext.getEventAggregator().publish(new ListingContextChanged(activeListingContext));
			}
			
			listingContext.getEventAggregator().publish(new ListingContextChanged(activeListingContext));
		}
	}
	
	public static void publishForContextsInDir(String dirPath, Event event) {
		for (ListingContext listingContext : listingContexts) {
			if (listingContext.getCurrentPath().equals(dirPath)) {
				listingContext.getEventAggregator().publish(event);
			}
		}
	}

	public static JFrame getMainWindow() {
		return mainWindow;
	}

	public static void setMainWindow(JFrame mainWindow) {
		ApplicationContext.mainWindow = mainWindow;
	}
}
