import PixelVision.*;

public class Driver {
    public static void main(String[] args) {
        Game g = new Game();

        try {
            g.Start();
        } catch(Exception e) {

        }
    }
}

class Game extends Engine {

    public Game() {
        SetScreenSize(200, 200, 4);
        SetTitle("Engine Implementation!");
    }

}