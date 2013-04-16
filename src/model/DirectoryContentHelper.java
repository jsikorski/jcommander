package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributeView;
import java.util.ArrayList;
import java.util.List;

public class DirectoryContentHelper {
	public static List<Object[]> buildMapFor(File[] files) {
		List<Object[]> result = new ArrayList<>();
		
		for (int i = 0; i < files.length; i++) {	
			File file = files[i];
			Object[] fileInfo = new Object[3];
			
			fileInfo[0] = file.getName();
			
			if (file.isDirectory()) {
				fileInfo[1] = "<DIR>";
			}
			else {
				fileInfo[1] = file.length() / 1024;
			}
			
			try {
				fileInfo[2] = Files.getFileAttributeView(file.toPath(), BasicFileAttributeView.class).readAttributes().creationTime().toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			result.add(fileInfo);
		}
		
		return result;
	}
}
