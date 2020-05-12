package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Window extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public static JMenuItem open;
	public static JMenuItem save;
	public static JMenuItem open2;
	public static BufferedImage image;
	
	private static Panel panel;
	private static File file;
	
	Window(String title) {
		
		image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		this.setSize(1920, 1080);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JMenu menu = new JMenu("Options");
		
		JMenuBar menubar = new JMenuBar();
		
		open2 = new JMenuItem("Open (Aufgabe2)");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		
		menu.add(open);
		menu.add(save);
		menu.add(open2);
		
		//set up panel
		panel = new Panel();
		menubar.add(menu);
		this.setJMenuBar(menubar);
		
		//set ActionListeners
		setActionListeners();
		
		this.add(panel);
		
		this.setTitle(title);
		this.setVisible(true);
	}
	
	private static void setActionListeners() {
		
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				doOpen();

			}
			
		});
		
		save.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fc = new JFileChooser();
				fc.showSaveDialog(panel);
				
				
				try {
					ImageIO.write(image, "png", fc.getSelectedFile());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		open2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileFilter filter = new FileNameExtensionFilter("JPEG File", "jpg", "jpeg", "png");
				JFileChooser fc = new JFileChooser();
				fc.setFileFilter(filter);
				//fc.setCurrentDirectory(new File("D:\\"));
				int response = fc.showOpenDialog(panel);
				file = fc.getSelectedFile();
				
				if(response == JFileChooser.APPROVE_OPTION) {
					try {
						image = ImageIO.read(file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				Panel.mode = 1;
				panel.repaint();
				
				
			}
			
		});
	}
	
	private static void doOpen() {
		FileFilter filter = new FileNameExtensionFilter("JPEG File", "jpg", "jpeg", "png");
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filter);
		//fc.setCurrentDirectory(new File("D:\\"));
		int response = fc.showOpenDialog(panel);
		file = fc.getSelectedFile();
		
		if(response == JFileChooser.APPROVE_OPTION) {
			try {
				image = ImageIO.read(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Panel.mode = 0;
		panel.repaint();
	}
	

	
}

