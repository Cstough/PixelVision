package PixelVision;

import PixelVision.Rendering.Bitmap;

public abstract class Engine {

	private Window window;
	private Bitmap target;
	private Timer timer;
	private boolean running;
	private int Width, Height, Scale;
	private String Title;
	private float framesPerSecond, updatesPerSecond;
	private byte[] clearColor;
	public static float deltaTime;
	private InputScanner inputScanner;
	
	public Engine() {}
	
	public void Start() throws InterruptedException {
		
		window = new Window(Width, Height, Scale, Title);
		target = window.GetFrameBuffer();
		timer = new Timer();
		running = true;
		inputScanner = new InputScanner();
		
		float elapsedTime, accumulator = 0.0f;
		float interval = 1f / updatesPerSecond;
		
		Init();
		
		while(running) {
			elapsedTime = timer.getElapsedTime();
			accumulator += elapsedTime;
			while(accumulator >= interval) {
				deltaTime = interval;
				Update();
				accumulator -= interval;
			}
			
			Render();
			window.SwapBuffers();
			sync();
		}
		Exit();
	}
	
	public void Init() {
		
	}
	
	public void Update() {
		inputScanner.Update();
	}
	
	public void Render() {
		target.Clear(clearColor);
	}
	
	public void Exit() {
		System.exit(0);
	}
	
	private void sync() throws InterruptedException {
		float loopSlot = 1f / framesPerSecond;
		double endTime = timer.getLastLoopTime() + loopSlot;
		while(timer.getTime() < endTime) {
			try {
				Thread.sleep(1);
			}catch(InterruptedException e) {
				
			}
		}
	}
	
	public void SetFramesPerSecond(float framesPerSecond) {
		this.framesPerSecond = framesPerSecond;
	}
	
	public void SetUpdatesPerSecond(float updatesPerSecond) {
		this.updatesPerSecond = updatesPerSecond;
	}
	
	public void SetTitle(String Title) {
		this.Title = Title;
	}
	
	public void SetClearColor(byte[] color) {
		clearColor = color;
	}
	
	public void SetScreenSize(int width, int height, int scale) {
		Width = width;
		Height = height;
		Scale = scale;
	}
	
	public Bitmap GetTarget() {
		return target;
	}
}
