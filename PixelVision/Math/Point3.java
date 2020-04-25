package PixelVision.Math;

public final class Point3 {

    public float x, y, z, w;

    public Point3() {
        x = 0;
        y = 0;
        z = 0;
        w = 0;
    }

    public Point3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        w = 0;
    }

    public void Add(Vec3 v) {
        x += v.x;
        y += v.y;
        z += v.z;
    }

    public static Point3 Add(Point3 p, Vec3 v) {
        return new Point3(p.x + v.x, p.y + v.y, p.z + v.z);
    }

    public Point2 toPoint2() {
        return new Point2(x, y);
    }

    public Vec3 toVec3() {
        return new Vec3(x, y, z);
    }
}