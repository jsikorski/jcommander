package view.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;

import javax.swing.JPanel;

public class ContentPanel extends JPanel {	
	public ContentPanel() {
		setLayout(new BorderLayout());
		initializeComponents();
	}
	
	private void initializeComponents() {
		setBackground(Color.red);
		
		Toolbar toolbar = new Toolbar();
		toolbar.setPreferredSize(new Dimension(0, 30));
		
		add(toolbar, BorderLayout.NORTH);
	}
}
