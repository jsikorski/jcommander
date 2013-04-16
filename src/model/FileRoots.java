package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FileRoots {
	public static File[] getAll() {
		ArrayList<File> fileRoots = new ArrayList<File>(Arrays.asList(File.listRoots()));
		File aRoot = null;
		
		for (File root : fileRoots) {
			if (root.toString().equals("A:\\")) {
				aRoot = root;
			}
		}
		
		if (aRoot != null) {
			fileRoots.remove(aRoot);
		}
		
		return fileRoots.toArray(new File[fileRoots.size()]);
	}
}
