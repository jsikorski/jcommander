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

import view.localization.Localization;

public class Footer extends JPanel {
	
	public Footer() {
		setLayout(new GridLayout(1, 7));
		initializeComponents();
	}

	private void initializeComponents() {
		
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
				CommandsInvoker.invoke(new CreateDirectory(listingContext, dirName));
			}
		};
		
		
		
		for (int i = 3; i < 9; i++) {
			add(createButton("Footer_F" + i, createDirActionListener));
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
