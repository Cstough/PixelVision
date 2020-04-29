package PixelVision.Math;

public final class Vec3 {

	public float x, y, z, w;
	
	public Vec3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		w = 1.0f;
	}
	
	public Vec3() {
		x = 0f;
		y = 0f;
		z = 0f;
		w = 1.0f;
	}
	
	public Vec3 GetComponents() {
		return new Vec3(x, y, z);
	}
	
	public void Add(Vec3 v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
	}

	public static Vec3 Add(Vec3 v1, Vec3 v2) {
		return new Vec3(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
	}

	public Vec3 Mul(float f) {
		return new Vec3(x*f, y*f, z*f);
	}

	public static Vec3 Mul(Vec3 v, float f) {
		return new Vec3(v.x * f, v.y * f, v.z * f);
	}
	
	public static Vec3 Diff(Vec3 dest, Vec3 orig) {
		return new Vec3(dest.x - orig.x, dest.y - orig.y, dest.z - orig.z);
	}

	public Vec3 Diff(Vec3 v) {
		return new Vec3(x - v.x, y - v.y, z - v.z);
	}

	public static float Dot(Vec3 v1, Vec3 v2) {
		float p = 0;
		
		p = v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
		
		return p;
	}

	public float Dot(Vec3 v) {
		return x * v.x + y * v.y + z * v.z;
	}
	
	public static Vec3 Cross(Vec3 v1, Vec3 v2) {
		Vec3 r = new Vec3();
		
		r.x = v1.y * v2.z - v2.y * v1.z;
		r.y = v2.x * v1.z - v1.x * v2.z;
		r.z = v1.x * v2.y - v2.x * v1.y;
		
		return r;
	}
	
	public static Vec3 Normalize(Vec3 v) {
		float l = (float)Math.sqrt(v.x*v.x + v.y*v.y + v.z*v.z);
		return new Vec3(v.x / l, v.y / l, v.z / l);
	}
	
	public Vec3 GetNegative() {
		return new Vec3(-x, -y, -z);
	}
	
	public String toString() {
		return "X: " + x + ", Y: " + y + ", Z: " + z;
	}
	
	public Vec3 Clone() {
		Vec3 ret = new Vec3(x, y, z);
		ret.w = w;
		return ret;
	}

	public Vec2 toVec2() {
		return new Vec2(x, y);
	}
}
