package commands;

import java.io.File;

import model.ApplicationContext;
import model.ListingContext;
import events.DirectoryChanged;
import exceptions.CreateDirectoryFailureException;
import exceptions.DirectoryAlreadyExistsException;
import exceptions.DirectoryNameEmptyException;

public class CreateDirectory implements Command {

	private String name;
	private String baseDir;
	
	public CreateDirectory(String baseDir, String name) {
		this.baseDir = baseDir;
		this.name = name;
	}
	
	@Override
	public void execute() throws Exception {
		if (name.isEmpty()) {
			throw new DirectoryNameEmptyException();
		}
		
		File directory = new File(baseDir, name);
		if (directory.exists()) {
			throw new DirectoryAlreadyExistsException();
		}
		
		boolean success = directory.mkdir();
		if (!success) {
			throw new CreateDirectoryFailureException();
		}
		
		ApplicationContext.publishForContextsInDir(baseDir, new DirectoryChanged(baseDir));
	}

}
