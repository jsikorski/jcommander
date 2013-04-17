package view.ui;

import infrastructure.CommandsInvoker;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.ApplicationContext;
import model.ListingContext;

import commands.Close;
import commands.CreateDirectory;
import commands.Delete;
import commands.Move;

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
		
		ActionListener moveActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String destinationPath = (String) JOptionPane.showInputDialog(
						ApplicationContext.getMainWindow(), 
						Localization.get("Move_Path"), 
						Localization.get("Move_Header"), 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						null, 
						ApplicationContext.getNotActiveListingContext().getCurrentPath());
				
				if (destinationPath == null) {
					return;
				}
				
				ListingContext listingContext = ApplicationContext.getActiveListingContext();
				CommandsInvoker.invoke(new Move(listingContext.getSelectedFiles(), destinationPath));
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
			emptyActionListener,
			moveActionListener,
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
