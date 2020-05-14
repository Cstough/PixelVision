package PixelVision.Rendering;

import PixelVision.Math.Point2;
import PixelVision.Math.Vec2;
import PixelVision.Util;

public final class Draw {
	
	//Bresenham's Line Drawing Algorithm
	public static void DrawLine(Point2 v1, Point2 v2, Bitmap target, Color color) {

		Vec2 l1 = new Vec2(v1.x, v1.y);
		Vec2 l2 = new Vec2(v2.x, v2.y);

		boolean steep = false;
		if (Math.abs(l1.x - l2.x) < Math.abs(l1.y - l2.y)) {
			Util.Swap(l1);
			Util.Swap(l2);
			steep = true;
		}
		if (l1.x > l2.x) {
			Util.SwapX(l1, l2);
			Util.SwapY(l1, l2);
		}

		int dx = (int) (l2.x - l1.x);
		int dy = (int) (l2.y - l1.y);
		int derror2 = Math.abs(dy) * 2;
		int error2 = 0;
		int y = (int) l1.y;
		int yinc = (l2.y > l1.y) ? 1 : -1;
		if (steep) {
			for (int x = (int) (l1.x); x < (int) (l2.x); ++x) {
				target.SetPixel(y, x, color);
				error2 += derror2;
				if (error2 > dx) {
					y += yinc;
					error2 -= dx * 2;
				}
			}
		} else {
			for (int x = (int) (l1.x); x < (int) (l2.x); ++x) {
				target.SetPixel(x, y, color);
				error2 += derror2;
				if (error2 > dx) {
					y += yinc;
					error2 -= dx * 2;
				}
			}
		}
	}
}
