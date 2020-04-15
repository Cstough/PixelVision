package PixelVision.Rendering;

import PixelVision.Math.*;
import org.apache.commons.lang.*;

import java.io.*;

//A model is a Mesh that is rendered in the world --> a Model has a Mesh
public class Model {

	private Vec3 location, rotation, scale;
	private Vec3 origin;
	private Mesh mesh;
	private boolean backFaceCulled;
	
	private Model(Mesh mesh, Vec3 location) {
		this.mesh = mesh;
		this.location = location;
		rotation = new Vec3();
		origin = new Vec3();
		scale = new Vec3(1, 1, 1);
		backFaceCulled = true;
	}
	
	public static Model CreateFromMeshData(Mesh m) {
		return new Model(m, new Vec3());
	}
	
	public static Model CreateModelFromFile(String filename) throws IOException{
		File file = new File(filename);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		MeshData md = new MeshData();
		String line;

		while(true) {
			line = br.readLine();
			if(line == null) {break;}
			if(line.length() == 0) {continue;}

			if(line.startsWith("v")){
				processVertex(line, md);
			}
			else if(line.startsWith("f")){
				processFace(line, md);
			}
		}

		return Model.CreateFromMeshData(Mesh.CreateFromMeshData(md));
	}

	private static void processVertex(String line, MeshData md) {
		//float[] comps =
	}

	private static void processFace(String line, MeshData md) {

	}
	
	public void Translate(Vec3 trans) {
		location.Add(trans);
	}
	
	public void Rotate(Vec3 rot) {
		rotation.Add(rot);

		//instead of letting the rotation be outside of 0-360, ther should be some extra measures in place to re-orient it. like rot.x = 360.0f & rot.x to get the remainder.
		//I think thats right.
	}
	
	public void Scale(Vec3 scale) {
		this.scale.Add(scale);
	}
	
	public Mesh getMesh() {
		return mesh;
	}
	
	public Vec3 getLocation() {
		return location;
	}
	
	public Vec3 getRotation() {
		return rotation;
	}
	
	public Vec3 getScale() {
		return scale;
	}
	
	public Vec3 getOrigin() {
		return origin;
	}
	
	public boolean IsBackFaceCulled() {
		return backFaceCulled;
	}
	
	public void SetBackFaceCulling(boolean b) {
		backFaceCulled = b;
	}
}
