package PixelVision.Rendering;

import PixelVision.Math.*;

public final class MeshData {

    Vertex[] Vertices;
    Triangle[] Triangles;

    private MeshData(Vertex[] verts, Triangle[] tris) {
        Vertices = verts;
        Triangles = tris;
    }

    public static MeshData CreateMeshData(Vertex[] vertices, Triangle[] triangles) {
        return new MeshData(vertices, triangles);
    }
}