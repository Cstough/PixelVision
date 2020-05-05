package PixelVision.Rendering;

import PixelVision.Math.Point2;
import PixelVision.Math.Point3;
import PixelVision.Math.Vec3;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public abstract class ModelUtil {
    public static Model LoadModelFromOBJ(String filepath) {
        File objFile = new File(filepath);
        Scanner input;
        try {
            input = new Scanner(objFile);
        } catch(Exception e) {
            System.out.println("File Not Found: " + filepath);
            return null;
        }

        ArrayList<Point3> vertices = new ArrayList<>();
        ArrayList<Point2> textCoords = new ArrayList<>();
        ArrayList<Vec3> vertexNormals = new ArrayList<>();
        ArrayList<Triangle> triangles = new ArrayList<>();

        while(input.hasNextLine()) {

            String line = input.nextLine();

            if(line == null || line.equals("") || line.startsWith("#")) { }
            else{

                String[] split = line.split("\\s+");

                switch(split[0]) {
                    case "v":
                        vertices.add(new Point3(Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3])));
                        break;
                    case "vt":
                        textCoords.add(new Point2(Float.parseFloat(split[1]), Float.parseFloat(split[2])));
                        break;
                    case "vn":
                        vertexNormals.add(new Vec3(Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3])));
                        break;
                    case "f":
                        int[] verts = new int[] { Integer.parseInt(split[1].split("/")[0]),
                                                  Integer.parseInt(split[2].split("/")[0]),
                                                  Integer.parseInt(split[3].split("/")[0]) };
                        int[] tcoords = new int[] { Integer.parseInt(split[1].split("/")[1]),
                                                    Integer.parseInt(split[2].split("/")[1]),
                                                    Integer.parseInt(split[3].split("/")[1]) };
                        int[] vnorms = new int[] {  Integer.parseInt(split[1].split("/")[2]),
                                                    Integer.parseInt(split[2].split("/")[2]),
                                                    Integer.parseInt(split[3].split("/")[2]) };

                        //Create the Triangle
                        Point2[] texCoords = new Point2[tcoords.length];
                        for(int i = 0; i < tcoords.length; i++) {
                            texCoords[i] = textCoords.get(tcoords[i] - 1);
                        }

                        Vec3[] vertNorms = new Vec3[vnorms.length];
                        for(int i = 0; i < vnorms.length; i++) {
                            vertNorms[i] = vertexNormals.get(vnorms[i] - 1);
                        }

                        triangles.add(new Triangle(verts, texCoords, vertNorms));

                        break;
                }
            }
        }

        Vertex[] verts = new Vertex[vertices.size()];

        for(int i = 0; i < verts.length; i++) {
            verts[i] = new Vertex(vertices.get(i));
        }

        Triangle[] tris = new Triangle[triangles.size()];
        tris = triangles.toArray(tris);

        MeshData md = MeshData.CreateMeshData(verts, tris);

        Mesh m = Mesh.CreateFromMeshData(md);

        Model model = new Model(m, new Point3());

        return model;
    }
}
