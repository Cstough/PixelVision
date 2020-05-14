package PixelVision.Math;

public final class Vec2 {

	public float x, y;
	
	public Vec2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vec2() {
		x = 0f;
		y = 0f;
	}
	
	public String toString() {
		return "X: " + x + ", Y: " + y;
	}
	
	public Vec3 toVec3() {
		return new Vec3(x, y, 0);
	}
}
