package events;


public class DirectoryChanged implements Event {
	private String path;

	public DirectoryChanged(String path) {
		this.setPath(path);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
