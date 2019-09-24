package src;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Bitmap {
	private int width;
	private int height;
	private int[] raster;

	public Bitmap(int width, int height) {
		this.width = width;
		this.height = height;
		raster = new int[width * height];
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		raster = new int[width * height];
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		raster = new int[width * height];
	}
	
	public void setRGB(int x,int y, int color){
		raster[x+y*width] = color;
	}
	
	public int getRGB(int x,int y){
		return raster[x+y*width];
	}
	public void fill(int color){
		Arrays.fill(raster, color);
	}
	public void copyToBufferedImage(BufferedImage image){
		image.setRGB(0, 0, width, height, raster, 0, width);
	}
}
