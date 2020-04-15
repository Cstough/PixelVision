package PixelVision.Rendering;

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
	
	public int GetWidth() {
		return width;
	}
	
	public int GetHeight() {
		return height;
	}
}
