package PixelVision.Rendering;

import PixelVision.Math.*;
import PixelVision.Rendering.*;
import java.util.ArrayList;
import java.nio.ByteBuffer;
import java.util.Vector;

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

/*

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

 */

public abstract class SWRenderer {

	//Renderer Global Variables
	protected Point3 CameraPosition;
	protected Vec3 CameraDirection;
	protected Vec3 GlobalLightDirection;
	protected Mat4x4 ProjectionMatrix, ViewMatrix;
	protected Bitmap Target;
	protected float[][] ZBuffer;
	protected Frustum ViewingFrustum;

	public SWRenderer() {
		CameraPosition = new Point3(0, 0, 0);
		CameraDirection = new Vec3(0, 0, 0);
		GlobalLightDirection = Vec3.Normalize(new Vec3(-1f, -1f, -1f));
	}

	public void SetProjectionMatrix(float FOV, float Near, float Far) {
		ProjectionMatrix = Mat4x4.GetProjection(90f, Target.GetWidth() / Target.GetHeight(), Near, Far);
	}

	public void SetViewMatrix(Mat4x4 view) {
		ViewMatrix = view;
	}

	public void SetRenderTarget(Bitmap target) {
		Target = target;
		ZBuffer = new float[target.GetWidth()][target.GetHeight()];
	}

	public void SetCameraPosition(Point3 pos) {
		CameraPosition = pos;
	}

	public void MoveCameraPosition(Vec3 move) {
		CameraPosition.Add(move);
	}

	public Point3 GetCameraPosition() {
		return CameraPosition;
	}

	public void SetCameraRotation(Vec3 rot) {
		CameraDirection = rot;
	}

	public Vec3 GetCameraRotation() {
		return CameraDirection;
	}

	public void RotateCamera(Vec3 rot) {
		CameraDirection.Add(rot);
	}

	//This function needs a Vertex[] and a transform, so passing the whole model would suffice
	public Vertex[] WorldTransform(Model m) {

		Vertex[] vertices = m.getMesh().GetVertexDataClone();

		Point3 location = m.getLocation();
		Point3 origin = m.getOrigin();
		Point3 rotation = m.getRotation();
		Point3 scale = m.getScale();

		Mat4x4 t = Mat4x4.GetTranslation(new Vec3(location.x, location.y, location.z));
		Mat4x4 r = Mat4x4.GetRotation(new Vec3(rotation.x, rotation.y, rotation.z));
		Mat4x4 s = Mat4x4.GetScale(new Vec3(scale.x, scale.y, scale.z));

		for(Vertex v : vertices) {
			v.position = Mat4x4.Mul(r, v.position);
			v.position = Mat4x4.Mul(t, v.position);
			v.position = Mat4x4.Mul(s, v.position);
		}

		return vertices;
	}

	public void ViewMatrix(Vertex[] verts) {

		Mat4x4 CamTrans = Mat4x4.GetTranslation(CameraPosition.toVec3());
		Mat4x4 CamRot = Mat4x4.GetRotation(CameraDirection);

		Mat4x4 Cameratransform = Mat4x4.Mul(CamTrans, CamRot);

		Mat4x4 view = Mat4x4.GetView(Cameratransform);

		for(Vertex v : verts) {
			v.position = Mat4x4.Mul(view, v.position);
		}

	}

	public void ProjectionMatrix(Vertex[] vertices) {
		for(Vertex v : vertices) {
			v.position = Mat4x4.Mul(ProjectionMatrix, v.position);
		}
	}

	public void PerspectiveDivide(Vertex[] vertices) {
	    for(Vertex v : vertices) {
            if(v.position.w != 1 || v.position.w != 0) {
                v.position.x /= v.position.w; v.position.y /= v.position.w; v.position.z /= v.position.w;
            }
        }
    }

	public boolean IsFaceVisible(Vertex a, Vertex b, Vertex c) {
		Vec3 surfaceNormal = GetTriangleNormal(a.position, b.position, c.position);

		float vis = surfaceNormal.x * (a.position.x - CameraPosition.x)
					+ surfaceNormal.y * (a.position.y - CameraPosition.y)
					+ surfaceNormal.z * (a.position.z - CameraPosition.z);

		return vis < 0f;
	}

    public void ScaleVertexToScreen(Point3 vert) {

        vert.x += 1.0f;
        vert.y += 1.0f;
        vert.x *= 0.5f * (float)Target.GetWidth();
        vert.y *= 0.5f * (float)Target.GetHeight();
    }

    public void ClearZBuffer() {
		for(int i = 0; i < ZBuffer.length; i++) {
			for(int j = 0; j < ZBuffer[i].length; j++) {
				ZBuffer[i][j] = Float.MIN_VALUE;
			}
		}
	}

    public void FlatShadeTriangle(Vertex a, Vertex b, Vertex c, Point2 at, Point2 bt, Point2 ct, Bitmap texture, Vec3[] vNorms) {

		Point2 v1, v2, v3;
		Point2 p = new Point2(0, 0);
		float Area;
		float w0, w1, w2;
		float s, t;

		v1 = a.position.toPoint2();
		v2 = b.position.toPoint2();
		v3 = c.position.toPoint2();

		int minX = (int) GetMin(v1.x, v2.x, v3.x);
		int maxX = (int) GetMax(v1.x, v2.x, v3.x);
		int minY = (int) GetMin(v1.y, v2.y, v3.y);
		int maxY = (int) GetMax(v1.y, v2.y, v3.y);

		minX = Math.max(minX, 0);
		minY = Math.max(minY, 0);
		maxX = Math.min(maxX, Target.GetWidth() - 1);
		maxY = Math.min(maxY, Target.GetHeight() - 1);

		for(int i = minY; i <= maxY; i++) {
			for (int j = minX; j <= maxX; j++) {

				p.x = j + 0.5f;
				p.y = i + 0.5f;

				Area = GetTriArea(v1, v2, v3);

				w0 = Orient2(v2, v3, p);
				w1 = Orient2(v3, v1, p);
				w2 = Orient2(v1, v2, p);

				if (w0 > 0.0f && w1 > 0.0f && w2 > 0.0f) {

					w0 /= Area;
					w1 /= Area;
					w2 /= Area;

					float z = 1 / (w0 * (a.position.w) + w1 * (b.position.w) + w2 * (c.position.w));

					if(ZBuffer[j][i] < z) {

						s = w0 * at.x + w1 * bt.x + w2 * ct.x;
						t = w0 * at.y + w1 * bt.y + w2 * ct.y;

						Color col = texture.GetPixel((int)Math.floor(s*texture.GetWidth() / 2), (int)Math.floor(t*texture.GetHeight() / 2));

						Vec3 norm = new Vec3();

						norm.x = w0 * vNorms[0].x + w1 * vNorms[1].x + w2 * vNorms[2].x;
						norm.y = w0 * vNorms[0].y + w1 * vNorms[1].y + w2 * vNorms[2].y;
						norm.z = w0 * vNorms[0].z + w1 * vNorms[1].z + w2 * vNorms[2].z;

						norm = Vec3.Normalize(norm);

						col = CalculateShadeColor(col, norm);

						Target.SetPixel(j, i, col);
						ZBuffer[j][i] = z;
					}
				}
			}
		}
	}

	protected float GetMin(float a, float b, float c) {
		return Math.min(a, Math.min(b, c));
	}

	protected float GetMax(float a, float b, float c) {
		return Math.max(a, Math.max(b, c));
	}

	protected float Orient2(Point2 a, Point2 b, Point2 c) {
		return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
	}

	protected float GetTriArea(Point2 v1, Point2 v2, Point2 v3) {
		return (float)Math.abs(v1.x * (v2.y - v3.y) + v2.x * (v3.y - v1.y) + v3.x * (v1.y - v2.y)) * 0.5f;
	}

	protected Vec3 GetTriangleNormal(Point3 a, Point3 b, Point3 c) {
		Vec3 ab = Vec3.Diff(b.toVec3(), a.toVec3());
		Vec3 bc = Vec3.Diff(b.toVec3(), c.toVec3());
		return Vec3.Normalize(Vec3.Cross(ab, bc));
	}

	protected Color CalculateShadeColor(Color c, Vec3 normal) {

		byte[] comps = c.GetComponents();

		float val = Vec3.Dot(Vec3.Normalize(GlobalLightDirection), normal);

		val += 1.0f;

		float perc = (val / 2f) * 255;

		for(int i = 1; i < 4; i++) {
			comps[i] = (byte) (comps[i] * (255 - perc));
		}

		return new Color(comps);
	}
}
