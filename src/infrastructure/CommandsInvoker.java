package infrastructure;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import view.localization.Localization;

import model.ApplicationContext;

import commands.Command;

public class CommandsInvoker {
	public static void invoke(final Command command) {
		new Thread() {
			@Override
			public void run() {
				execute(command);
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
}
