package commands;

import java.io.File;
import java.util.List;

import model.ApplicationContext;

import org.apache.commons.io.FileUtils;

import events.DirectoryChanged;
import exceptions.DestinationPathAsFileException;
import exceptions.FileNotSelectedException;

public class Copy implements Command {

	private List<File> sourceFiles;
	private String destinationPath;

	public Copy(List<File> sourceFiles, String destinationPath) {
		this.sourceFiles = sourceFiles;
		this.destinationPath = destinationPath;
	}
	
	@Override
	public void execute() throws Exception {
		if (sourceFiles == null || sourceFiles.isEmpty()) {
			throw new FileNotSelectedException();
		}
		
		File destinationDir = new File(destinationPath);
		
		if (!destinationDir.isDirectory()) {
			throw new DestinationPathAsFileException();
		}
		
		for (File file : sourceFiles) {
			if (file.isDirectory()) {				
				FileUtils.copyDirectoryToDirectory(file, destinationDir);
			}
			else {
				FileUtils.copyFileToDirectory(file, destinationDir);
			}
			
			String sourceBaseDir = sourceFiles.get(0).getParent();
			ApplicationContext.publishForContextsInDir(sourceBaseDir, new DirectoryChanged(sourceBaseDir));
			ApplicationContext.publishForContextsInDir(destinationPath, new DirectoryChanged(destinationPath));
		}
	}
}
