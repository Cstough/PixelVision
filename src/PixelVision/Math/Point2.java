package PixelVision.Math;

public final class Point2 {

    public float x, y;

    public Point2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point2() {
        x = 0.0f;
        y = 0.0f;
    }

    public Point2(Point2 p) {
        x = p.x;
        y = p.y;
    }

    public static Point2 Mul(Point2 p, float f) {
        return new Point2(p.x * f, p.y * f);
    }

    public static Point2 Add(Point2 p1, Point2 p2) {
        return new Point2(p1.x + p2.x, p1.y + p2.y);
    }
}