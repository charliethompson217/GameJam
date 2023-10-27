import processing.core.*;

public class MainSketch extends PApplet{
	
	Scene currentScene;
	
	public void settings() {
        size(1000, 500);
    }

    public void setup() {
    	currentScene = new Level1(this);
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


    

    public static void main(String[] args) {
        PApplet.main("MainSketch");
    }

}

