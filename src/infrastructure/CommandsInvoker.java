package infrastructure;

import commands.Command;

public class CommandsInvoker {
	public static void invoke(final Command command) {
		new Thread() {
			@Override
			public void run() {
				try {
					execute(command);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	}
	
	public static void execute(final Command command) {
		try {
			command.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
