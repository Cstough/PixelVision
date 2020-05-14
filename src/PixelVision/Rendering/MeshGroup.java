package PixelVision.Rendering;

import java.util.ArrayList;

//This class will contain the information of an obj group
public class MeshGroup {

    private String name;
    private ArrayList<Triangle> triangles;
    private Material material;
    private boolean smooth;

    public MeshGroup() {
        triangles = new ArrayList<>();
    }

    //returns a deep copy of the triangles arraylist
    public ArrayList<Triangle> GetTrianglesDeepCopy() {
        ArrayList<Triangle> triangles_dc = new ArrayList<>();
        for(Triangle tri : triangles) {
            triangles_dc.add(tri.GetTriangleDeepCopy());
        }
        return triangles_dc;
    }

    public ArrayList<Triangle> GetTrianglesReference() {
        return triangles;
    }

    public Material GetMaterial() {
        return material;
    }

    public String GetName() {
        return name;
    }

    public boolean GetSmoothState() {
        return smooth;
    }

    public void SetTriangles(ArrayList<Triangle> triangles) {
        this.triangles = triangles;
    }

    public void SetMaterial(Material mat) {
        material = mat;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public void SetSmoothState(boolean smooth) {
        this.smooth = smooth;
    }
}
