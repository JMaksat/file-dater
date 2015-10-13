package daterCore;

import java.io.File;
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
	
	public RenameFiles(ChooseForm chf) {
		this.chf = chf;
	}
	
	public void runRenameFiles() {
		prg = new ProgressBar(MainForm.TITLE, chf.getFilesNumber());
		
	    Thread t = new Thread(new Runnable() {
	    	private File soleFile;
	    	private String format;
	    	
	        public void run() {
	  	        for (int i = 0; i < chf.getFilesNumber(); i++) {
	  	        	// Stop button was pressed.
	  	        	if (prg.isStop()) {
	  	        		prg.dispose();
	  	        		break;
	  	        	}
		    	    soleFile = new File(chf.getFileName(i));
		    	    
		    	    // Making file name on chosen method.
		    	    if (MainForm.nameMethods[0] == MainForm.nameMethod) {
			        	format = chf.getCreationDate(i) + "_" + i;
			        } else if (MainForm.nameMethods[1] == MainForm.nameMethod) {
			        	format = chf.getCreationDate(i);
			        }

		    	    // Renaming files in dependency which format was chosen.
		    	    switch (MainForm.nameType) {
		    	    case "FIRST"   :
		    	    	soleFile.renameTo(new File(soleFile.getParent() + File.separator + format + "_" + soleFile.getName()));
		    	        break;
		    	    case "REPLACE" : 
		    	    	soleFile.renameTo(new File(soleFile.getParent() + File.separator + format +  
				    	    soleFile.getName().substring(soleFile.getName().length() - 4, soleFile.getName().length())));
		    	    	break;
		    	    case "LAST"    : 
		    	    	soleFile.renameTo(new File(soleFile.getParent() + File.separator + 
		    	    		soleFile.getName().substring(0, soleFile.getName().length() - 4) + "_" + format + 
		    	    		soleFile.getName().substring(soleFile.getName().length() - 4, soleFile.getName().length())));
		    	    	break;
		    	    default : 
		    	    	break;
		    	    }

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
