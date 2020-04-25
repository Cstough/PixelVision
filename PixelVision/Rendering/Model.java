package PixelVision.Rendering;

import PixelVision.Math.*;

public class Model {

	private Point3 location, rotation, scale, origin;
	private Mesh mesh;
	
	public Model(Mesh mesh, Point3 location) {
		this.mesh = mesh;
		this.location = location;
		rotation = new Point3();
		origin = new Point3();
		scale = new Point3(1, 1, 1);
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
	
	public Point3 getLocation() {
		return location;
	}
	
	public Point3 getRotation() {
		return rotation;
	}
	
	public Point3 getScale() {
		return scale;
	}
	
	public Point3 getOrigin() {
		return origin;
	}
}
