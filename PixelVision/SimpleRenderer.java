package PixelVision;

import PixelVision.Math.*;
import PixelVision.Rendering.*;

import java.security.SecureClassLoader;
import java.util.ArrayList;

public class SimpleRenderer extends SWRenderer {

    private ArrayList<Model> Models;

    public SimpleRenderer(Bitmap renderTarget) {
        super();
        SetRenderTarget(renderTarget);
        SetProjectionMatrix(90f, 0.1f, 100f);
        Models = new ArrayList<>();
    }

    public void AddModel(Model m) {
        Models.add(m);
    }

    public void Update() {
        ClearZBuffer();
        for(Model m : Models) {
            RenderModel(m);
        }
    }

    private void RenderModel(Model m) {

        Vertex[] modelVerts = WorldTransform(m);

        ProjectionMatrix(modelVerts);

        PerspectiveDivide(modelVerts);

        for(Vertex v : modelVerts) {
            ScaleVertexToScreen(v.position);
        }

        Triangle[] triangles = m.getMesh().GetTriangles();

        for(int t = 0; t < triangles.length; t++) {

            Triangle currentTriangle = triangles[t];

            Vertex a, b, c;

            a = modelVerts[currentTriangle.vertices[0] - 1];
            b = modelVerts[currentTriangle.vertices[1] - 1];
            c = modelVerts[currentTriangle.vertices[2] - 1];

            if(IsFaceVisible(a, b, c)){

                Color col = new Color(Color.YELLOW);

                Vec3 norm = currentTriangle.vertexNormals[0];

                norm = Mat4x4.Mul(Mat4x4.GetRotation(m.getRotation().toVec3()), norm);

                col = CalculateShadeColor(col, norm);
                FlatShadeTriangle(a, b, c, col, currentTriangle.vertexNormals[0], currentTriangle.vertexNormals[1], currentTriangle.vertexNormals[2]);
            }
        }
    }
}
