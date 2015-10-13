package daterCore;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
//import java.awt.image.BufferedImage;
import java.awt.event.ItemEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import java.io.File;

/**
 * Creates frame and set its properties.
 * 
 * @author Maksat E.
 */

@SuppressWarnings("serial")
public class MainForm extends JFrame{
	
	public static String[] nameMethods = {"Using increment after the date to make unique, the files made in same date.", 
			                               "Using milliseconds to make unique, the files made in same date."};
	public static final String TITLE = "FileDater v1.0";
	
	public static Boolean isJPG;
	public static Boolean isPNG;
	public static Boolean isBMP;
	public static String  nameType;
	public static String  nameMethod;
	
	private JMenuBar     menuBar;
	private JMenu        fileMenu;
	private JMenuItem    menuItem;
	private JMenuItem    aboutItem;
	private JButton      dirButton;
	private JButton      closeButton;
	private JButton      okButton;
	private JCheckBox    jpgCheckBox;
	private JCheckBox    pngCheckBox;
	private JCheckBox    bmpCheckBox;
	private JRadioButton firstRadioButton;
	private JRadioButton replaceRadioButton;
	private JRadioButton lastRadioButton;
	private JComboBox<Object> nameComboBox;
	private JTextField   pathTextField;
	private JLabel       comboLabel;
	private ButtonGroup  groupRadio;
	private JPanel       mPanel;
	private JPanel       leftPanel;
	private JPanel       rightPanel;
	//private MainForm     mf;
	
	public MainForm() {
		super(TITLE);
		
		mPanel = new JPanel();
		mPanel.setLayout(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(436, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        /*
        BufferedImage image = null;
        try {
            image = ImageIO.read(
                mf.getClass().getResource("/res/favicon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mf.setIconImage(image);       
        */
        
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);
        
        aboutItem = new JMenuItem("About", KeyEvent.VK_A);
        fileMenu.add(aboutItem);
        
        fileMenu.addSeparator();
        
        menuItem = new JMenuItem("Exit", KeyEvent.VK_E);
        fileMenu.add(menuItem);

		pathTextField = new JTextField(50);
		pathTextField.setBounds(12, 10, 305, 32);
		pathTextField.setEditable(false);
		mPanel.add(pathTextField);
		
		dirButton = new JButton("Browse");
		dirButton.setBounds(328, 10, 90, 32);
		dirButton.setEnabled(true);
		mPanel.add(dirButton);
		
		leftPanel = new JPanel();
		leftPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("Formats"),
        BorderFactory.createEmptyBorder(10,10,10,10)));
		leftPanel.setBounds(10, 48, 200, 150);
		
		
		jpgCheckBox = new JCheckBox("JPEG");
		jpgCheckBox.setBounds(25, 80, 150, 32);
		jpgCheckBox.setEnabled(true);
		jpgCheckBox.setSelected(true);
		isJPG = true;
		mPanel.add(jpgCheckBox);
		
		pngCheckBox = new JCheckBox("PNG");
		pngCheckBox.setBounds(25, 112, 150, 32);
		pngCheckBox.setEnabled(true);
		pngCheckBox.setSelected(true);
		isPNG = true;
		mPanel.add(pngCheckBox);
		
		bmpCheckBox = new JCheckBox("BMP");
		bmpCheckBox.setBounds(25, 144, 150, 32);
		bmpCheckBox.setEnabled(true);
		bmpCheckBox.setSelected(true);
		isBMP = true;
		mPanel.add(bmpCheckBox);
		
		rightPanel = new JPanel();
		rightPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("File name"),
        BorderFactory.createEmptyBorder(10,10,10,10)));
		rightPanel.setBounds(220, 48, 200, 150);
		
		firstRadioButton = new JRadioButton("Date_Oldname");
		firstRadioButton.setBounds(235, 80, 150, 32);
		firstRadioButton.setEnabled(true);
		mPanel.add(firstRadioButton);
		
		replaceRadioButton = new JRadioButton("Only date");
		replaceRadioButton.setBounds(235, 112, 150, 32);
		replaceRadioButton.setEnabled(true);
		replaceRadioButton.setSelected(true);
		nameType = "REPLACE";
		mPanel.add(replaceRadioButton);
		
		lastRadioButton = new JRadioButton("Oldname_date");
		lastRadioButton.setBounds(235, 144, 150, 32);
		lastRadioButton.setEnabled(true);
		mPanel.add(lastRadioButton);
		
		groupRadio = new ButtonGroup();
		groupRadio.add(firstRadioButton);
		groupRadio.add(replaceRadioButton);
		groupRadio.add(lastRadioButton);
		
		comboLabel = new JLabel("File name creation method");
		comboLabel.setBounds(15, 198, 400, 32);
		comboLabel.setEnabled(true);
		mPanel.add(comboLabel);
		
		nameComboBox = new JComboBox<Object>(nameMethods);
		nameComboBox.setBounds(12, 226, 406, 32);
		nameComboBox.setEnabled(true);
		nameComboBox.setToolTipText(nameComboBox.getSelectedItem().toString());
		nameMethod = nameComboBox.getSelectedItem().toString();
		mPanel.add(nameComboBox);
		
		closeButton = new JButton("Close");
		closeButton.setBounds(246, 288, 80, 32);
		closeButton.setEnabled(true);
		mPanel.add(closeButton);
		
		okButton = new JButton("Start");
		okButton.setBounds(338, 288, 80, 32);
		okButton.setEnabled(false);
		mPanel.add(okButton);
		
		
		mPanel.add(leftPanel);
		mPanel.add(rightPanel);
		getContentPane().add(mPanel);
		
		
		
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, 
					"FileDater can help you to preserve the creation date of photograph file in the name of file.\n" +
				    "The program automatically rename all selected files with picked up parameters. Parameters are\n" +
				    "extension limitation, file name structure and method making file name unique:\n" +
				    "- Program can handle only jpg, png and bmp formats.\n" +
				    "- Examples of file names: 2015_09_27_21_43_55_220.jpg, Summer_Holiday_2015_09_27_21_43_55_220.jpg\n" +
				    "- Format of date is following: Year_Month_Day_Hour_Minute_Second_Milliscond.jpg\n" +
				    "- To make file name unique among the others, the milliseconds or increment used at the end of\n" +
				    " date in file name.\n" +
				    " \n" +
				    "2015. Maksat E."
						, TITLE, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		dirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			    JFileChooser chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle(TITLE);
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);
	
			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			      //System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
			    	File file = new File(chooser.getSelectedFile().toString());
			    	if (file.exists()) {
			    		DirectoryHandle.setPath(chooser.getSelectedFile());
			    		pathTextField.setText(DirectoryHandle.getPath().toString());
			    		pathTextField.setToolTipText(DirectoryHandle.getPath().toString());
			    		checkPreparation();
			    	} 
			    	
			    } else {
			    	okButton.setEnabled(false);
			    }				
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ChooseForm cForm = new ChooseForm();
				cForm.setVisible(true);
			}
		});
		
		jpgCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				checkPreparation();
				if (jpgCheckBox.isSelected()) {
					isJPG = true;
				} else {
					isJPG = false;
				}
			}
		});
		
		pngCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				checkPreparation();
				if (pngCheckBox.isSelected()) {
					isPNG = true;
				} else {
					isPNG = false;
				}			
			}
		});
		
		bmpCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				checkPreparation();
				if (bmpCheckBox.isSelected()) {
					isBMP = true;
				} else {
					isBMP = false;
				}					
			}
		});
		
		firstRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				nameType = "FIRST";
			}
		});
		
		replaceRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				nameType = "REPLACE";
			}
		});
		
		lastRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				nameType = "LAST";
			}
		});
		
		nameComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					nameMethod = nameComboBox.getSelectedItem().toString();
				}
			}
		});
	}
	
	private void checkPreparation() {
		if (pathTextField.getText().length() > 0 &&
				(jpgCheckBox.isSelected() || 
						pngCheckBox.isSelected() || 
						bmpCheckBox.isSelected())) {
			okButton.setEnabled(true);
		} else {
			okButton.setEnabled(false);
		}
	}
}
