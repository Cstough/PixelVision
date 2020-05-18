package PixelVision.Rendering;

import javax.swing.text.View;
import java.util.ArrayList;

public class RenderingEngine extends Rasterizer{

    private ArrayList<Model> models;

    public RenderingEngine(Bitmap target) {
        SetRenderTarget(target);
        models = new ArrayList<>();
    }

    public void AddModel(Model model) {
        models.add(model);
    }

    public void Render() {
        ClearZBuffer();
        for(Model model : models) {
            RenderModel(model);
        }
    }

    public void RenderModel(Model model) {

        Model CopyModel = model.GetModelDeepCopy();

        WorldTransform(CopyModel);
        ViewTransform(CopyModel);
        ProjectionTransform(CopyModel);

        //TODO: Clipping!!!

        PerspectiveDivide(CopyModel);

        ScaleModelToScreen(CopyModel);

        for(int mg = 0; mg < CopyModel.GetMeshGroupsReference().length; mg++) {
            for(int tri = 0; tri < CopyModel.GetMeshGroupsReference()[mg].GetTrianglesReference().size(); tri++) {
                Triangle triangle = CopyModel.GetMeshGroupsReference()[mg].GetTrianglesReference().get(tri);
                if(IsFaceVisible(triangle))
                    RasterizeTriangle(triangle, CopyModel.GetMeshGroupsReference()[mg]);
            }
        }
    }
}
