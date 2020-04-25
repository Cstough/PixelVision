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
    Model cube;

    public Game() {
        SetScreenSize(200, 200, 4);
        SetTitle("Engine Implementation!");
        SetClearColor(Color.BLACK);
        SetFramesPerSecond(30.0f);
        SetUpdatesPerSecond(30.0f);
    }

    @Override
    public void Init() {
        cube = LoadModelFromOBJ("Monkey.obj");

        cube.Translate(new Vec3(0, 0, -2f));
        cube.Rotate(new Vec3(0, 0, (float) Math.toRadians(180f)));

        renderer = new SimpleRenderer(GetTarget());

        renderer.AddModel(cube);
    }

    @Override
    public void Update() {
        super.Update();

        if(InputScanner.GetKeyDown(KeyEvent.VK_W)) {
            cube.Rotate(new Vec3(-.01f, 0, 0));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_A)) {
            cube.Rotate(new Vec3(0, .01f, 0));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_S)) {
            cube.Rotate(new Vec3(.01f, 0, 0));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_D)) {
            cube.Rotate(new Vec3(0, -.01f, 0));
        }
    }

    @Override
    public void Render() {
        super.Render();
        renderer.Update();
    }
}