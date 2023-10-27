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
	
	Player(PApplet p, int x, int y ){
		this.parent = p;
		
		this.x=x;
		this.y=y;
				
		this.r=255;
		this.g=255;
		this.b=255;
		
		this.height=50;
		this.width=50;
		
		this.xVelocity=0;
		this.yVelocity=0;
	}
	
	void Update(){
		parent.fill(r,g,b);
		parent.rect(x, y, width, height);
		x+=xVelocity;
		y+=yVelocity;
	}
}
