import processing.core.*;
import java.awt.Dimension;
import java.awt.Toolkit;

public class MainSketch extends PApplet{
	
	static Scene currentScene;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	
	public void settings() {
		fullScreen();
    }

    public void setup() {
    	currentScene = new Level3(this, width, height);
        currentScene.setup();
    }

    public void draw() {
    	currentScene.draw();
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


