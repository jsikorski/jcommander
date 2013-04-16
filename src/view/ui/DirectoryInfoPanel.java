package view.ui;

import infrastructure.EventHandler;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import events.DirectoryChanged;
import events.Event;

import model.ListingContext;

public class DirectoryInfoPanel extends JPanel implements EventHandler {
	
	private ListingContext listingContext;
	
	private JLabel directoryLabel;
	
	public DirectoryInfoPanel(ListingContext listingContext) {
		this.listingContext = listingContext;
		
		setLayout(new BorderLayout());
		initializeComponents();
		
		listingContext.getEventAggregator().subscribe(this, new Class[]{ DirectoryChanged.class });
	}

	private void initializeComponents() {
		directoryLabel = new JLabel(listingContext.getCurrentPath() + "*.*");
		directoryLabel.setBackground(Color.BLUE);
		directoryLabel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
		directoryLabel.setOpaque(true);
		directoryLabel.setForeground(Color.WHITE);
		add(directoryLabel, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane(new DirectoryContent(listingContext));
		add(scrollPane, BorderLayout.CENTER);
	}

	@Override
	public void handle(final Event event) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				directoryLabel.setText(((DirectoryChanged) event).getPath() + "*.*");
			}
		});
	}
}
