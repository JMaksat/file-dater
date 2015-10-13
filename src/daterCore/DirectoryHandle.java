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
	private static String[]  datesArray;
	private static int     filesNumber = 0;
	
	public static File setPath(File dir) {
		return currentPath = dir;
	}
	
	public static File getPath() {
		return currentPath;
	}
	
	public static void initFileArray(int sz) {
		filesArray = new Path[sz];
		datesArray = new String[sz];
	}
	
	public static void addFileName(Path fileName) {
		
		filesArray[filesNumber] = fileName;
		filesNumber++;
	}
	
	public static Path getFileName(int number) {
		return filesArray[number];
	}
	
	// This method has to be executed before the getFileName method.
	public static void addCreationDate(String dateCreated) {
		
		datesArray[filesNumber] = dateCreated;
	}
	
	public static String getCreationDate(int number) {
		return datesArray[number];
	}	
	
	public static int getFilesNumber() {
		return filesNumber - 1;
	}
	
	public static void cleanArrays() {
		filesArray = null;
		datesArray = null;
		filesNumber = 0;
	}
}
