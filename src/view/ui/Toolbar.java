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
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import view.utils.FlexibleFlowLayout;

public class Toolbar extends JToolBar {

	private static final int ICON_SIZE = 30;
	private static final int BUTTON_SIZE = ICON_SIZE + 2;	
	private static final int ICONS_MARGIN = 2;
	
	private static final String GRAPHICS_REFRESH = "graphics/refresh.png";

	public Toolbar() {
		setLayout(new FlexibleFlowLayout(FlowLayout.LEADING, ICONS_MARGIN, ICONS_MARGIN));
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
		initializeComponents();
	}
	
	private void initializeComponents() {
		addButton(GRAPHICS_REFRESH);
		JSeparator jSeparator = new JSeparator(SwingConstants.VERTICAL);
		jSeparator.setBorder(BorderFactory.createLineBorder(Color.RED));
		jSeparator.setPreferredSize(new Dimension(5, getHeight()));
		add(jSeparator);
		addButton(GRAPHICS_REFRESH);
	}
	
	private void addButton(String imagePath) {
		Icon icon = new ImageIcon(imagePath);	
		final JButton button = new JButton(icon);
		
		button.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				button.setBorderPainted(true);
				button.setContentAreaFilled(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBorderPainted(false);
				button.setContentAreaFilled(false);
			}
		});
		
		add(button);
	}
	
	public void pack() {
		setPreferredSize(new Dimension(0, BUTTON_SIZE + ICONS_MARGIN * 2));
	}
}
