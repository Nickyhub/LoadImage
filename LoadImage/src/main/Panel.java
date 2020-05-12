package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static int mode = 0;
	private static BufferedImage secondImage;
	private static boolean drawn = false;

	public static Graphics2D g2d;

	public Panel() {
		try {
			secondImage = ImageIO.read(new File("D:\\Galaxie.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g2d = (Graphics2D) g;

		if (mode == 0) {
			g2d.drawImage(Window.image, 0, 0, null);
		} else if (mode == 1) {

			g2d.drawImage(Window.image, 0, 0, null);
			if (!drawn) {
				for (int y = 0; y < secondImage.getHeight(); y++) {
					for (int x = 0; x < secondImage.getWidth(); x++) {
						secondImage.setRGB(x, y, secondImage.getRGB(x, y) + Color.MAGENTA.getRGB());
					}
				}
			}
			g2d.drawImage(secondImage, 0, Window.image.getHeight(), null);
			drawn = true;

		}

	}

}
