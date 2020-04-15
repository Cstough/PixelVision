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

	public static Mesh CreateBasicTri() {
		Mesh m = new Mesh();
		
		return m;
	}
	
	public MeshData GetMeshData() {
		return meshData;
	}
	
	public Triangle[] GetTriangles() {
		return meshData.Triangles;
	}
	
	public Vec3[] GetVertexData() {
		return meshData.Vertices;
	}
}

final class Triangle {

	int[] Verts;

	public Triangle(int a, int b, int c) {
		Verts = new int[] {a, b, c};
	}

	public String toString() {
		return Verts[0] + ", " + Verts[1] + ", " + Verts[2];
	}
}

final class MeshData {

	Vec3[] Vertices;
	Triangle[] Triangles;

	public MeshData() {

		Vertices = new Vec3[] {
				new Vec3(-0.5f, -0.5f, -0.5f),
				new Vec3(0.5f, -0.5f, -0.5f),
				new Vec3(0.5f, 0.5f, -0.5f)
		};
		Triangles = new Triangle[] {
				new Triangle(0, 1, 2)
		};
	}
}


