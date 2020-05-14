package PixelVision;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class InputScanner extends KeyAdapter{

    private static ArrayList<Integer> currentKeys, prevKeys;

    public InputScanner() {
        prevKeys 	= new ArrayList<Integer>();
        currentKeys = new ArrayList<Integer>();
    }

    public void Update() {
        prevKeys = currentKeys;
    }

    //Public Static Member Functions
    public static boolean GetKeyDown(int keycode) {
        if(currentKeys.contains(keycode)) {
            return true;
        }
        return false;
    }

    public static boolean GetKeyUp(int keycode) {
        if(prevKeys.contains(keycode) && !currentKeys.contains(keycode)) {
            return true;
        }
        return false;
    }

    public static boolean GetKeyTyped(int keycode) {
        if(!prevKeys.contains(keycode) && currentKeys.contains(keycode)) {
            return true;
        }
        return false;
    }

    //Needed for implementation of KeyListener

    @Override
    public void keyPressed(KeyEvent e) {
        if(!currentKeys.contains(e.getKeyCode()))
            currentKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(int i = 0; i < currentKeys.size(); i++) {
            if(currentKeys.get(i) == e.getKeyCode()) {
                currentKeys.remove(i);
                return;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
