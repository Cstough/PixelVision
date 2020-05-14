package PixelVision;

import PixelVision.Math.Vec2;

public abstract class Util {

	public static void Swap(Vec2 vec) {
		float temp = vec.x;
		vec.x = vec.y;
		vec.y = temp;
	}
	
	public static void SwapX(Vec2 v1, Vec2 v2) {
		float temp = v1.x;
		v1.x = v2.x;
		v2.x = temp;
	}
	
	public static void SwapY(Vec2 v1, Vec2 v2) {
		float temp = v1.y;
		v1.y = v2.y;
		v2.y = temp;
	}
	
	public static float Min(float f1, float f2) {
		if(f1 > f2) {return f1;}
		else {return f2;}
	}
	
	public static float Max(float f1, float f2) {
		if(f1 > f2) {return f2;}
		else {return f1;}
	}
}
