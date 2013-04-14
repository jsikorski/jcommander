package view.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.localization.Localization;

public class MenuBar extends JMenuBar {
	
	public MenuBar() {
		initializeComponents();
	}
	
	private void initializeComponents() {
		JMenu fileMenu = createFileMenu();
		add(fileMenu);
		
		JMenu languagesMenu = createLanguagesMenu();
		add(languagesMenu);
	}
	
	private JMenu createFileMenu() {
		JMenu fileMenu = new JMenu();
		Localization.setTextFor(fileMenu, "MenuBar_File");
		
		JMenuItem closeItem = new JMenuItem();
		Localization.setTextFor(closeItem, "MenuBar_File_Close");
		
		closeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		fileMenu.add(closeItem);
		
		return fileMenu;
	}
	
	private JMenu createLanguagesMenu() {
		final JMenu languagesMenu = new JMenu();
		Localization.setTextFor(languagesMenu, "MenuBar_Language");
		
		for (String language : Localization.getSupportedLanguages()) {
			final JMenuItem langItem = createLanguageItem(language);
			languagesMenu.add(langItem);
		}
		
		return languagesMenu;
	}

	private JMenuItem createLanguageItem(final String language) {
		final JMenuItem langItem = new JMenuItem();
		Localization.setTextFor(langItem, "MenuBar_Languages_" + language);
		
		langItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Localization.changeLanguage(language);
			}
		});
		
		return langItem;
	}
}
