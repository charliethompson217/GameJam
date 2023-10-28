import processing.core.*;

public class GrumpyBlock {
	int x;
	int y;
	int height=100;
	int width=100;
	boolean hit=false;
	PApplet p;
	PImage img;
	
	GrumpyBlock (PApplet p, PImage img, int x, int y){
		this.p=p;
		this.x=x;
		this.y=y;
		this.img=img;
	}
	void Update() {
		if(!hit) {
			p.image(img, x, y, width, height);
		}
	}
	
}
