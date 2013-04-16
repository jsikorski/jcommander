package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributeView;

public class DirectoryContentHelper {
	public static Object[][] buildMapFor(File[] files) {
		Object[][] result = new Object[files.length][3];
		
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			result[i][0] = file.getName();
			
			if (file.isDirectory()) {
				result[i][1] = "<DIR>";
			}
			else {
				result[i][1] = file.length() / 1024;
			}
			
			try {
				result[i][2] = Files.getFileAttributeView(file.toPath(), BasicFileAttributeView.class).readAttributes().creationTime().toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
