package PixelVision;

import PixelVision.Math.Point2;
import PixelVision.Math.Vec2;
import PixelVision.Rendering.*;

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
        for(Model m : Models) {
            RenderModel(m);
        }
    }

    private void RenderModel(Model m) {

        Vertex[] modelVerts = WorldTransform(m);

        ProjectionMatrix(modelVerts);

        PerspectiveDivide(modelVerts);

        Point2[] screenVerts = new Point2[modelVerts.length];
        Triangle[] triangles = m.getMesh().GetTriangles();

        for(int t = 0; t < triangles.length; t++) {

            Triangle currentTriangle = triangles[t];

            Vertex a, b, c;

            a = modelVerts[currentTriangle.vertices[0] - 1];
            b = modelVerts[currentTriangle.vertices[1] - 1];
            c = modelVerts[currentTriangle.vertices[2] - 1];

            if(IsFaceVisible(a, b, c)){
                Point2 p0, p1, p2;

                p0 = a.position.toPoint2();
                p1 = b.position.toPoint2();
                p2 = c.position.toPoint2();

                p0 = ScaleVertexToScreen(p0);
                p1 = ScaleVertexToScreen(p1);
                p2 = ScaleVertexToScreen(p2);

                RasterizeTriangle(p0, p1, p2);
            }
        }
    }
}
