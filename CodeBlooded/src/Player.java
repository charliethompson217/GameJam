import processing.core.*;

public class Player{
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
	
	Player(PApplet p, int x, int y, int maxX, int maxY, PImage playerHelicopter, double xScale, double yScale){
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
		
		this.minX=0;
		this.minY=0;
		this.maxX=maxX;
		this.maxY=maxY;
		
	}
	
	void Update(Boolean gameOver) {
	    parent.fill(r, g, b);
	    parent.image(playerHelicopter, x, y, width, height);

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


}
