import processing.core.*;
import java.awt.Dimension;
import java.awt.Toolkit;
//import processing.sound.*;

public class MainSketch extends PApplet {

    static Scene currentScene;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) ((int) screenSize.getWidth() * 0.65);
    int height = (int) ((int) screenSize.getHeight() * 0.50);
    //SoundFile music;

    public void settings() {
        width -= width % 50;
        height -= height % 50;
        size(width, height);
    }

    public void setup() {
        currentScene = new StartScene(this, width, height);
        currentScene.setup();
        //music = new SoundFile(this, "C:\\Users\\raman\\Downloads\\trevor_lentz_cyberpunkgenesismp3\\Trevor Lentz - Cyberpunk- Genesis\\Trevor Lentz - Cyberpunk- Genesis - 09 Debug Chamber.mp3");
        //music.play();
    }

    public void draw() {
        currentScene.draw();
        /*
        if (!music.isPlaying()) {
            music.play();
        }
        */
    }

    public void keyPressed() {
        currentScene.keyPressed();
    }

    public void keyReleased() {
        currentScene.keyReleased();
    }

    public static void switchScene(Scene newScene) {
        currentScene = newScene;
        currentScene.setup();
    }

    public static void main(String[] args) {
        PApplet.main("MainSketch");
    }


}
