package PixelVision.Rendering;

import PixelVision.Math.*;

public class Mesh {

	private MeshData meshData;
	
	private Mesh() {

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

		Vertex[] verts = new Vertex[meshData.Vertices.length];

		for(int i = 0; i < verts.length; i++) {
			Vertex v = new Vertex(meshData.Vertices[i].position);
			verts[i] = v;
		}

		return verts;
	}
}


