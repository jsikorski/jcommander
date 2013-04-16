package view.ui;

import infrastructure.Startable;

import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.ApplicationContext;

import view.localization.Localization;

public class InterfaceStarter implements Startable {
	
	@Override
	public void start() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initUI();
            }
        });
	}

	protected void initUI() {
		MainWindow mainWindow = new MainWindow();
		
		try {
			setLookAndFeel();
		} catch (Exception e) {
			String message = Localization.get("Common_CannotInitializeInterface");
			JOptionPane.showMessageDialog(mainWindow, message);
		}
		
		ApplicationContext.setMainWindow(mainWindow);
        mainWindow.present();
	}

	private void setLookAndFeel() throws UnsupportedLookAndFeelException {
		LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
		UIManager.setLookAndFeel(lookAndFeel);
	}
}
