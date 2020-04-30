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

        //modelVerts = ViewMatrix(modelVerts, m);

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

                Vec3[] norms = new Vec3[]{currentTriangle.vertexNormals[0], currentTriangle.vertexNormals[1], currentTriangle.vertexNormals[2]};

                for(int i = 0; i < 3; i++) {
                    norms[i] = Mat4x4.Mul(Mat4x4.GetRotation(m.getRotation().toVec3()), norms[i]);
                }

                FlatShadeTriangle(a, b, c, currentTriangle.textCoords[0], currentTriangle.textCoords[1], currentTriangle.textCoords[2], m.getTexture(), norms);
            }
        }
    }
}
