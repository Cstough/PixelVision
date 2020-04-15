package PixelVision.Rendering;

import PixelVision.Math.*;

import javax.sound.sampled.Clip;
import java.awt.image.Raster;
import java.util.ArrayList;

/*
 * This class sits in an implementation of the Engine. This Object does
 * everything from abstracting the rendering of meshes, to scaling the
 * rendered coordinates to the screen size.
 * 
 * Full List:
 * ~Scales Coordinates to ScreenSpace. (0, 0) is the center of the screen.
 * ~Performs triangle clipping of triangles that go outside the screen.
 * ~abstracts the rendering of meshes
 */

public final class SWRenderer {
	
	private Bitmap target;
	public ArrayList<Model> Models;
	Mat4x4 projectionMat, viewMat;
	Vec3 Camera, CameraDir, LightDir;

	ArrayList<Vec3> ClipSpaceVertices;
	ArrayList<Triangle> ClipSpaceTriangles;

	public SWRenderer() {
		Models = new ArrayList<Model>();
	}
	
	public void SetRenderTarget(Bitmap tar) {
		target = tar;
	}
	
	public void Init() {
		projectionMat = Mat4x4.GetProjection(120f, 0.1f, 10f);
		Camera = new Vec3();
		LightDir = new Vec3(0, 0, -1f);
	}
	
	public void Render() {
		for(Model m : Models) {
			RenderModel(m);
		}
	}

	private void RenderModel(Model model) {

		/*
		Vec3[] vertices = new Vec3[model.getMesh().GetVertexData().length];
		Triangle[] tris = new Triangle[model.getMesh().GetTriangles().length];

		System.arraycopy(model.getMesh().GetVertexData(), 0, vertices, 0, model.getMesh().GetVertexData().length);
		System.arraycopy(model.getMesh().GetTriangles(), 0, tris, 0, model.getMesh().GetTriangles().length);

		//Transform all vertices
		for(int v = 0; v < vertices.length; v++) {
			vertices[v] = WorldTransform(vertices[v], model);
			vertices[v] = ViewTransform(vertices[v]);
			vertices[v] = ProjectionTransform(vertices[v]);
		}

		//Clipping
		ClipSpaceVertices = new ArrayList<>();
		ClipSpaceTriangles = new ArrayList<>();

		for(int t = 0; t < tris.length; t++) {

			//Generate ArrayList of new Triangles from currentTriangle(tris[t])


			//Perspective Division
			for(Vec3 vertex : ClipSpaceVertices) {
				vertex = PerspectiveDivide(vertex);
			}

			for(Triangle tri : ClipSpaceTriangles) {

				Vec2 v0, v1, v2;

				v0 = ClipSpaceVertices.get(tri.Verts[0]).toVec2();
				v1 = ClipSpaceVertices.get(tri.Verts[1]).toVec2();
				v2 = ClipSpaceVertices.get(tri.Verts[2]).toVec2();

				v0 = ScaleVertexToScreen(v0);
				v1 = ScaleVertexToScreen(v1);
				v2 = ScaleVertexToScreen(v2);

				RasterizeTriangle(v0, v1, v2);
			}
		}
		 */

		//create 2 arrays for the local Vertices and Triangles of our model
		Vec3[] vertices = new Vec3[model.getMesh().GetVertexData().length];
		Triangle[] triangles = new Triangle[model.getMesh().GetTriangles().length];

		//copy them from the actual model meshdata arrays
		System.arraycopy(model.getMesh().GetVertexData(), 0, vertices, 0, model.getMesh().GetVertexData().length);
		System.arraycopy(model.getMesh().GetTriangles(), 0, triangles, 0, model.getMesh().GetTriangles().length);

		//start by transforming all the vertices of the model
		for(int i = 0; i < vertices.length; i++) {
			vertices[i] = WorldTransform(vertices[i], model);
			vertices[i] = ViewTransform(vertices[i]);
			vertices[i] = ProjectionTransform(vertices[i]);
		}

		//From this point on, we will be using the ClipSpaceVertices and ClipSpaceTriangles arrays since we are in clip space!
		//now we will iterate through every triangle and determine if it needs to be back face culled
		for(int t = 0; t < triangles.length; t++) {
			//get triangle normal
			//calculate

		}


	}

	private Vec3 WorldTransform(Vec3 vert, Model currentModel) {

		vert = Mat4x4.Mul(Mat4x4.GetScale(currentModel.getScale()), vert); //Scaling

		vert = Mat4x4.Mul(Mat4x4.GetTranslation(currentModel.getOrigin().GetNegative()), vert); //Rotation about models origin
		vert = Mat4x4.Mul(Mat4x4.GetRotation(currentModel.getRotation()), vert);
		vert = Mat4x4.Mul(Mat4x4.GetTranslation(currentModel.getOrigin()), vert);

		vert = Mat4x4.Mul(Mat4x4.GetTranslation(currentModel.getLocation()), vert); //Translation

		return vert;
	}

	private Vec3 ViewTransform(Vec3 vert) {
		//not yet implemented
		return vert;
	}

	private Vec3 ProjectionTransform(Vec3 vert) {
		return Mat4x4.Mul(projectionMat, vert);
	}

	private Vec3 PerspectiveDivide(Vec3 vert) {
		if(vert.w != 1 || vert.w != 0) {
			vert.x /= vert.w; vert.y /= vert.w; vert.z /= vert.w;
		}
		return vert;
	}

	private Vec2 ScaleVertexToScreen(Vec2 vert) {

		vert.x += 1.0f;
		vert.y += 1.0f;
		vert.x *= 0.5f * (float)target.GetWidth();
		vert.y *= 0.5f * (float)target.GetHeight();

		return vert;
	}

	private void RasterizeTriangle(Vec2 v1, Vec2 v2, Vec2 v3) {
		Color c = new Color(Color.RED);
		Draw.DrawLine(v1, v2, target, c);
		Draw.DrawLine(v1, v3, target, c);
		Draw.DrawLine(v2, v3, target, c);
	}


}
