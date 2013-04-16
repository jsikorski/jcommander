package view.ui;

import infrastructure.EventHandler;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;

import events.Event;
import events.ListingContextChanged;

import view.localization.ContextChangeListener;
import view.localization.Localization;

import model.ApplicationContext;
import model.DirectoryContentModel;
import model.ListingContext;

public class DirectoryContent extends JTable implements EventHandler {
	private ListingContext listingContext;
	private DirectoryContentModel directoryContentModel;
	
	public DirectoryContent(final ListingContext listingContext) {
		this.listingContext = listingContext;
		
		directoryContentModel = new DirectoryContentModel(listingContext);
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
				
				int row = rowAtPoint(e.getPoint());
				if (SwingUtilities.isRightMouseButton(e)) {
					boolean isSelected = directoryContentModel.toggleSelectionFor(row);
					if (isSelected) {
						setRowSelectionInterval(row, row);
					}
					return;
				}
				
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
	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component component = super.prepareRenderer(renderer, row, column);
		if (directoryContentModel.getSelectedRowsIndexes().contains(row)) {
			component.setForeground(Color.RED);
		}
		else {
			component.setForeground(Color.BLACK);
		}
		
		return component;
	}
}
