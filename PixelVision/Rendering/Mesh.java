package PixelVision.Rendering;

import PixelVision.Math.*;

public class Mesh {

	private MeshData meshData;
	
	private Mesh() {
		meshData = new MeshData();
	}

	public static Mesh CreateFromMeshData(MeshData md) {
		Mesh m = new Mesh();
		m.meshData = md;
		return m;
	}
	
	public MeshData GetMeshData() {
		return meshData;
	}
	
	public Triangle[] GetTriangles() {
		return meshData.Triangles;
	}
	
	public Vertex[] GetVertexData() {
		return meshData.Vertices;
	}
}

final class Triangle {

	int[] vertices;

	public Triangle(int a, int b, int c) {
		Verts = new int[] {a, b, c};
	}
}


