package commands;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;

public class EditFile implements Command {
	
	private File file;
	private String baseDir;
	private String newName;
	
	public EditFile(File file, String baseDir, String newName) {
		this.file = file;
		this.baseDir = baseDir;
		this.newName = newName;
	}
	
	@Override
	public void execute() throws Exception {
		File newFile = new File(baseDir, newName);
		if (newFile.exists()) {
			throw new FileAlreadyExistsException(newName);
		}
		
		boolean status = file.renameTo(newFile);
		if (!status) {
			throw new Exception();
		}
	}
}
