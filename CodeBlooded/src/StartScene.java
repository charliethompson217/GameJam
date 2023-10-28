import processing.core.PApplet;

public class StartScene implements Scene {
    private final PApplet parent;
    private final int width;
    private final int height;

    public StartScene(PApplet parent, int width, int height) {
        this.parent = parent;
        this.width = width;
        this.height = height;
    }

    public void display() {
       
    }

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		
	}

	int counter=0;
	@Override
	public void draw() {
		counter++;
		parent.textAlign(PApplet.CENTER);
        parent.textSize(20);
        parent.fill(255);
        parent.text("Game Name: Year 3000 \nYou only get one Mission \n\nObjective: You are a skilled hacker seeking to disrupt a powerful AI’s control over a sprawling metropolis. \nFight against hacking puzzles, stealth gameplay, and combat sequences and various levels\n set against the backdrop of a retro-futuristic cyberpunk city!", width / 2, height / 2);
		if(counter>=60*10)
		{
			MainSketch.switchScene(new Level1Narration(parent, width, height));
		}
	}

	@Override
	public void keyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased() {
		// TODO Auto-generated method stub
		
	}
}
