package PixelVision;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.JFrame;

import PixelVision.Rendering.Bitmap;

final class Window extends Canvas{
	
	private final JFrame frame;
	private final Bitmap frameBuffer;
	private final BufferedImage displayImage;
	private final byte[] displayComponents;
	private final BufferStrategy bufferStrategy;
	private final Graphics graphics;
	private final int scale;
	private final Dimension dimension;
	
	public Window(int Width, int Height, int Scale, String Title) {
		dimension = new Dimension(Width * Scale, Height * Scale);
		scale = Scale;
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		
		frameBuffer = new Bitmap(Width, Height);
		displayImage = new BufferedImage(Width, Height, BufferedImage.TYPE_4BYTE_ABGR);
		displayComponents = ((DataBufferByte) (displayImage.getRaster().getDataBuffer())).getData();
		
		frame = new JFrame();
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new InputScanner());
		frame.setLocationRelativeTo(null);
		frame.setTitle(Title);
		frame.setVisible(true);
		frame.setAutoRequestFocus(true);
		
		createBufferStrategy(1);
		bufferStrategy = getBufferStrategy();
		graphics = bufferStrategy.getDrawGraphics();
	}
	
	public void SwapBuffers() {
		frameBuffer.CopyToByteArray(displayComponents);
		graphics.drawImage(displayImage, 0, 0, frameBuffer.GetWidth() * scale, frameBuffer.GetHeight() * scale, null);
		bufferStrategy.show();
	}
	
	public Bitmap GetFrameBuffer() {
		return frameBuffer;
	}
}
