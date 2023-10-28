import processing.core.*;

public class Level3 implements Scene {
    PApplet p;  // Reference to the PApplet instance
    Player player;
    Boolean gameOver = false;
    int width;
    int height;

    PImage playerHelicopter;
    PImage background;
    PImage target;
    PGraphics backgroundGraphics;
    
    Target targets[];

    public Level3(PApplet p, int width, int height) {
        this.p = p;
        this.width=width;
        this.height=height;
        playerHelicopter = p.loadImage("level1Player.png");
        background = p.loadImage("level3background.png");
        target = p.loadImage("HotAirBalloon.png");
    }

    int countdown = 60*3;
    public void setup() {
    	player = new Player(p, 0, 225, width, height, playerHelicopter, 0.1, 0.1);
    	
    	p.background(255,0,0);
    	backgroundGraphics = p.createGraphics(width, height);
    	backgroundGraphics.beginDraw();
    	backgroundGraphics.image(background, 0, 0);
    	backgroundGraphics.endDraw();
    	
    	targets = new Target[6];
    	for(int i=0; i<targets.length; i++) {
    		int speed = 4 + i*2;
    		if(i%2==0)
    			speed*=-1;
    		targets[i] = new Target(p, i*200+200, 250, width, height, target, 0.1, 0.1, speed);
    	}
    }

    public void draw() {
    	p.background(255,0,0);
    	p.image(backgroundGraphics, 0, 0);
    	player.Update(gameOver);
    	
    	for(int i=0; i<targets.length; i++) {
    		targets[i].Update();
    	}
    	
    	if(gameOver) {
    		countdown--;
    		p.fill(255, 255, 255);
    		p.textSize(128);
    		p.textAlign(PApplet.CENTER, PApplet.CENTER);
    		p.text("Player Lost", width/2, height/2);
    		if(countdown == 0) {
    	        MainSketch.switchScene(new Level3(p, width, height));
    	    }
    	}
    }

    public void keyPressed() {
        if (p.key == PApplet.CODED) {
            switch (p.keyCode) {
                case PApplet.UP:
                    handleUpPress();
                    break;
                case PApplet.DOWN:
                    handleDownPress();
                    break;
            }
        } else {
            switch (p.key) {
                case 'W':
                case 'w':
                    handleUpPress();
                    break;
                case 'S':
                case 's':
                    handleDownPress();
                    break;
            }
        }
    }

    public void keyReleased() {
        if (p.key == PApplet.CODED) {
            switch (p.keyCode) {
                case PApplet.UP:
                    handleUpRelease();
                    break;
                case PApplet.DOWN:
                    handleDownRelease();
                    break;
            }
        } else {
            switch (p.key) {
                case 'W':
                case 'w':
                    handleUpRelease();
                    break;
                case 'S':
                case 's':
                    handleDownRelease();
                    break;
            }
        }
    }

    // Arrow keys press handlers
    void handleUpPress() {
        player.yVelocity=-3;
    }

    void handleDownPress() {
    	player.yVelocity=3;
    }

    // Arrow keys release handlers
    void handleUpRelease() {
    	player.yVelocity=0;
    }

    void handleDownRelease() {
    	player.yVelocity=0;
    }
    

}
