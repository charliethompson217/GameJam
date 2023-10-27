import processing.core.*;

public class MainSketch extends PApplet{
	Box charachter;
	public void settings() {
        size(1000, 500);
    }

    public void setup() {
        background(0,255,0);
        charachter =  new Box(this, 0,0);
    }

    public void draw() {
    	background(0,255,0);
    	charachter.Update();
    }
    
    

    public static void main(String[] args) {
        PApplet.main("MainSketch");
    }
}

