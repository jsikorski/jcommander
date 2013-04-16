package view.ui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import model.ListingContext;

public class ListingPanel extends JPanel {
	
	private ListingContext listingContext;
	public ListingPanel(ListingContext listingContext) {
		this.listingContext = listingContext;
		setLayout(new BorderLayout());		
		initializeComponents();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("AAA");
			}
		});
	}

	private void initializeComponents() {
		add(new DrivesInfoToolbar(listingContext), BorderLayout.NORTH);
		add(new DirectoryInfoPanel(listingContext), BorderLayout.CENTER);		
	}
}