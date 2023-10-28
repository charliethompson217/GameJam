import processing.core.PApplet;
import processing.core.PImage;

public class Robot{
	PApplet parent;
	
	int x;
	int y;
	
	float r;
	float g;
	float b;
	
	int height;
	int width;
	
	int xVelocity;
	int yVelocity;
	
	int minX;
	int maxX;
	int minY;
	int maxY;
	
	boolean gameWon=false;
	
	PImage roboImage;
	
	Robot(PApplet p, PImage roboImage, int screenWidth, int screenHeight) {
        this.parent = p;
        this.roboImage = roboImage;

        this.r = 255;
        this.g = 255;
        this.b = 255;

        int screenEdge = (int) (Math.random() * 4);  // Randomly select one of the 4 screen edges.

        switch (screenEdge) {
            case 0:  // Top edge
                this.x = (int) (10+screenWidth/3+Math.random()*(screenWidth/4));
                this.y = 0;
                this.yVelocity = 1;
                break;
            case 1:  // Bottom edge
                this.x = (int) (10+screenWidth/3+Math.random()*(screenWidth/4));
                this.y = screenHeight;
                this.yVelocity = -1;
                break;
                
                
            case 2:  // Left edge
                this.x = 0;
                this.y = (int) (10+screenHeight/4+Math.random()*(screenHeight/3));
                this.xVelocity = 1;
                break;
            case 3:  // Right edge
                this.x = screenWidth;
                this.y = (int) (10+screenHeight/4+Math.random()*(screenHeight/3));
                this.xVelocity = -1;
                break;
        }

        this.height = screenHeight / 15;  // You can adjust the scale as per your requirements.
        this.width = screenWidth / 15;    // You can adjust the scale as per your requirements.

    }
	
	void Update() {
	    parent.fill(r, g, b);
	    parent.image(roboImage, x, y, width, height);

	    // calculate new x and y positions after movement
	    x += xVelocity;
	    y += yVelocity;
	    
	}
	
	public boolean collidesWith(float otherX, float otherY, float otherWidth, float otherHeight) {
	    return this.x < otherX + otherWidth && 
	           this.x + this.width > otherX && 
	           this.y < otherY + otherHeight && 
	           this.y + this.height > otherY;
	}


}
