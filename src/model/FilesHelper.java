package model;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FilesHelper {
	public static File[] getContentOf(File directory) {		
		if (!(directory.isDirectory())) {
			throw new IllegalArgumentException();
		}
		
		File[] content = directory.listFiles(new FilesAndDirsFilter());
		Arrays.sort(content, new Comparator<File>() {
			@Override
			public int compare(File f1, File f2) {
				if (f1.isDirectory() && !f2.isDirectory()) {
					return -1;
				}
				
				if (f2.isDirectory() && !f1.isDirectory()) {
					return 1;
				}
				
				return f1.compareTo(f2);
			}
		});
		
		return content;
	}
	
	public static boolean isRoot(File file) {
		return file.toPath().endsWith(":"); 
	}
}
