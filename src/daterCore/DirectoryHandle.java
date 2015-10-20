package daterCore;

import java.io.File;
import java.nio.file.Path;

/**
 * Variables and methods to work with files.
 * 
 * @author Maksat E.
 */

public class DirectoryHandle {

	private static File    currentPath;
	private static Path[]  filesArray;
	private static int     filesNumber = 0;
	
	public static File setPath(File dir) {
		return currentPath = dir;
	}
	
	public static File getPath() {
		return currentPath;
	}
	
	public static void initFileArray(int sz) {
		filesArray = new Path[sz];
	}
	
	public static void addFileName(Path fileName) {
		
		filesArray[filesNumber] = fileName;
		filesNumber++;
	}
	
	public static Path getFileName(int number) {
		return filesArray[number];
	}
	
	public static int getFilesNumber() {
		return filesNumber - 1;
	}
	
	public static void cleanArrays() {
		filesArray = null;
		filesNumber = 0;
	}
}
