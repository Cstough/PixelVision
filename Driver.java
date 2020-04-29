import PixelVision.*;
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
        SetClearColor(Color.BLACK);
        SetFramesPerSecond(60.0f);
        SetUpdatesPerSecond(60.0f);
    }

    @Override
    public void Init() {
        SetTitle("Engine Implementation!");

        cube = LoadModelFromOBJ("donut.obj");

        cube.Translate(new Vec3(0, 0, -2f));
        cube.Rotate(new Vec3(0, 0, (float) Math.toRadians(180f)));

        renderer = new SimpleRenderer(GetTarget());

        renderer.AddModel(cube);
    }

    @Override
    public void Update() {
        super.Update();

        if(InputScanner.GetKeyDown(KeyEvent.VK_W)) {
            cube.Rotate(new Vec3(-.05f, 0, 0));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_A)) {
            cube.Rotate(new Vec3(0, 0, 0.05f));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_S)) {
            cube.Rotate(new Vec3(.05f, 0, 0));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_D)) {
            cube.Rotate(new Vec3(0, 0, -0.05f));
        }
    }

    @Override
    public void Render() {
        super.Render();
        renderer.Update();
    }
}