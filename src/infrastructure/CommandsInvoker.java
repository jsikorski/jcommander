package infrastructure;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import model.ApplicationContext;
import view.localization.Localization;

import commands.Command;

public class CommandsInvoker {
	public static void invoke(final Command command) {
		final JFrame progressBar = createProgressBar();
		new Thread() {
			@Override
			public void run() {
				progressBar.setVisible(true);
				execute(command);
				progressBar.dispose();
			}
		}.start();
	}
	
	public static void execute(final Command command) {
		try {
			command.execute();
		} catch (Exception e) {
			
			e.printStackTrace();
			
			String message; 
			
			try {
				String exceptionName = e.getClass().getSimpleName().replace("Exception", "");
				message = Localization.get("ErrorMessage_" + exceptionName); 
			} catch (Exception e2) {
				message = Localization.get("ErrorMessage_Unknown");
			}
			
			final String errorMessage = message;
			final String title = Localization.get("Common_Error");
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					JOptionPane.showMessageDialog(
							ApplicationContext.getMainWindow(), 
							errorMessage,
							title, 
							JOptionPane.ERROR_MESSAGE);
				}
			});
		}
	}
	
	public static JFrame createProgressBar() {
		JFrame frame = new JFrame(Localization.get("Common_WorkInProgress"));
		JFrame mainWindow = ApplicationContext.getMainWindow();
		frame.setLocation(
				mainWindow.getLocation().x + mainWindow.getWidth() / 2 - 150, 
				mainWindow.getLocation().y + mainWindow.getHeight() / 2 - 50);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    Container content = frame.getContentPane();
	    
	    JProgressBar progressBar = new JProgressBar();
	    progressBar.setValue(25);
	    progressBar.setStringPainted(true);
	    Border border = BorderFactory.createTitledBorder(Localization.get("Common_WorkInProgress"));
	    progressBar.setBorder(border);
	    
	    content.add(progressBar, BorderLayout.NORTH);
	    frame.setSize(300, 100);
	    return frame;
	}
}
