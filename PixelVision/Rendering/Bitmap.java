package PixelVision.Rendering;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bitmap {
	
	private int width, height;
	private byte components[];
	
	public Bitmap(int width, int height) {
		this.width = width;
		this.height = height;
		components = new byte[width * height * 4];
	}
	
	public void Clear(byte[] color) {
		for(int i = 0; i < components.length / 4; i++) {
			components[i * 4] = color[0];
			components[i * 4 + 1] = color[1];
			components[i * 4 + 2] = color[2];
			components[i * 4 + 3] = color[3];
		}
	}
	
	public void CopyToByteArray(byte[] dest) {
		for(int i = 0; i < width * height; i++) {
			dest[i * 4 + 3] = components[i * 4 + 3];
			dest[i * 4 + 2] = components[i * 4 + 2];
			dest[i * 4 + 1] = components[i * 4 + 1];
			dest[i * 4 + 0] = components[i * 4 + 0];
		}
	}
	
	public void SetPixel(int x, int y, Color color) {
		if(x <= width - 1 && y <= height - 1 && x >= 0 && y >= 0) {
			int index = ((y * width) + x) * 4;
			byte[] colorComponents = color.GetComponents();
			components[index] = colorComponents[0];
			components[index + 1] = colorComponents[1];
			components[index + 2] = colorComponents[2];
			components[index + 3] = colorComponents[3];
		}
	}

	public Color GetPixel(int x, int y) {
		int index = ((y * width) + x) * 4;

		try {
			return new Color(new byte[]{
					components[index + 3],
					components[index + 2],
					components[index + 1],
					components[index + 0]
			});
		} catch(Exception e) {
			return new Color(Color.MAGENTA);
		}
	}

	public static Bitmap LoadSprite(String filename) {
		BufferedImage image = null;
		Bitmap spr = null;
		try {
			image = ImageIO.read(new File(filename));
		} catch(IOException e) {
			e.printStackTrace();
		}
		spr = new Bitmap(image.getWidth(), image.getHeight());
		for(int i = 0; i < spr.GetHeight(); i++) {
			for(int j = 0; j < spr.GetWidth(); j++) {
				int colorint = image.getRGB(j, i);
				byte[] bytecolor = new byte[] {
						(byte)(colorint >> 0),
						(byte)(colorint >> 8),
						(byte)(colorint >> 16),
						(byte)(colorint >> 24)
				};
				spr.SetPixel(j, i, new Color(bytecolor));
			}
		}
		return spr;
	}
	
	public int GetWidth() {
		return width;
	}
	
	public int GetHeight() {
		return height;
	}
}
