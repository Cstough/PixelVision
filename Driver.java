import PixelVision.*;
import PixelVision.Math.Vec3;
import PixelVision.Rendering.Color;
import PixelVision.Rendering.Model;

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
        SetFramesPerSecond(15.0f);
        SetUpdatesPerSecond(30.0f);
    }

    @Override
    public void Init() {
        SetTitle("Engine Implementation!");

        cube = LoadModelFromOBJ("smoothLeon.obj");
        cube.LoadTexture("checkerBoard.png");

        cube.Translate(new Vec3(0, 2f, -5));
        cube.Rotate(new Vec3((float) Math.toRadians(-90), 0, 0));
        renderer = new SimpleRenderer(GetTarget());

        renderer.AddModel(cube);
    }

    @Override
    public void Update() {
        super.Update();

        if(InputScanner.GetKeyDown(KeyEvent.VK_W)) {
            renderer.MoveCameraPosition(new Vec3(0, 0, -0.05f));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_A)) {
            renderer.MoveCameraPosition(new Vec3(-0.05f, 0, 0));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_S)) {
            renderer.MoveCameraPosition(new Vec3(0, 0, 0.05f));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_D)) {
            renderer.MoveCameraPosition(new Vec3(0.05f, 0, 0));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_UP)) {
            renderer.RotateCamera(new Vec3(0.05f, 0, 0));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_DOWN)) {
            renderer.RotateCamera(new Vec3(-0.05f, 0, 0));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_LEFT)) {
            renderer.RotateCamera(new Vec3(0, -0.05f, 0));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_RIGHT)) {
            renderer.RotateCamera(new Vec3(0, 0.05f, 0));
        }

    }

    @Override
    public void Render() {
        super.Render();
        renderer.Update();
    }
}