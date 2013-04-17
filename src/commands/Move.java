package commands;

import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

import model.ApplicationContext;

import org.apache.commons.io.FileUtils;

import events.DirectoryChanged;
import exceptions.FileNotSelectedException;

import view.localization.Localization;

public class Move implements Command {
	
	private List<File> sourceFiles;
	private String destinationPath;
	
	public Move(List<File> sourceFiles, String destinationPath) {
		this.sourceFiles = sourceFiles;
		this.destinationPath = destinationPath;
	}
	
	@Override
	public void execute() throws Exception {
		if (sourceFiles == null || sourceFiles.isEmpty()) {
			throw new FileNotSelectedException();
		}
		
		File destinationFile = new File(destinationPath);
		
		for (File file : sourceFiles) {
			if (file.isDirectory()) {
				String message = Localization.get("Move_DirConfirmation").replace("{0}", file.getName());
				int result = JOptionPane.showConfirmDialog(
						ApplicationContext.getMainWindow(), 
						message, 
						Localization.get("Move_Title"),
						JOptionPane.YES_NO_CANCEL_OPTION);
				
				if (result == JOptionPane.NO_OPTION) {
					continue;
				}
				
				if (result == JOptionPane.CANCEL_OPTION) {
					return;
				}
				
				if (destinationFile.isDirectory()) {
					FileUtils.moveDirectoryToDirectory(file, destinationFile, true);
				} else {
					FileUtils.moveDirectory(file, destinationFile);
				}
			}
			else {
				if (destinationFile.isDirectory()) {
					FileUtils.moveFileToDirectory(file, destinationFile, true);
				} else {
					FileUtils.moveFile(file, destinationFile);
				}
			}
			
			String sourceBaseDir = sourceFiles.get(0).getParent();
			ApplicationContext.publishForContextsInDir(sourceBaseDir, new DirectoryChanged(sourceBaseDir));
			ApplicationContext.publishForContextsInDir(destinationPath, new DirectoryChanged(destinationPath));
		}
	}
}
