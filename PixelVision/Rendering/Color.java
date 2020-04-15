package PixelVision.Rendering;

public class Color {
	private byte[] components;
	
	public Color(byte[] components) {
		this.components = components;
	}
	
	public byte[] GetComponents() {
		return components;
	}
												//A			B			G			R
	public static byte[] RED = new byte[] { (byte)0xff, (byte)0x00, (byte)0x00, (byte)0xff};
	public static byte[] GREEN = new byte[] { (byte)0xff, (byte)0x00, (byte)0xff, (byte)0x00};
	public static byte[] BLUE = new byte[] { (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00};
	public static byte[] BLACK = new byte[] { (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00};
	public static byte[] WHITE = new byte[] { (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff};
	public static byte[] CYAN = new byte[] { (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x00};
	public static byte[] YELLOW = new byte[] { (byte)0xff, (byte)0x00, (byte)0xff, (byte)0xff};
	public static byte[] MAGENTA = new byte[] { (byte)0xff, (byte)0xff, (byte)0x00, (byte)0xff};
}
