package PixelVision.Rendering;

import PixelVision.Math.Mat4x4;
import PixelVision.Math.Point2;
import PixelVision.Math.Point3;
import PixelVision.Math.Vec3;

public abstract class Rasterizer {

    private Bitmap _renderTarget;
    private float[][] _zBuffer;

    private Mat4x4 _projectionMatrix;

    private Vec3 _globalLightRotation;

    private Point3 CameraLocation;
    private Vec3 CameraRotation;

    public Rasterizer() {

    }

    public void SetProjectionMatrix(Mat4x4 projectionMatrix) {
        _projectionMatrix = projectionMatrix;
    }

    public void SetGlobalLightingRotation(Vec3 lightRotation) {
        _globalLightRotation = lightRotation;
    }

    public void SetRenderTarget(Bitmap renderTarget) {
        _renderTarget = renderTarget;
        _zBuffer = new float[renderTarget.GetWidth()][renderTarget.GetHeight()];
    }

    //Rendering Functions
    protected boolean IsFaceVisible(Triangle triangle) {
        return true;
    }

    protected void WorldTransform(Model model) {
        Mat4x4 scaling = Mat4x4.GetScale(model.GetScale().toVec3());
        Mat4x4 rotation = Mat4x4.GetRotation(model.GetRotation().toVec3());
        Mat4x4 translation = Mat4x4.GetTranslation(model.GetLocation().toVec3());
        Mat4x4 origin_translation = Mat4x4.GetTranslation(model.GetOrigin().toVec3());
        Mat4x4 origin_translation_neg = Mat4x4.GetTranslation(model.GetOrigin().toVec3().GetNegative());

        for(MeshGroup meshGroup : model.GetMeshGroupsReference()) {
            for(Triangle triangle : meshGroup.GetTrianglesReference()) {
                for(int v = 0; v < triangle.vertices.length; v++) {
                    Point3 vert = triangle.vertices[v];
                    Point3 transVert;

                    transVert = Mat4x4.Mul(scaling, vert);

                    transVert = Mat4x4.Mul(origin_translation_neg, transVert);
                    transVert = Mat4x4.Mul(rotation, transVert);
                    transVert = Mat4x4.Mul(origin_translation, transVert);

                    transVert = Mat4x4.Mul(translation, transVert);

                    triangle.vertices[v] = transVert;
                }
            }
        }
    }

    protected void ViewTransform(Model model) {

    }

    protected void ProjectionTransform(Model model) {
        for(MeshGroup meshGroup : model.GetMeshGroupsReference()) {
            for(Triangle triangle : meshGroup.GetTrianglesReference()) {
                for(int v = 0; v < triangle.vertices.length; v++) {
                    triangle.vertices[v] = Mat4x4.Mul(_projectionMatrix, triangle.vertices[v]);
                }
            }
        }
    }

    protected void PerspectiveDivide(Model model) {
        for(MeshGroup meshGroup : model.GetMeshGroupsReference()) {
            for(Triangle triangle : meshGroup.GetTrianglesReference()) {
                for(int v = 0; v < triangle.vertices.length; v++) {
                    Point3 vert = triangle.vertices[v];
                    if(vert.w != 1 || vert.w != 0) {
                        vert.x /= vert.w;
                        vert.y /= vert.w;
                        vert.z /= vert.w;
                    }
                    triangle.vertices[v] = vert;
                }
            }
        }
    }

    protected void ClearZBuffer() {
        for(int i = 0; i < _zBuffer.length; i++) {
            for(int j = 0; j < _zBuffer[i].length; j++) {
                _zBuffer[j][i] = Float.MIN_VALUE;
            }
        }
    }

    protected void ScaleModelToScreen(Model model) {
        for(MeshGroup meshGroup : model.GetMeshGroupsReference()) {
            for(Triangle triangle : meshGroup.GetTrianglesReference()) {
                for(int v = 0; v < triangle.vertices.length; v++) {
                    Point3 transVert = triangle.vertices[v];
                    transVert.x += 1.0f;
                    transVert.y += 1.0f;
                    transVert.x *= 0.5f * (float)_renderTarget.GetWidth();
                    transVert.y *= 0.5f * (float)_renderTarget.GetHeight();
                    triangle.vertices[v] = transVert;
                }
            }
        }
    }

    protected void RasterizeTriangle(Triangle triangle, MeshGroup mg) {

        if(mg.GetMaterial() == null) {return;}

        Point2 v1, v2, v3;
        Point2 p = new Point2(0, 0);
        float Area;
        float w0, w1, w2;
        float s, t;

        v1 = triangle.vertices[0].toPoint2();
        v2 = triangle.vertices[1].toPoint2();
        v3 = triangle.vertices[2].toPoint2();

        int minX = (int) GetMin(v1.x, v2.x, v3.x);
        int maxX = (int) GetMax(v1.x, v2.x, v3.x);
        int minY = (int) GetMin(v1.y, v2.y, v3.y);
        int maxY = (int) GetMax(v1.y, v2.y, v3.y);

        minX = Math.max(minX, 0);
        minY = Math.max(minY, 0);
        maxX = Math.min(maxX, _renderTarget.GetWidth() - 1);
        maxY = Math.min(maxY, _renderTarget.GetHeight() - 1);

        for (int i = minY; i <= maxY; i++) {
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

                    float z = 1 / (w0 * (triangle.vertices[0].w) + w1 * (triangle.vertices[1].w) + w2 * (triangle.vertices[2].w));

                    if (_zBuffer[j][i] < z) {
                        s = w0 * triangle.textCoords[0].x + w1 * triangle.textCoords[1].x + w2 * triangle.textCoords[2].x;
                        t = w0 * triangle.textCoords[0].y + w1 * triangle.textCoords[1].y + w2 * triangle.textCoords[2].y;

                        Color pixelColor;

                        if(mg.GetMaterial() != null) {
                            Bitmap texture = mg.GetMaterial().GetTexture();

                            s *= 0.5f;
                            t *= 0.5f;

                            pixelColor = texture.GetPixel((int)(s * texture.GetWidth()), (int)(t * texture.GetHeight()));
                        }
                        else {
                            pixelColor = new Color(Color.MAGENTA);
                        }

                        Vec3 norm = new Vec3();

                        if(!mg.GetSmoothState()) {

                            norm.x = w0 * triangle.vertexNormals[0].x + w1 * triangle.vertexNormals[1].x + w2 * triangle.vertexNormals[2].x;
                            norm.y = w0 * triangle.vertexNormals[0].y + w1 * triangle.vertexNormals[1].y + w2 * triangle.vertexNormals[2].y;
                            norm.z = w0 * triangle.vertexNormals[0].z + w1 * triangle.vertexNormals[1].z + w2 * triangle.vertexNormals[2].z;

                            norm = Vec3.Normalize(norm);
                        }
                        else{
                            norm = triangle.vertexNormals[0].GetNegative();
                        }

                        //pixelColor = CalculateShadeColor(pixelColor, norm);

                        _renderTarget.SetPixel(j, i, pixelColor);
                        _zBuffer[j][i] = z;
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

    protected Color CalculateShadeColor(Color c, Vec3 normal) {

        byte[] comps = c.GetComponents();

        float val = Vec3.Dot(Vec3.Normalize(_globalLightRotation), normal);

        val += 1.0f;

        float perc = (val / 2f) * 255;

        for(int i = 1; i < 4; i++) {
            comps[i] = (byte) (comps[i] * (255 - perc));
        }

        return new Color(comps);
    }
}
