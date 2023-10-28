import processing.core.*;

public class Bullet {
	int x;
	int y;
	PApplet p;
	Bullet(PApplet p, int x, int y){
		this.p=p;
		this.x=x+10;
		this.y=y;
		
	}
	void Update(){
		x+=10;
		p.circle(x, y, 15);
	}
	public boolean collidesWith(int checkX, int checkY, int width, int height) {
	    return x > checkX && x < checkX + width && y > checkY && y < checkY + height;
	}
}
