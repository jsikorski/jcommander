package commands;

import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import view.localization.Localization;

import model.ApplicationContext;

import events.DirectoryChanged;
import exceptions.FileNotSelectedException;

public class Delete implements Command {

	private List<File> files;

	public Delete(List<File> files) {
		this.files = files;		
	}
	
	@Override
	public void execute() throws Exception {
		if (files == null || files.isEmpty()) {
			throw new FileNotSelectedException();
		}
		
		for (File file : files) {
			String message = Localization.get("Delete_Confirmation").replace("{0}", file.getName());
			int result = JOptionPane.showConfirmDialog(
					ApplicationContext.getMainWindow(), 
					message, 
					Localization.get("Delete_Title"),
					JOptionPane.YES_NO_CANCEL_OPTION);
			
			if (result == JOptionPane.NO_OPTION) {
				continue;
			}
			
			if (result == JOptionPane.CANCEL_OPTION) {
				return;
			}
			
			if (file.isDirectory()) {
				message = Localization.get("Delete_DirConfirmation").replace("{0}", file.getName());
				result = JOptionPane.showConfirmDialog(
						ApplicationContext.getMainWindow(), 
						message, 
						Localization.get("Delete_Title"),
						JOptionPane.YES_NO_CANCEL_OPTION);
				
				if (result == JOptionPane.NO_OPTION) {
					continue;
				}
				
				if (result == JOptionPane.CANCEL_OPTION) {
					return;
				}
				
				FileUtils.deleteDirectory(file);
			}
			else {
				file.delete();
			}
		}
		
		String baseDir = files.get(0).getParent();
		ApplicationContext.publishForContextsInDir(baseDir, new DirectoryChanged(baseDir));
	}
}
