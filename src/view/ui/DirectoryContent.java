package view.ui;

import java.awt.Color;

import javax.swing.JTable;

import model.DirectoryContentModel;
import model.ListingContext;

public class DirectoryContent extends JTable {
	public DirectoryContent(ListingContext listingContext) {
		DirectoryContentModel directoryContentModel = new DirectoryContentModel(listingContext);
		setBackground(Color.WHITE);
		setModel(directoryContentModel);
	}
}
