package src;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8659411578285562291L;

	private final JFrame window = new JFrame();
	private int width, height;
	private BufferedImage frame;
	private Bitmap bitmap;
	private boolean isPainted;
	private Fluid fluid;

	public Display(int width, int height) {
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(this.width, this.height));
		frame = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
		bitmap = new Bitmap(this.width, this.height);
		fluid = new Fluid(this.width, this.height);
		window.add(this);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(3);
		window.setVisible(true);
	}

	public void init() {
	}

	public void update() {
		for (int i = 0; i < width * height; i++) {
			int x = i % width;
			int y = i / width;
			bitmap.setRGB(x, y, ((int)(fluid.getMass(x, y)*256))|0xff000000);
		}
		bitmap.copyToBufferedImage(frame);
		bitmap.fill(-1);
		isPainted = false;
		repaint();
		while (!isPainted) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
		fluid.update();
	}

	public void paint(Graphics g) {
		g.drawImage(frame, 0, 0, null);
		isPainted = true;
	}

}
