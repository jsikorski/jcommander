package model;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;

public class FilesAndDirsFilter implements FileFilter {

	@Override
	public boolean accept(File pathname) {			return Files.isReadable(pathname.toPath()) &&  !Files.isSymbolicLink(pathname.toPath());
	}

}
