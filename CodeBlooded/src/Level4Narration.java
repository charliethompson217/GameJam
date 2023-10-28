import processing.core.PApplet;

public class Level4Narration implements Scene {
	PApplet parent;
	int width;
	int height;
    public Level4Narration(PApplet parent, int width, int height) {
    	this.parent = parent;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setup() {
        // Add any setup logic here
    }

    int counter=0;
    @Override
    public void draw() {
    	counter++;
        parent.background(0);
        parent.textAlign(PApplet.CENTER);
        parent.textSize(20);
        parent.fill(255);
        parent.text("Level 4: The final stage is to escape the city by shooting the grumpy blocks and not killing the civilian balloons. \nDon’t run into the grumpy blocks.\r\n"
        		+ "", width / 2, height / 2);
        if (counter >= 60*5) {
            MainSketch.switchScene(new Level4(parent, width, height));
        }
    }

    @Override
    public void keyPressed() {
        // Add any keyPressed logic here
    }

    @Override
    public void keyReleased() {
        // Add any keyReleased logic here
    }
}
