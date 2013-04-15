package view.ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.localization.Localization;

public class Footer extends JPanel {
	
	public Footer() {
		setLayout(new GridLayout(1, 7));
		initializeComponents();
	}

	private void initializeComponents() {
		for (int i = 3; i < 9; i++) {
			add(createButton("Footer_F" + i));
		}
		
		add(createButton("Footer_Alt+F4"));
	}
	
	private JButton createButton(String localizationKey) {
		JButton button = new JButton();
		Localization.setTextFor(button, localizationKey);
		return button;
	}
}
