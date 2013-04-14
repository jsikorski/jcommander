package main;

import infrastructure.Startable;
import view.localization.Localization;
import view.ui.InterfaceStarter;

public class JCommander {
	
	public static void main(String[] args) {
		initializeLocalization();
		initializeUserInterface();
	}

	private static void initializeLocalization() {
		String[] supportedLanguages = { "pl", "en" };
		Localization.init("Localization", supportedLanguages);
	}
	
	private static void initializeUserInterface() {
		Startable interfaceStarter = new InterfaceStarter();
		interfaceStarter.start();
	}
}
