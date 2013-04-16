package view.ui;

import javax.swing.JSplitPane;

import model.ApplicationContext;
import model.ListingContext;

public class ContentPanel extends JSplitPane {	
	public ContentPanel() {
		setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		setDividerLocation(0.5);
		setResizeWeight(0.5);
        setContinuousLayout(true);
        
		initializeComponents();
	}
	
	private void initializeComponents() {
				
		ListingContext leftListingContext = new ListingContext();
		ListingContext rightListingContext = new ListingContext();
		
		ListingPanel left = new ListingPanel(leftListingContext);
		ListingPanel right = new ListingPanel(rightListingContext);
		
		ApplicationContext.setActiveListingContext(leftListingContext);
		
		add(left, JSplitPane.LEFT);
		add(right, JSplitPane.RIGHT);
	}
}
