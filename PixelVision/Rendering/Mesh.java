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
	
	public Triangle[] GetTrianglesClone() {
		Triangle[] tris = new Triangle[meshData.Triangles.length];

		for(int i = 0; i < tris.length; i++) {
			Triangle t = meshData.Triangles[i];
			tris[i] = new Triangle(t.vertices, t.textCoords, t.vertexNormals);
		}

		return tris;
	}

	public Triangle[] GetTrianglesRef() {
		return meshData.Triangles;
	}
	
	public Vertex[] GetVertexDataClone() {

		Vertex[] verts = new Vertex[meshData.Vertices.length];

		for(int i = 0; i < verts.length; i++) {
			Vertex v = new Vertex(meshData.Vertices[i].position);
			verts[i] = v;
		}

		return verts;
	}

	public Vertex[] GetVertexDataRef() {
		return meshData.Vertices;
	}
}


