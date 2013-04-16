package view.ui;

import infrastructure.EventAggregator;

import javax.swing.JSplitPane;

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
				
		ListingPanel left = new ListingPanel(new ListingContext(), new EventAggregator());
		ListingPanel right = new ListingPanel(new ListingContext(), new EventAggregator());
		
		add(left, JSplitPane.LEFT);
		add(right, JSplitPane.RIGHT);
	}
}
