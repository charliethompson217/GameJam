import processing.core.PApplet;

public class FinishScene implements Scene {

	PApplet parent;
	int width;
	int height;
    public FinishScene(PApplet parent, int width, int height) {
        this.parent = parent;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setup() {
        // Add any setup logic here
    }

    @Override
    public void draw() {
        parent.background(0);
        parent.textAlign(PApplet.CENTER);
        parent.textSize(20);
        parent.fill(255);
        parent.text("Congratulations! You saved the Cyberpunk city from AI's control!", width / 2, height / 2);
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

