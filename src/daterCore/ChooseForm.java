package daterCore;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates frame with two lists.
 * 
 * @author Maksat E.
 */

@SuppressWarnings("serial")
public class ChooseForm extends JDialog {
	private JPanel           cPanel;
	private JScrollPane      leftScrollPane;
	private JScrollPane      rightScrollPane;
	private JButton          toLeftButton;
	private JButton          allLeftButton;
	private JButton          toRightButton;
	private JButton          allRightButton;
	private JButton          okButton;
	private JButton          cancelButton;
	private JLabel           leftLabel;
	private JLabel           rightLabel;
	private ChooseForm       curForm;

	private JList<String>    leftList;
	private JList<String>    rightList;
	private DefaultListModel<String> modelList;
	private String[]         arrayList;
	private String[]         arrayDates;
	
	private final String     TITLE;
	private final int        VISIBLE_ROW_COUNT = 10;
	
	private int k = 0;
	
	public ChooseForm() {
	
		this.TITLE = MainForm.TITLE;
		
		curForm = this;
		
		cPanel = new JPanel();
		cPanel.setLayout(null);
		
		this.setTitle(TITLE + " - Choose files to rename");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(640, 480);
		this.setResizable(false);
		this.setModalityType(DEFAULT_MODALITY_TYPE);
		this.setLocationRelativeTo(null);
		
		// Fulfilling read of the directory tree		
		try {
			DirectoryHandle.cleanArrays();
			FileListingVisitor.runFileListingVisitor(DirectoryHandle.getPath().toString());
			arrayList  = new String[DirectoryHandle.getFilesNumber() + 1];
			arrayDates = new String[DirectoryHandle.getFilesNumber() + 1];
			modelList = new DefaultListModel<>();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Failed to get files list! Program will be closed!", TITLE, JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
		
		// Filling lists using data of file
		for (int i = 0; i <= DirectoryHandle.getFilesNumber(); i++) {
			
			if (DirectoryHandle.getFileName(i).toString().toLowerCase().endsWith(".jpg") && MainForm.isJPG) {
				arrayList[k]  = DirectoryHandle.getFileName(i).toString();
		        modelList.add(k, DirectoryHandle.getFileName(i).getFileName().toString());
		        k++;
			}
			
			if (DirectoryHandle.getFileName(i).toString().toLowerCase().endsWith(".png") && MainForm.isPNG) {
				arrayList[k]  = DirectoryHandle.getFileName(i).toString();
				modelList.add(k, DirectoryHandle.getFileName(i).getFileName().toString());
				k++;
			}
			
			if (DirectoryHandle.getFileName(i).toString().toLowerCase().endsWith(".bmp") && MainForm.isBMP) {
				arrayList[k]  = DirectoryHandle.getFileName(i).toString();
				modelList.add(k, DirectoryHandle.getFileName(i).getFileName().toString());
				k++;
			}				
		}
		
		// Left list
		leftList = new JList<>(modelList);
		leftList.setVisibleRowCount(VISIBLE_ROW_COUNT);
		leftList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		leftList.setBounds(15, 25, 265, 350);
		leftList.setEnabled(true);
		cPanel.add(leftList);
		
		leftScrollPane = new JScrollPane(leftList);
	    leftScrollPane.setBounds(15, 25, 265, 350);
	    cPanel.add(leftScrollPane);

		leftLabel = new JLabel("Files list:");
		leftLabel.setBounds(20, 0, 260, 32);
		leftLabel.setEnabled(true);
		cPanel.add(leftLabel);	    
	    
		// Transfer buttons
		toLeftButton = new JButton(">");
		toLeftButton.setBounds(290, 120, 65, 32);
		toLeftButton.setEnabled(true);
		cPanel.add(toLeftButton);
		
		allLeftButton = new JButton(">>>");
		allLeftButton.setBounds(290, 160, 65, 32);
		allLeftButton.setEnabled(true);
		cPanel.add(allLeftButton);
		
		toRightButton = new JButton("<");
		toRightButton.setBounds(290, 200, 65, 32);
		toRightButton.setEnabled(true);
		cPanel.add(toRightButton);
		
		allRightButton = new JButton("<<<");
		allRightButton.setBounds(290, 240, 65, 32);
		allRightButton.setEnabled(true);
		cPanel.add(allRightButton);
		
		// Right list
		rightLabel = new JLabel("Will be renamed:");
		rightLabel.setBounds(370, 0, 260, 32);
		rightLabel.setEnabled(true);
		cPanel.add(rightLabel);	
		
		rightList = new JList<>(new DefaultListModel<String>());
		rightList.setVisibleRowCount(VISIBLE_ROW_COUNT);
		rightList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		rightList.setBounds(365, 25, 265, 350);
		rightList.setEnabled(true);
		cPanel.add(rightList);
		
		rightScrollPane = new JScrollPane(rightList);
	    rightScrollPane.setBounds(365, 25, 265, 350);
	    cPanel.add(rightScrollPane);
	    
	    // Control buttons
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(440, 395, 85, 32);
		cancelButton.setEnabled(true);
		cPanel.add(cancelButton);
		
		okButton = new JButton("Ok");
		okButton.setBounds(535, 395, 85, 32);
		okButton.setEnabled(false);
		cPanel.add(okButton);		
		
		// Handling actions of controls.
		toLeftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				List<String> selected = leftList.getSelectedValuesList();
		        DefaultListModel<String> left = (DefaultListModel<String>) leftList.getModel();
		        DefaultListModel<String> right = (DefaultListModel<String>) rightList.getModel();
		        for (String item : selected) {
		            left.removeElement(item);
		            right.addElement(item);
		        }
		        
		        setOkButtonEnabled(right);			        
			}
		});
		
		allLeftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
		        DefaultListModel<String> left = (DefaultListModel<String>) leftList.getModel();
		        DefaultListModel<String> right = (DefaultListModel<String>) rightList.getModel();
		        for (Object item : left.toArray()) {
		            right.addElement((String) item);
		        }
		        left.removeAllElements();
		        
		        setOkButtonEnabled(right);				        
			}
		});
		
		toRightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
		        List<String> selected = rightList.getSelectedValuesList();
		        DefaultListModel<String> left = (DefaultListModel<String>) leftList.getModel();
		        DefaultListModel<String> right = (DefaultListModel<String>) rightList.getModel();
		        for (String item : selected) {
		            right.removeElement(item);
		            left.addElement(item);				
			    }
		        
		        setOkButtonEnabled(right);				        
		    }
		});
		
		allRightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
		        DefaultListModel<String> left = (DefaultListModel<String>) leftList.getModel();
		        DefaultListModel<String> right = (DefaultListModel<String>) rightList.getModel();
		        for (Object item : right.toArray()) {
		            left.addElement((String) item);
		        }
		        right.removeAllElements();	
		        
		        setOkButtonEnabled(right);		        
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent even) {
				dispose();
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				RenameFiles rf = new RenameFiles(curForm);
				rf.runRenameFiles();
				dispose();
			}
		});
		
		getContentPane().add(cPanel);
	}
	
	private void setOkButtonEnabled(DefaultListModel<String> right) {
		if (!right.isEmpty()) {
			okButton.setEnabled(true);
		} else {
			okButton.setEnabled(false);
		}
	}
	
	public int getFilesNumber() {
		return rightList.getModel().getSize();
	}
	
	public String getFileName(int k) {
		return DirectoryHandle.getPath().toString() + File.separator + rightList.getModel().getElementAt(k);
	}
	
	public String getCreationDate(int k) {
		return arrayDates[k];
	}	
}
