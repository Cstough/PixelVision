package PixelVision.Rendering;

import PixelVision.Math.Point3;

public final class Vertex {

    public Point3 position;
    public float weight; //used for a future animation system.

    public Vertex(Point3 position) {
        this.position = position;
    }

}