package view.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.utils.FlexibleFlowLayout;

public class Toolbar extends JPanel {

	private static final int ICON_SIZE = 30;
	private static final int BUTTON_SIZE = ICON_SIZE + 2;	
	private static final int ICONS_MARGIN = 2;
	
	private static final String GRAPHICS_REFRESH = "graphics/refresh.png";

	public Toolbar() {
		setLayout(new FlexibleFlowLayout(FlowLayout.LEADING, ICONS_MARGIN, ICONS_MARGIN));		
		initializeComponents();
	}
	
	private void initializeComponents() {
		addButton(GRAPHICS_REFRESH);
	}
	
	private void addButton(String imagePath) {
		Icon icon = new ImageIcon(imagePath);	
		final JButton refreshButton = new JButton(icon);
		
		refreshButton.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
		refreshButton.setBorderPainted(false);
		refreshButton.setContentAreaFilled(false);
		
		refreshButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				refreshButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				refreshButton.setBorderPainted(true);
				refreshButton.setContentAreaFilled(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				refreshButton.setBorderPainted(false);
				refreshButton.setContentAreaFilled(false);
			}
		});
		
		add(refreshButton);
	}
	
	public void pack() {
		setPreferredSize(new Dimension(0, BUTTON_SIZE + ICONS_MARGIN * 2));
	}
}
