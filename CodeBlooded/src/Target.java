import processing.core.*;

public class Target{
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
	PImage img;
	
	Target(PApplet p, int x, int y, int maxX, int maxY, PImage img, double xScale, double yScale, int speed){
		this.parent = p;
		this.img = img;
		this.x=x;
		this.y=y;
				
		this.r=255;
		this.g=255;
		this.b=255;
		
		this.height = (int) (yScale * maxY);
		this.width = (int) (xScale * maxY);
		
		this.xVelocity=0;
		this.yVelocity=speed;
		
		this.minX=0;
		this.minY=0;
		this.maxX=maxX;
		this.maxY=maxY;
		
	}
	
	void Update() {
	    parent.fill(r, g, b);
	    parent.image(img, x, y, width, height);

    	x+=xVelocity;
        y+=yVelocity;
        if(y<minY || y>maxY-height) {
            yVelocity*=-1;
        }
        if(x<minX || x>maxX-width) {
            xVelocity*=-1;
        }
	    
	}

}
