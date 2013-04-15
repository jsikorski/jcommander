package view.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class ContentPanel extends JPanel {	
	public ContentPanel() {
		setLayout(new BorderLayout());
		initializeComponents();
	}
	
	private void initializeComponents() {
		setBackground(Color.red);
		
		Toolbar toolbar = new Toolbar();
		toolbar.pack();
		add(toolbar, BorderLayout.NORTH);
		
		Footer footer = new Footer();
		add(footer, BorderLayout.SOUTH);
	}
}
