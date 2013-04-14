package view.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

import view.localization.Localization;

public class MainWindow extends JFrame {
	public MainWindow() {
		Localization.setTextFor(this, "Common_Title", "setTitle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension size = new Dimension(500, 500);
		setSize(size);
		
		initializeComponents();
	}
	
	private void initializeComponents() {
		setJMenuBar(new MenuBar());
		add(new ContentPanel());
	}
	
	public void present() {
		pack();
		setVisible(true);
	}
}