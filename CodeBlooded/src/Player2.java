import processing.core.*;

public class Player2{
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
	
	PImage playerHelicopter;
	
	Player2(PApplet p, int x, int y, int minX, int minY, int maxX, int maxY, PImage playerHelicopter, double xScale, double yScale){
		this.parent = p;
		this.playerHelicopter = playerHelicopter;
		this.x=x;
		this.y=y;
				
		this.r=255;
		this.g=255;
		this.b=255;
		
		this.height = (int) (yScale * maxY);
		this.width = (int) (xScale * maxY);
		
		this.xVelocity=0;
		this.yVelocity=0;
		
		this.minX=minX;
		this.minY=minY;
		this.maxX=maxX;
		this.maxY=maxY;
		
	}
	
	void Update(Boolean gameOver) {
	    parent.fill(r, g, b);
	    // Calculate rotation angle towards the mouse
	    float angle = calculateRotationAngle(parent.mouseX, parent.mouseY);

	    // Draw the player image rotated towards the mouse
	    parent.pushMatrix();
	    parent.translate(this.x + this.width / 2, this.y + this.height / 2); // Move to the center of the player image
	    parent.rotate(angle ); // Rotate the image
	    parent.image(playerHelicopter, -this.width / 2, -this.height / 2, this.width, this.height); // Draw the image centered
	    parent.popMatrix();
	    
	    if(!gameOver) {
		    // calculate new x and y positions after movement
		    int newX = x + xVelocity;
		    int newY = y + yVelocity;
	
		    // check if the new x position (after moving) would be outside the bounds
		    if (newX + width <= maxX && newX >= minX) {
		        x = newX;
		    } else if (newX + width > maxX) {
		        x = maxX - width;  // adjust to just inside the right boundary
		        gameWon=true;
		    } else if (newX < minX) {
		        x = minX;  // adjust to just inside the left boundary
		    }
	
		    // check if the new y position (after moving) would be outside the bounds
		    if (newY + height <= maxY && newY >= minY) {
		        y = newY;
		    } else if (newY + height > maxY) {
		        y = maxY - height;  // adjust to just inside the bottom boundary
		    } else if (newY < minY) {
		        y = minY;  // adjust to just inside the top boundary
		    }
	    }
	}
	
	public boolean collidesWith(float otherX, float otherY, float otherWidth, float otherHeight) {
	    return this.x < otherX + otherWidth && 
	           this.x + this.width > otherX && 
	           this.y < otherY + otherHeight && 
	           this.y + this.height > otherY;
	}
	float calculateRotationAngle(int mouseX, int mouseY) {
	    float angle = (float) Math.atan2(mouseY - this.y, mouseX - this.x);
	    return angle;
	}


}
