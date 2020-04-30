import PixelVision.*;
import PixelVision.Math.Mat4x4;
import PixelVision.Math.Point3;
import PixelVision.Math.Vec3;
import PixelVision.Rendering.Color;
import PixelVision.Rendering.Draw;
import PixelVision.Math.Vec2;
import PixelVision.Rendering.Model;
import PixelVision.Rendering.ModelUtil;

import java.awt.event.KeyEvent;

import static PixelVision.Rendering.ModelUtil.*;

public class Driver {
    public static void main(String[] args) {
        Game g = new Game();

        try {
            g.Start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

class Game extends Engine {

    SimpleRenderer renderer;
    Model cube, cube2;

    public Game() {
        SetScreenSize(256, 256, 3);
        SetClearColor(Color.CYAN);
        SetFramesPerSecond(20.0f);
        SetUpdatesPerSecond(20.0f);
    }

    @Override
    public void Init() {
        SetTitle("Engine Implementation!");

        cube = LoadModelFromOBJ("Ball.obj");
        cube.LoadTexture("checkerBoard.png");

        cube.Translate(new Vec3(0, 0f, -2f));
        cube.Rotate(new Vec3((float)Math.toRadians(-90f), 0, 0));

        renderer = new SimpleRenderer(GetTarget());

        renderer.AddModel(cube);
    }

    @Override
    public void Update() {
        super.Update();

        cube.Rotate(new Vec3(0.05f, 0.05f, 0.05f));
    }

    @Override
    public void Render() {
        super.Render();
        renderer.Update();
    }
}