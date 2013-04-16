package view.ui;

import infrastructure.EventAggregator;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import model.ListingContext;

public class ListingPanel extends JPanel {
	
	private ListingContext listingContext;
	private EventAggregator eventAggregator;

	public ListingPanel(ListingContext listingContext, EventAggregator eventAggregator) {
		this.listingContext = listingContext;
		this.eventAggregator = eventAggregator;
		
		setLayout(new BorderLayout());		
		initializeComponents();
	}

	private void initializeComponents() {
		add(new DrivesInfoToolbar(listingContext), BorderLayout.NORTH);
		add(new DirectoryInfoPanel(listingContext), BorderLayout.CENTER);		
	}
}