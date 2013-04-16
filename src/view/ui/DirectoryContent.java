package view.ui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import view.localization.ContextChangeListener;
import view.localization.Localization;

import model.DirectoryContentModel;
import model.ListingContext;

public class DirectoryContent extends JTable {
	public DirectoryContent(ListingContext listingContext) {
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
				if (e.getClickCount() == 2) {
					directoryContentModel.navigateTo(getSelectedRow());
				}
			}
		});
	}
}
