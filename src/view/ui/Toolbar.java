package view.ui;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.utils.ExtensibleFlowLayout;

public class Toolbar extends JPanel {

	public Toolbar() {
		setLayout(new ExtensibleFlowLayout(FlowLayout.LEADING));		
		
		initializeComponents();
	}

	private void initializeComponents() {
		Icon icon = new ImageIcon("graphics/refresh.png");
		
		JButton refreshButton = new JButton(icon);
		refreshButton.setBorder(BorderFactory.createEmptyBorder());
		refreshButton.setContentAreaFilled(false);
		
		add(refreshButton);
	}
	
}
