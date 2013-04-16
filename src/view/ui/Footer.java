package view.ui;

import infrastructure.CommandsInvoker;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.ApplicationContext;
import model.ListingContext;

import commands.Close;
import commands.CreateDirectory;
import commands.Delete;

import view.localization.Localization;

public class Footer extends JPanel {
	
	public Footer() {
		setLayout(new GridLayout(1, 7));
		initializeComponents();
	}

	private void initializeComponents() {
		
		ActionListener emptyActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		ActionListener moveRenameActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newName = JOptionPane.showInputDialog(
						ApplicationContext.getMainWindow(), 
						Localization.get("Edit_Name"), 
						Localization.get("Edit_Header"), 
						JOptionPane.QUESTION_MESSAGE);
				
				if (newName == null) {
					return;
				}
				
				ListingContext listingContext = ApplicationContext.getActiveListingContext();
				CommandsInvoker.invoke(new CreateDirectory(listingContext.getCurrentPath(), newName));
			}
		};
		
		ActionListener createDirActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dirName = JOptionPane.showInputDialog(
						ApplicationContext.getMainWindow(), 
						Localization.get("CreateDir_Name"), 
						Localization.get("CreateDir_Header"), 
						JOptionPane.QUESTION_MESSAGE);
				
				if (dirName == null) {
					return;
				}
				
				ListingContext listingContext = ApplicationContext.getActiveListingContext();
				CommandsInvoker.invoke(new CreateDirectory(listingContext.getCurrentPath(), dirName));
			}
		};
		
		ActionListener deleteActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListingContext listingContext = ApplicationContext.getActiveListingContext();
				CommandsInvoker.invoke(new Delete(listingContext.getSelectedFiles()));
			}
		};
		
		ActionListener[] actionListeners = new ActionListener[] {
			emptyActionListener,
			emptyActionListener,
			moveRenameActionListener,
			emptyActionListener,
			createDirActionListener,
			deleteActionListener
		};
		
		for (int i = 3; i < 9; i++) {
			add(createButton("Footer_F" + i, actionListeners[i - 3]));
		}
		
		ActionListener closeActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CommandsInvoker.execute(new Close());
			}
		};
		
		add(createButton("Footer_Alt+F4", closeActionListener));
	}
	
	private JButton createButton(String localizationKey, ActionListener actionListener) {
		JButton button = new JButton();
		Localization.setTextFor(button, localizationKey);
		button.addActionListener(actionListener);
		return button;
	}
}
