package PixelVision.Rendering;

import PixelVision.Math.Point2;
import PixelVision.Math.Vec3;

final public class Triangle {

    public int[] vertices;
    public Vec3[] vertexNormals;
    public Point2[] textCoords;

    public Triangle(int[] verts, Point2[] textureCoords, Vec3[] vertNormals) {
        vertices = verts;
        vertexNormals = vertNormals;
        textCoords = textureCoords;
    }
}