package daterCore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * Progress bar class with STOP button.
 * 
 * @author Maksat E.
 */

@SuppressWarnings("serial")
public class ProgressBar extends JDialog{
	private JProgressBar progressBar;
	private JPanel       cPanel;
	private JButton      stopButton;
	private Boolean      stop = false;
	
	
	public ProgressBar(String title, int fileCnt) {
		
		cPanel = new JPanel();
		cPanel.setLayout(null);
		
		this.setTitle(title + " - Renaming progress");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(325, 130);
		this.setResizable(false);
		this.setModalityType(DEFAULT_MODALITY_TYPE);
		this.setLocationRelativeTo(null);
		
	    progressBar = new JProgressBar(0, fileCnt);
	    progressBar.setValue(0);
	    progressBar.setStringPainted(true);
	    progressBar.setBounds(10, 10, 300, 30);
	    cPanel.add(progressBar);
	    
	    stopButton = new JButton("Stop");
	    stopButton.setBounds(119, 50, 80, 32);
	    stopButton.setEnabled(true);
	    cPanel.add(stopButton);
	    
	    getContentPane().add(cPanel);
	    
	    stopButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent event) {
	    		stop = true;
	    	}
	    });
	}
	
	public void setProgress(int progress) {
		progressBar.setValue(progress);
	}
	
	public Boolean isStop() {
		return stop;
	}
}
