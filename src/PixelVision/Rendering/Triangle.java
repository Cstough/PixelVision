package PixelVision.Rendering;

import PixelVision.Math.Point2;
import PixelVision.Math.Point3;
import PixelVision.Math.Vec3;

final public class Triangle {

    public Point3[] vertices;
    public Vec3[] vertexNormals;
    public Point2[] textCoords;

    public Triangle(Point3[] verts, Point2[] textureCoords, Vec3[] vertNormals) {
        vertices = verts;
        vertexNormals = vertNormals;
        textCoords = textureCoords;
    }

    //returns a deep copy of this triangle
    public Triangle GetTriangleDeepCopy() {
        Point3[] vertices_dc = new Point3[]{new Point3(vertices[0]), new Point3(vertices[1]), new Point3(vertices[2])};
        Point2[] textureCoords_dc = new Point2[]{new Point2(textCoords[0]), new Point2(textCoords[1]), new Point2(textCoords[2])};
        Vec3[] vertexNormals_dc = new Vec3[]{new Vec3(vertexNormals[0]), new Vec3(vertexNormals[1]), new Vec3(vertexNormals[2])};

        return new Triangle(vertices_dc, textureCoords_dc, vertexNormals_dc);
    }
}