import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.plaf.metal.*;
import javax.swing.JScrollBar;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.Toolkit;
import javax.swing.DropMode;
import javax.swing.JFileChooser;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;


public class JNote {
	
	private JFrame frmJnoteFor;
	
	/**
	* Launch the application.
	*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JNote window = new JNote();
					window.frmJnoteFor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	* Create the application.
	*/
	public JNote() {
		initialize();
	}
	
	/**
	* Initialize the contents of the frame.
	*/
	private void initialize() {
		frmJnoteFor = new JFrame();
		frmJnoteFor.setTitle("JNote - For Quick Notes" );
		frmJnoteFor.setResizable(true);
		frmJnoteFor.setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\JAVA-DEV\\JAVA-Workspace\\JNote\\src\\coffee1.png"));
		frmJnoteFor.setBounds(100, 100, 450, 300);
		frmJnoteFor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			// Set metal look and feel
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			// Set theme to ocean
			MetalLookAndFeel.setCurrentTheme(new OceanTheme());
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(frmJnoteFor, "Error", "Sorry,an error occured", 0);
		}

		JTextArea editor = new JTextArea();
		JScrollPane scroll = new JScrollPane(editor);
		frmJnoteFor.getContentPane().add(scroll);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 153, 255));
		frmJnoteFor.setJMenuBar(menuBar);
		
		JMenu File = new JMenu("File");
		menuBar.add(File);
		
		
		JMenuItem Open = new JMenuItem("Open a file");
		Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK ));
		Open.setHorizontalAlignment(SwingConstants.LEFT);
		File.add(Open);
		Open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser("f:");
				
				// Invoke the showsOpenDialog function to show the save dialog
				int r = j.showOpenDialog(null);
				
				// If the user selects a file
				if (r == JFileChooser.APPROVE_OPTION) {
					// Set the label to the path of the selected directory
					File fi = new File(j.getSelectedFile().getAbsolutePath());
					
					try {
						// String
						String s1 = "", sl = "";
						
						// File reader
						FileReader fr = new FileReader(fi);
						
						// Buffered reader
						BufferedReader br = new BufferedReader(fr);
						
						// Initialize sl
						sl = br.readLine();
						
						// Take the input from the file
						while ((s1 = br.readLine()) != null) {
							sl = sl + "\n" + s1;
						}
						
						// Set the text
						editor.setText(sl);
					}
					catch (Exception evt) {
						JOptionPane.showMessageDialog(frmJnoteFor, evt.getMessage());
					}
				}
				
				
				
			}
			
		});
		
		
		
		JMenuItem Save = new JMenuItem("Save");
		Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK ));
		File.add(Save);
		Save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser("f:");
				
				// Invoke the showsSaveDialog function to show the save dialog
				int r = j.showSaveDialog(null);
				
				if (r == JFileChooser.APPROVE_OPTION) {
					
					// Set the label to the path of the selected directory
					File fi = new File(j.getSelectedFile().getAbsolutePath());
					
					
					try {
						// Create a file writer
						FileWriter wr = new FileWriter(fi, false);
						
						// Create buffered writer to write
						BufferedWriter w = new BufferedWriter(wr);
						
						// Write
						w.write(editor.getText());
						
						w.flush();
						w.close();
					}
					catch (Exception evt) {
						JOptionPane.showMessageDialog(frmJnoteFor, evt.getMessage());
						
					}
					
				}
				
				
			}
			
		});
		
		JMenuItem download_as = new JMenuItem("Download as");
		download_as.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
		File.add(download_as);
		download_as.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// print the file
					editor.print();
					
				}
				catch (Exception evt) {
					JOptionPane.showMessageDialog(frmJnoteFor, "Sorry an error occured in downloading your file");
				}
				
			}
		});
		
		JMenu Styles = new JMenu("Styles");
		menuBar.add(Styles);
		
		JMenu font_color = new JMenu("Font Color");
		Styles.add(font_color);
		
		JMenuItem red_color = new JMenuItem("Red");
		font_color.add(red_color);
		red_color.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editor.setForeground(Color.red);
				
			}
			
		});
		
		JMenuItem blue_color = new JMenuItem("Blue");
		font_color.add(blue_color);
		blue_color.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editor.setForeground(Color.blue);
				
			}
			
		});
		
		
		JMenuItem green_color = new JMenuItem("Green");
		font_color.add(green_color);
		green_color.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editor.setForeground(Color.green);
				
			}
			
		});
		
		JMenuItem yellow_color = new JMenuItem("Yellow");
		font_color.add(yellow_color);
		yellow_color.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editor.setForeground(Color.yellow);
				
			}
			
		});
		
		JMenu Exit = new JMenu("Exit");
		menuBar.add(Exit);
		
		JMenuItem Exit_App = new JMenuItem("Exit App");
		Exit_App.setSelected(true);
		Exit_App.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
		Exit.add(Exit_App);
		Exit_App.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frmJnoteFor.setVisible(false);
				
			}
			
		});
		
		
		
		
	}
	
}
