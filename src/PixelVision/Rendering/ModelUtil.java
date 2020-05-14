package PixelVision.Rendering;

import PixelVision.Math.Point2;
import PixelVision.Math.Point3;
import PixelVision.Math.Vec3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class ModelUtil {

    public static Model LoadModelFromOBJ(String filepath) {

        File objFile = new File(filepath);
        Scanner input;
        try {
            input = new Scanner(objFile);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + filepath);
            return null;
        }

        Material[] materials = null;

        ArrayList<MeshGroup> meshGroups = new ArrayList<>();
        ArrayList<Point3> vertices = new ArrayList<>();
        ArrayList<Vec3> vertexNormals = new ArrayList<>();
        ArrayList<Point2> textureCoords = new ArrayList<>();

        int currentGroup = -1;

        while(input.hasNextLine()) {
            String line = input.nextLine();

            if(line == null || line.equals("") || line.startsWith("#")) {
                continue;
            }

            String[] split = line.split("\\s+");

            switch(split[0]) {

                case "mtllib":
                    materials = LoadMaterialsFromMTL(objFile.getParentFile() + "\\" + split[1]);
                    break;
                case "v":
                    vertices.add(new Point3(Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3])));
                    break;
                case "vn":
                    vertexNormals.add(new Vec3(Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3])));
                    break;
                case "vt":
                    textureCoords.add(new Point2(Float.parseFloat(split[1]), Float.parseFloat(split[2])));
                    break;
                case "g":
                    MeshGroup mg = new MeshGroup();
                    mg.SetName(split[1]);
                    meshGroups.add(mg);
                    currentGroup++;
                    break;
                case "usemtl":
                    for(Material mtl : materials) {
                        if(mtl.name.equals(split[1])) {
                            meshGroups.get(currentGroup).SetMaterial(mtl);
                            break;
                        }
                    }
                    break;
                case "s":
                    meshGroups.get(currentGroup).SetSmoothState((Integer.parseInt(split[1]) == 1) ? true : false);
                    break;
                case "f":

                    int[] v = new int[] { Integer.parseInt(split[1].split("/")[0]),
                            Integer.parseInt(split[2].split("/")[0]),
                            Integer.parseInt(split[3].split("/")[0]) };
                    int[] tc = new int[] { Integer.parseInt(split[1].split("/")[1]),
                            Integer.parseInt(split[2].split("/")[1]),
                            Integer.parseInt(split[3].split("/")[1]) };
                    int[] vn = new int[] {  Integer.parseInt(split[1].split("/")[2]),
                            Integer.parseInt(split[2].split("/")[2]),
                            Integer.parseInt(split[3].split("/")[2]) };

                    Point3[] verts = new Point3[]{ new Point3(vertices.get(v[0] - 1)), new Point3(vertices.get(v[1] - 1)), new Point3(vertices.get(v[2] - 1))};
                    Point2[] texCoords = new Point2[]{new Point2(textureCoords.get(tc[0] - 1)), new Point2(textureCoords.get(tc[1] - 1)), new Point2(textureCoords.get(tc[2] - 1))};
                    Vec3[] vertNorms = new Vec3[]{new Vec3(vertexNormals.get(vn[0] - 1)), new Vec3(vertexNormals.get(vn[1] - 1)), new Vec3(vertexNormals.get(vn[2] - 1))};

                    Triangle tri = new Triangle(verts, texCoords, vertNorms);

                    meshGroups.get(currentGroup).GetTrianglesReference().add(tri);

                    break;
            }
        }

        return new Model(meshGroups.toArray(new MeshGroup[meshGroups.size()]));
    }

    private static Material[] LoadMaterialsFromMTL(String filepath) {

        File matFile = new File(filepath);
        Scanner input;
        try {
            input = new Scanner(matFile);
        } catch(Exception e) {
            System.out.println("File Not Found: " + filepath);
            return null;
        }

        ArrayList<Material> materials = new ArrayList<>();

        Material currentMaterial = null;

        while(input.hasNextLine()) {
            String line = input.nextLine();

            if(line == null || line == "" || line.startsWith("#")) {
                continue;
            }
            else{

                line = line.replace("\t", "");
                String[] split = line.split("\\s+");

                switch(split[0]) {

                    case "newmtl": //create a new material

                        /*
                        Material mat = new Material();

                        mat.name = split[1];

                        line = line.replace("\t", "");

                        String[] params = line.split("\\s+");

                        line = (input.hasNextLine()) ? input.nextLine() : "";
                        line = line.replace("\t", "");
                        params = line.split("\\s+");

                        while(!params[0].contains("newmtl")) {

                            switch(params[0]) {
                                case "map_Ka":
                                case "map_Kd":
                                    if(mat.texture == null)
                                        mat.texture = Bitmap.LoadSprite(matFile.getParentFile().getPath() + "\\" + params[1]);
                                    break;
                            }

                            line = (input.hasNextLine()) ? input.nextLine() : "";
                            line = line.replace("\t", "");
                            params = line.split("\\s+");

                        }

                        materials.add(mat);
                        break;
                         */
                        currentMaterial = new Material();
                        currentMaterial.name = split[1];
                        break;
                    case "map_Kd":
                        if(currentMaterial != null) {
                            currentMaterial.texture = Bitmap.LoadSprite(matFile.getParentFile().getPath() + "\\" + split[1]);
                            materials.add(currentMaterial);
                            currentMaterial = null;
                        }
                }

            }
        }

        return materials.toArray(new Material[materials.size()]);
    }
}
