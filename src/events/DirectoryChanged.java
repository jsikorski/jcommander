package events;

import java.io.File;

import model.FilesHelper;


public class DirectoryChanged implements Event {
	private String path;
	private File[] newDirContent;

	public DirectoryChanged(String path) {
		this.path = path;
		this.newDirContent = FilesHelper.getContentOf(new File(path));
	}

	public String getPath() {
		return path;
	}

	public File[] getNewDirContent() {
		return newDirContent;
	}
}
