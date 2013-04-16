package view.ui;

import infrastructure.EventHandler;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JTable;

import events.Event;
import events.ListingContextChanged;

import view.localization.ContextChangeListener;
import view.localization.Localization;

import model.ApplicationContext;
import model.DirectoryContentModel;
import model.ListingContext;

public class DirectoryContent extends JTable implements EventHandler {
	private ListingContext listingContext;

	public DirectoryContent(final ListingContext listingContext) {
		this.listingContext = listingContext;
		
		final DirectoryContentModel directoryContentModel = new DirectoryContentModel(listingContext);
		setBackground(Color.WHITE);
		setModel(directoryContentModel);
		setShowGrid(false);
		
		Localization.addChangeListener(new ContextChangeListener() {
			@Override
			public void contextChanged() {
				directoryContentModel.fireTableStructureChanged();
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ApplicationContext.setActiveListingContext(listingContext);
				
				if (e.getClickCount() == 2) {
					directoryContentModel.navigateTo(getSelectedRow());
				}
			}
		});
		
		listingContext.getEventAggregator().subscribe(this, new Class[] { ListingContextChanged.class });
	}

	@Override
	public void handle(Event event) {
		if (((ListingContextChanged) event).getNewContext().equals(listingContext)) {
			setBorder(BorderFactory.createLineBorder(Color.BLUE));
		}
		else {
			setBorder(BorderFactory.createEmptyBorder());
		}
	}
}
