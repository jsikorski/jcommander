package model;

import javax.swing.JFrame;

import events.ListingContextChanged;

public class ApplicationContext {
	
	private static JFrame mainWindow;
	private static ListingContext activeListingContext;
	
	public static ListingContext getActiveListingContext() {
		return activeListingContext;
	}
	
	public static void setActiveListingContext(ListingContext listingContext) {
		ListingContext oldListingContext = activeListingContext;
		activeListingContext = listingContext;
		
		if (oldListingContext == null || !oldListingContext.equals(activeListingContext)) {
			if (oldListingContext != null) {
				oldListingContext.getEventAggregator().publish(new ListingContextChanged(activeListingContext));
			}
			
			listingContext.getEventAggregator().publish(new ListingContextChanged(activeListingContext));
		}
	}

	public static JFrame getMainWindow() {
		return mainWindow;
	}

	public static void setMainWindow(JFrame mainWindow) {
		ApplicationContext.mainWindow = mainWindow;
	}
}
