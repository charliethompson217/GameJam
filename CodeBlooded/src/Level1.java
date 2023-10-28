import java.util.ArrayList;

import processing.core.*;

public class Level1 implements Scene {
    PApplet p;  // Reference to the PApplet instance

    int width;
    int height;

    PImage background;
    PImage guy;
    PImage robo;
    PImage helicopter;
    PGraphics backgroundGraphics;
    Player2 player;
    boolean gameOver = false;
    boolean next = false;
    int interval = 60;
    
    boolean shooting = false;
    ArrayList<Bullet2> bullets = new ArrayList<Bullet2>();
    ArrayList<Robot> robots = new ArrayList<Robot>();
    
    public Level1(PApplet p, int width, int height) {
        this.p = p;
        this.width=width;
        this.height=height;
        background = p.loadImage("level4background.png");
        guy = p.loadImage("guy.png");
        robo = p.loadImage("robot2.png");
        helicopter = p.loadImage("helicopter.png");
    }

    int countdown = 60*3;
    public void setup() {
    	p.background(255,0,0);
    	backgroundGraphics = p.createGraphics(width, height);
    	backgroundGraphics.beginDraw();
    	backgroundGraphics.image(background, 0, 0, width, height);
    	backgroundGraphics.endDraw();
    	player = new Player2(p, width/2, height/2, (width/3), (height/4), (width/3)*2, (height/4)*3, guy, 0.1, 0.1);
    }
    int frame=0;
    int time=0;
    public void draw() {
        frame++;
        time++;
        p.background(255,0,0);
        p.image(backgroundGraphics, 0, 0);

        // Update and display bullets
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet2 b = bullets.get(i);
            b.update();
            b.display();

            // Check for collisions between this bullet and all robots
            for (int j = robots.size() - 1; j >= 0; j--) {
                Robot r = robots.get(j);
                if (r.collidesWith(b.x, b.y, 5, 5)) {  // bullet size is 5x5 as per the display method
                    robots.remove(j);
                    bullets.remove(i);
                    break;  // break out of inner loop as bullet is removed
                }
            }
        }

        if(!next)
	        for (int i = robots.size() - 1; i >= 0; i--) {
	            Robot r = robots.get(i);
	            r.Update();
	
	            // Check for collisions between the robot and the player
	            if (r.collidesWith(player.x, player.y, player.width, player.height)) {
	                gameOver = true;
	                return;  // Exit out of draw method early since we're switching scenes
	            }
	        }

        if(frame % (interval) == 0) {
            robots.add(new Robot(p, robo, width, height));
        }
        if(frame % 200==0) {
        	interval--;
        	frame=0;
        }
        if(time>=60*15) {
        	p.image(helicopter, (width - 200)/2, (height - 200)/2, 200, 200);
        	next=true;
        }
        if(time>=60*20) {
        	MainSketch.switchScene(new Level2Narration(p, width, height));
        }

        player.Update(gameOver);
        if(gameOver) {
    		countdown--;
    		p.fill(255, 255, 255);
    		p.textSize(128);
    		p.textAlign(PApplet.CENTER, PApplet.CENTER); // sets the text alignment to center
    		p.text("Player Lost", width/2, height/2);
    		if(countdown == 0) {
    	        MainSketch.switchScene(new Level1(p, width, height));
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
                case PApplet.LEFT:
                    handleLeftPress();
                    break;
                case PApplet.RIGHT:
                    handleRightPress();
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
                case 'A':
                case 'a':
                    handleLeftPress();
                    break;
                case 'D':
                case 'd':
                    handleRightPress();
                    break;
                case ' ':
                    bullets.add(new Bullet2(p, player.x + player.width / 2, player.y + player.height / 2, p.mouseX, p.mouseY));
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
                case PApplet.LEFT:
                    handleLeftRelease();
                    break;
                case PApplet.RIGHT:
                    handleRightRelease();
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
                case 'A':
                case 'a':
                    handleLeftRelease();
                    break;
                case 'D':
                case 'd':
                    handleRightRelease();
                    break;
            }
        }
    }

    // Arrow keys press handlers
    void handleUpPress() {
        player.yVelocity=-2;
    }

    void handleDownPress() {
    	player.yVelocity=2;
    }

    void handleLeftPress() {
    	player.xVelocity=-2;
    }

    void handleRightPress() {
    	player.xVelocity=2;
    }

    // Arrow keys release handlers
    void handleUpRelease() {
    	player.yVelocity=0;
    }

    void handleDownRelease() {
    	player.yVelocity=0;
    }

    void handleLeftRelease() {
    	player.xVelocity=0;
    }

    void handleRightRelease() {
    	player.xVelocity=0;
    }    
    

}
