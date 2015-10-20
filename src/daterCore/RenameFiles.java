package daterCore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

/**
 * Renaming the files.
 * 
 * @author Maksat E.
 */

public class RenameFiles {
	private ProgressBar prg;
	private ChooseForm  chf;
	private int renamedFilesCount = 0;
	private String formatDate;
	private BasicFileAttributes aAttrs; 
	
	public RenameFiles(ChooseForm chf) {
		this.chf = chf;
	}
	
	public void runRenameFiles() {
		prg = new ProgressBar(MainForm.TITLE, chf.getFilesNumber());
		
    	// Making date format
        if (MainForm.nameMethods[0] == MainForm.nameMethod) {
        	formatDate = "yyyy_MM_dd_HH_mm_ss";
        } else if (MainForm.nameMethods[1] == MainForm.nameMethod) {
        	formatDate = "yyyy_MM_dd_HH_mm_ss_SSS";
        }
		
	    Thread t = new Thread(new Runnable() {
	    	private File soleFile;
	    	private String format;
	    	private String newName;
	    	
	        public void run() {
	  	        for (int i = 0; i < chf.getFilesNumber(); i++) {
	  	        	// Stop button was pressed.
	  	        	if (prg.isStop()) {
	  	        		prg.dispose();
	  	        		break;
	  	        	}
		    	    soleFile = new File(chf.getFileName(i));
		    	    
			        // Converting date to string, which have taken from file attributes
			        try {
						aAttrs = Files.readAttributes(soleFile.toPath(), BasicFileAttributes.class);
					} catch (IOException e) {
						e.printStackTrace();
					}
			        FileTime   date        = aAttrs.lastModifiedTime();
			        DateFormat df          = new SimpleDateFormat(formatDate);
			        String     dateCreated = df.format(date.toMillis());		    	    
		    	    
		    	    // Making file name on chosen method.
		    	    if (MainForm.nameMethods[0] == MainForm.nameMethod) {
			        	format = dateCreated + "_" + i;
			        } else if (MainForm.nameMethods[1] == MainForm.nameMethod) {
			        	format = dateCreated;
			        }

		    	    // Renaming files in dependency which format was chosen.
		    	    switch (MainForm.nameType) {
		    	    case "FIRST"   :
		    	    	newName = format + "_" + soleFile.getName().substring(0, soleFile.getName().length() - 4);
		    	    	newName = newName.substring(0, Math.min(newName.length(), 251)) +
		    	    		soleFile.getName().substring(soleFile.getName().length() - 4, soleFile.getName().length());
		    	        break;
		    	    case "REPLACE" : 
		    	    	newName = format +  
				    	    soleFile.getName().substring(soleFile.getName().length() - 4, soleFile.getName().length());
		    	    	break;
		    	    case "LAST"    : 
		    	    	newName = soleFile.getName().substring(0, soleFile.getName().length() - 4);
		    	    	newName = newName.substring(0, Math.min(newName.length(), (250 - format.length()))) + "_" + format +
		    	    		soleFile.getName().substring(soleFile.getName().length() - 4, soleFile.getName().length());
		    	    	System.out.println(newName);
		    	    	break;
		    	    default : 
		    	    	break;
		    	    }

		    	    soleFile.renameTo(new File(soleFile.getParent() + File.separator + newName));
		    	    
	                prg.setProgress(i);
	                renamedFilesCount++;
	                /*try {
	                    Thread.sleep(1);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }*/              
		        }
	  	        prg.dispose();
	        }
	    });
	    
	    t.start();
	    prg.setVisible(true);
		
		JOptionPane.showMessageDialog(null, renamedFilesCount + " files renamed!", MainForm.TITLE, JOptionPane.PLAIN_MESSAGE);
	} 	
}
