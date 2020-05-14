package PixelVision.Rendering;

public class Material {

    public String name;
    public Bitmap texture;

    public Material() {
        name = "";
        texture = null;
    }

    public Material(String name, Bitmap texture) {
        this.name = name;
        this.texture = texture;
    }

    public String GetName() {
        return name;
    }

    public Bitmap GetTexture() {
        return texture;
    }
}
