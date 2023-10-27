import processing.core.*;

public class Box{
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
	
	Box(PApplet p, int x, int y ){
		this.parent = p;
		
		this.x=x;
		this.y=y;
				
		this.r=255;
		this.g=255;
		this.b=255;
		
		this.height=10;
		this.width=10;
		
		this.xVelocity=2;
		this.yVelocity=2;
	}
	
	void Update(){
		parent.fill(r,g,b);
		parent.rect(x, y, width, height);
		x+=xVelocity;
		y+=yVelocity;
	}
}
