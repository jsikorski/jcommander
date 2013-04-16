package view.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import view.localization.Localization;

public class MainWindow extends JFrame {
	public MainWindow() {
		Localization.setTextFor(this, "Common_Title", "setTitle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setPreferredSize(new Dimension(900, 800));
		
		setLayout(new BorderLayout());
		initializeComponents();
	}
	
	private void initializeComponents() {
		setJMenuBar(new MenuBar());
		
		Toolbar toolbar = new Toolbar();
		toolbar.pack();
		add(toolbar, BorderLayout.NORTH);
		
		add(new ContentPanel(), BorderLayout.CENTER);
		
		Footer footer = new Footer();
		add(footer, BorderLayout.SOUTH);		
	}
	
	public void present() {
		pack();
		setVisible(true);
	}
}