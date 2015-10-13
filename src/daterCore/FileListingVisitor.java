package daterCore;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.File;

/**
 * Creates frame and set its properties.
 * 
 * @author Maksat E.
 */

public final class FileListingVisitor {
  
    public static void runFileListingVisitor(String root) throws IOException{
	    DirectoryHandle.initFileArray(countFilesInDirectory(new File(root)));
        FileVisitor<Path> fileProcessor = new ProcessFile();
        Files.walkFileTree(Paths.get(root), fileProcessor);
    }
  
    public static int countFilesInDirectory(File directory) {
        int count = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                count++;
            }
            if (file.isDirectory()) {
              count += countFilesInDirectory(file);
            }
        }
        return count;
    }

    private static final class ProcessFile extends SimpleFileVisitor<Path> {
    	private String format;
    	
        @Override 
        public FileVisitResult visitFile(Path aFile, BasicFileAttributes aAttrs) throws IOException {
	        if (MainForm.nameMethods[0] == MainForm.nameMethod) {
	        	format = "yyyy_MM_dd_HH_mm_ss";
	        } else if (MainForm.nameMethods[1] == MainForm.nameMethod) {
	        	format = "yyyy_MM_dd_HH_mm_ss_SSS";
	        }
        	
	        aAttrs                 = Files.readAttributes(aFile, BasicFileAttributes.class);
	        FileTime   date        = aAttrs.creationTime();
	        DateFormat df          = new SimpleDateFormat(format);
	        String     dateCreated = df.format(date.toMillis());
	        
	        DirectoryHandle.addCreationDate(dateCreated);
	        DirectoryHandle.addFileName(aFile);
	  
            return FileVisitResult.CONTINUE;
        }
    
        @Override  
        public FileVisitResult preVisitDirectory(Path aDir, BasicFileAttributes aAttrs) throws IOException {
            //System.out.println("Processing directory:" + aDir);
            return FileVisitResult.CONTINUE;
        }
  }

}