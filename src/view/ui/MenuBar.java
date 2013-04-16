package view.ui;

import infrastructure.CommandsInvoker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.localization.Localization;

import commands.Close;

public class MenuBar extends JMenuBar {
	
	public MenuBar() {
		initializeComponents();
	}
	
	private void initializeComponents() {
		JMenu fileMenu = createFileMenu();
		add(fileMenu);
		
		JMenu languagesMenu = createLanguagesMenu();
		add(languagesMenu);
		
		add(createEmptyMenu("MenuBar_Select"));
		add(createEmptyMenu("MenuBar_Commands"));
		add(createEmptyMenu("MenuBar_Network"));
		add(createEmptyMenu("MenuBar_View"));
		add(createEmptyMenu("MenuBar_Configuration"));
		add(createEmptyMenu("MenuBar_Start"));
		
		add(Box.createHorizontalGlue());
		
		add(createEmptyMenu("MenuBar_Help"));
	}
	
	private JMenu createFileMenu() {
		JMenu fileMenu = new JMenu();
		Localization.setTextFor(fileMenu, "MenuBar_File");
		
		JMenuItem closeItem = new JMenuItem();
		Localization.setTextFor(closeItem, "MenuBar_File_Close");
		
		closeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CommandsInvoker.execute(new Close());
			}
		});
		
		fileMenu.add(closeItem);
		
		return fileMenu;
	}
	
	private JMenu createLanguagesMenu() {
		JMenu languagesMenu = new JMenu();
		Localization.setTextFor(languagesMenu, "MenuBar_Language");
		
		for (String language : Localization.getSupportedLanguages()) {
			final JMenuItem langItem = createLanguageItem(language);
			languagesMenu.add(langItem);
		}
		
		return languagesMenu;
	}

	private JMenuItem createLanguageItem(final String language) {
		JMenuItem langItem = new JMenuItem();
		Localization.setTextFor(langItem, "MenuBar_Languages_" + language);
		
		langItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Localization.changeLanguage(language);
			}
		});
		
		return langItem;
	}
	
	private JMenu createEmptyMenu(String localizationKey) {
		JMenu menu = new JMenu();
		Localization.setTextFor(menu, localizationKey);
		return menu;
	}
}
