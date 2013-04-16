package commands;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;

import exceptions.CreateDirectoryFailureException;
import exceptions.DirectoryAlreadyExistsException;
import exceptions.DirectoryNameEmptyException;

import model.ListingContext;

public class CreateDirectory implements Command {

	private ListingContext listingContext;
	private String name;
	
	public CreateDirectory(ListingContext listingContext, String name) {
		this.listingContext = listingContext;
		this.name = name;
	}
	
	@Override
	public void execute() throws Exception {
		if (name.equals("")) {
			throw new DirectoryNameEmptyException();
		}
		
		String path = listingContext.getCurrentPath();
		
		File directory = new File(path, name);
		if (directory.exists()) {
			throw new DirectoryAlreadyExistsException();
		}
		
		boolean success = directory.mkdir();
		if (!success) {
			throw new CreateDirectoryFailureException();
		}
		
		new ChangeDirectory(listingContext, path).execute();
	}

}
