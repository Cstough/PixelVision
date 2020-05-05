package PixelVision.Rendering;

import PixelVision.Rendering.*;
import PixelVision.Math.*;
import java.util.ArrayList;

//this class improves upon the old SWRenderer class
public abstract class SWRenderer2 {

    //Public Globals
    protected Point3 CameraPosition;
    protected Vec3 CameraDirection;

    protected Vec3 GlobalLightDirection;
    protected Color GlobalLightColor;
    protected float GlobalLightIntensity;

    protected Mat4x4 ProjectionMatrix, ViewMatrix;

    protected Bitmap Target;
    protected float[][] ZBuffer;

    private Model currentModel; //allows render function signitures to be smaller;

    //Settings
    protected boolean DrawWireframe;
    protected boolean UseGlobalLighting;

    public SWRenderer2() {
        CameraDirection = new Vec3();
        CameraPosition = new Point3();
        GlobalLightColor = new Color(Color.WHITE);
        GlobalLightDirection = new Vec3(1, 1, 1);
        GlobalLightIntensity = 1.0f;
    }

    public void SetRenderTarget(Bitmap target) {
        Target = target;
        ZBuffer = new float[Target.GetWidth()][Target.GetHeight()];
    }

    public void SetProjectionMatrix(Mat4x4 proj) {
        ProjectionMatrix = proj;
    }

    //Camera Functions

    public void SetCameraPosition(Point3 pos) {
        CameraPosition = pos;
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

    //Transformation Functions

    public void SetCurrentModel(Model m) {
        currentModel = m.clone();
    }

    public void WorldTransform() {
        Mat4x4 translation = Mat4x4.GetTranslation(currentModel.getLocation().toVec3());
        Mat4x4 rotation = Mat4x4.GetRotation(currentModel.getRotation().toVec3());
        Mat4x4 scaling = Mat4x4.GetScale(currentModel.getScale().toVec3());

        for(Vertex v : currentModel.getMesh().GetVertexDataRef()) {
            Mat4x4.Mul(translation, v.position);
            Mat4x4.Mul(rotation, v.position);
            Mat4x4.Mul(scaling, v.position);
        }
    }

    public void ViewTransform() {
        //TODO: Add View Matrix Code
    }

    public void ProjectionTransform() {
        for(Vertex v : currentModel.getMesh().GetVertexDataRef()) {
            Mat4x4.Mul(ProjectionMatrix, v.position);
        }
    }

    public void PerspectiveDivide() {
        for(Vertex v : currentModel.getMesh().GetVertexDataRef()) {
            if(v.position.w != 1 || v.position.w != 0) {
                v.position.x /= v.position.w;
                v.position.y /= v.position.w;
                v.position.z /= v.position.w;
            }
        }
    }

    //Utility Function

    public void ClearZBuffer() {
        for(int i = 0; i < ZBuffer.length; i++) {
            for(int j = 0; j < ZBuffer[i].length; j++) {
                ZBuffer[i][j] = Float.MIN_VALUE;
            }
        }
    }

    public Vec3 GetTriangleNormal(Triangle face) {
        Vec3 ab = Vec3.Diff(currentModel.getMesh().GetVertexDataRef()[face.vertices[1]].position,
                currentModel.getMesh().GetVertexDataRef()[face.vertices[0]].position);
        Vec3 bc = Vec3.Diff(currentModel.getMesh().GetVertexDataRef()[face.vertices[2]].position,
                currentModel.getMesh().GetVertexDataRef()[face.vertices[1]].position);
        return Vec3.Normalize(Vec3.Cross(ab, bc));
    }

    public boolean IsFaceVisible(Triangle face) {
        Vec3 surfaceNormal = GetTriangleNormal(face);
        float visibility = Vec3.Dot(surfaceNormal,
                Vec3.Cross(currentModel.getMesh().GetVertexDataRef()[face.vertices[0]].position.toVec3(),
                        CameraPosition.toVec3()));
        return visibility < 0.0f;
    }

    public void ScaleVertexToScreen(Point3 vert) {
        vert.x += 1.0f;
        vert.y += 1.0f;
        vert.x *= 0.5f * (float)Target.GetWidth();
        vert.y *= 0.5f * (float)Target.GetHeight();
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
}
