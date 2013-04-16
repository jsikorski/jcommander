package commands;


public class Close implements Command {
	@Override
	public void execute() {
		System.exit(0);
	}
}
