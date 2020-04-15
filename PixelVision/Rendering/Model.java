package PixelVision.Rendering;

import PixelVision.Math.*;
import org.apache.commons.lang.*;
import java.io.*;

public class Model {

	private Point3 location, rotation, scale;
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
	
	public void Translate(Vec3 trans) {
		location.Add(trans);
	}
	
	public void Rotate(Vec3 rot) {
		rotation.Add(rot);
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
