package PixelVision.Rendering;

public final class Vertex {

    public Point3 position;
    public Vec3 normal;
    public Point2 textureCoordinate;

    public Vertex(Point3 position, Vec3 normal, Point2 textureCoordinate) {
        this.position = position;
        this.normal = normal;
        this.textureCoordinate = textureCoordinate;
    }

}