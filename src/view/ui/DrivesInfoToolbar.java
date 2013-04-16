package view.ui;

import infrastructure.CommandsInvoker;
import infrastructure.EventAggregator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import commands.ChangeDirectory;

import model.FileRoots;
import model.ListingContext;

import view.localization.Localization;

public class DrivesInfoToolbar extends JPanel {
	
	private ListingContext listingContext;
	
	private JComboBox<File> roots;
	private JLabel driveDescription;
	private JPanel navigationButtons;
	
	public DrivesInfoToolbar(ListingContext listingContext) {
		this.listingContext = listingContext;
		
		setLayout(new BorderLayout());
		initializeComponents();
	}
	
	private void initializeComponents() {
		File[] fileRoots = FileRoots.getAll();
		File selectedItem = fileRoots[0];
		listingContext.setCurrentPath(selectedItem.getAbsolutePath());
		
		initializeDriveDescription(selectedItem);
		initializeDrives(fileRoots, selectedItem);
		navigationButtons();
	}	

	private void initializeDrives(File[] fileRoots, File selectedItem) {
		roots = new JComboBox<>(fileRoots);
		roots.setSelectedItem(selectedItem);		
		roots.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File root = (File) roots.getSelectedItem();
				setDriveDescription(root);
				CommandsInvoker.invoke(new ChangeDirectory(listingContext, root.getAbsolutePath()));
			}		
		});
		
		add(roots, BorderLayout.WEST);
	}
	
	private void setDriveDescription(File selectedItem) {
		String[] arguments = new String[] {
			FileSystemView.getFileSystemView().getSystemTypeDescription(selectedItem),
			String.valueOf(selectedItem.getFreeSpace() / 1024),
			String.valueOf(selectedItem.getTotalSpace() / 1024)
		};
		Localization.setTextFor(driveDescription, "DrivesInfo_Description", arguments);
	}

	private void initializeDriveDescription(File selectedItem) {
		driveDescription = new JLabel();
		setDriveDescription(selectedItem);
		driveDescription.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		add(driveDescription, BorderLayout.CENTER);
	}
	
	private void navigationButtons() {
		navigationButtons = new JPanel();
		navigationButtons.setLayout(new FlowLayout());
		
		JButton rootButton = new JButton("\\", null);
		rootButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CommandsInvoker.invoke(new ChangeDirectory(listingContext, listingContext.getRootPath()));	
			}
		});
		navigationButtons.add(rootButton);
		
		JButton upperLevelButton = new JButton("..", null);
		upperLevelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CommandsInvoker.invoke(new ChangeDirectory(listingContext, listingContext.getUpperLevelPath()));
			}
		});
		
		navigationButtons.add(upperLevelButton);
		
		add(navigationButtons, BorderLayout.EAST);
	}
}
