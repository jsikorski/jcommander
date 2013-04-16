package events;

import java.io.File;


public class DirectoryChanged implements Event {
	private String path;
	private File[] newDirContent;

	public DirectoryChanged(String path, File[] newDirContent) {
		this.path = path;
		this.newDirContent = newDirContent;
	}

	public String getPath() {
		return path;
	}

	public File[] getNewDirContent() {
		return newDirContent;
	}
}
