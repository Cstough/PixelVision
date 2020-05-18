import PixelVision.Engine;
import PixelVision.InputScanner;
import PixelVision.Math.Mat4x4;
import PixelVision.Math.Vec3;
import PixelVision.Rendering.*;

import java.awt.event.KeyEvent;

import static PixelVision.Rendering.ModelUtil.*;

public class Driver extends Engine{

    RenderingEngine renderer;
    Model model;

    public Driver() {
        SetClearColor(Color.CYAN);
        SetFramesPerSecond(20.0f);
        SetUpdatesPerSecond(20.0f);
        SetTitle("New Engine!");
        SetScreenSize(256, 256, 3);
    }

    public static void main(String[] args) throws InterruptedException {
        Driver driver = new Driver();
        driver.Start();
    }

    @Override
    public void Init() {
        model = ModelUtil.LoadModelFromOBJ("src/Assets/Leon/leon.obj");
        renderer = new RenderingEngine(GetTarget());
        renderer.SetProjectionMatrix(Mat4x4.GetProjection(90f, 1, 0.1f, 100f));
        renderer.SetGlobalLightingRotation(new Vec3(1, 1, 1));
        renderer.AddModel(model);


    }

    @Override
    public void Update() {
        super.Update();

        if(InputScanner.GetKeyDown(KeyEvent.VK_W)) {
            model.Move(new Vec3(0, 0, -0.1f));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_A)) {
            model.Move(new Vec3(-0.1f, 0, 0));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_S)) {
            model.Move(new Vec3(0, 0, 0.1f));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_D)) {
            model.Move(new Vec3(0.1f, 0, 0));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_Z)) {
            model.Move(new Vec3(0, -0.3f, 0));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_X)) {
            model.Move(new Vec3(0, 0.3f, 0));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_Q)) {
            model.Rotate(new Vec3(0, -0.1f, 0));
        }
        if(InputScanner.GetKeyDown(KeyEvent.VK_E)) {
            model.Rotate(new Vec3(0, 0.1f, 0));
        }
    }

    @Override
    public void Render() {
        super.Render();
        renderer.Render();

    }
}
