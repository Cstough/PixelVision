package PixelVision.Rendering;

import PixelVision.Math.Point3;
import PixelVision.Math.Vec3;
/*
public class Model {

	private Point3 location, rotation, scale, origin;
	private Mesh mesh;
	private Bitmap texture;
	
	public Model(Mesh mesh, Point3 location) {
		this.mesh = mesh;
		this.location = location;
		rotation = new Point3();
		origin = new Point3();
		scale = new Point3(1, 1, 1);
		texture = Bitmap.LoadSprite("checkerBoard.png");
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

	public void LoadTexture(String texturePath) {
		texture = Bitmap.LoadSprite(texturePath);
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

	public Bitmap getTexture() {
		return texture;
	}

	public Model clone() {

		MeshData meshData = MeshData.CreateMeshData(mesh.GetVertexDataClone(), mesh.GetTrianglesClone());
		Mesh newMesh = Mesh.CreateFromMeshData(meshData);

		Model m = new Model(newMesh, new Point3(location.x, location.y, location.z));

		m.Rotate(rotation.toVec3());
		m.Scale(scale.toVec3());

		return m;
	}
}
 */

public class Model {
	private Point3 location, rotation, scaling, origin;
	private MeshGroup[] meshGroups;

	public Model(MeshGroup[] meshgroups) {
		this.meshGroups = meshgroups;
		location = new Point3(0, 0, -5);
		rotation = new Point3((float)Math.toRadians(180f), 0, 0);
		scaling = new Point3(1, 1, 1);
		origin = new Point3();
	}

	private Model(MeshGroup[] mgs, Point3 l, Point3 r, Point3 s, Point3 o) {
		this.meshGroups = mgs;
		location = l;
		rotation = r;
		scaling = s;
		origin = o;
	}

	public Model GetModelDeepCopy() {
		return new Model(GetMeshGroupsDeepCopy(), new Point3(location), new Point3(rotation), new Point3(scaling), new Point3(origin));
	}

	public MeshGroup[] GetMeshGroupsReference() {
		return meshGroups;
	}

	private MeshGroup[] GetMeshGroupsDeepCopy() {
		MeshGroup[] meshGroups_dc = new MeshGroup[meshGroups.length];

		for(int mg = 0; mg < meshGroups.length; mg++) {
			MeshGroup old = meshGroups[mg];
			MeshGroup newGroup = new MeshGroup();
			newGroup.SetName(old.GetName());
			newGroup.SetMaterial(old.GetMaterial());
			newGroup.SetSmoothState(old.GetSmoothState());
			newGroup.SetTriangles(old.GetTrianglesDeepCopy());
			meshGroups_dc[mg] = newGroup;
		}
		return meshGroups_dc;
	}

	public Point3 GetScale() {
		return scaling;
	}

	public Point3 GetRotation() {
		return rotation;
	}

	public Point3 GetLocation() {
		return location;
	}

	public Point3 GetOrigin() {
		return origin;
	}

	public void Move(Vec3 m) {
		location.Add(m);
	}

	public void Rotate(Vec3 r) {
		rotation.Add(r);
	}
}
